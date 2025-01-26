import java.util.Stack;

public class InfixToPrefix {
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    private static String reverse(String input) {
        StringBuilder reversed = new StringBuilder(input).reverse();
        for (int i = 0; i < reversed.length(); i++) {
            if (reversed.charAt(i) == '(') {
                reversed.setCharAt(i, ')');
            } else if (reversed.charAt(i) == ')') {
                reversed.setCharAt(i, '(');
            }
        }
        return reversed.toString();
    }

    public static String infixToPrefix(String infix) {
        // Reverse the infix expression
        String reversedInfix = reverse(infix);
        Stack<Character> stack = new Stack<>();
        StringBuilder prefix = new StringBuilder();

        for (int i = 0; i < reversedInfix.length(); i++) {
            char c = reversedInfix.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                prefix.append(c);
            }
            else if (c == '(') {
                stack.push(c);
            }
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    prefix.append(stack.pop());
                }
                stack.pop(); 
            }
            else if (isOperator(c)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    prefix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            prefix.append(stack.pop());
        }

        return prefix.reverse().toString();
    }

    public static void main(String[] args) {
        String infix = "A+B*(C^D-E)^(F+G*H)-I";
        String prefix = infixToPrefix(infix);
        System.out.println("Infix Expression: " + infix);
        System.out.println("Prefix Expression: " + prefix);
    }
}
