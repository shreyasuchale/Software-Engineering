public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {

        if (b == 0)
            throw new ArithmeticException("Division by Zero");

        return a / b;
    }
    public static void main(String[] args) {
        System.out.println("Calculator is running...");
        
        // Create an instance of your calculator or call your methods here
        // Calculator calc = new Calculator();
    }
}