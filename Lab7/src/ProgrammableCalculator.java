// Демонстрация работы
public class ProgrammableCalculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        CalculatorKeyboard keyboard = new CalculatorKeyboard(calculator);

        // Демонстрация работы калькулятора
        System.out.println("--- Тестирование базовых операций ---");
        keyboard.pressButton("5");
        keyboard.pressButton("3");
        keyboard.pressButton("+");
        keyboard.pressButton("2");
        keyboard.pressButton("0");
        keyboard.pressButton("=");
        keyboard.pressButton("C");

        System.out.println("\n--- Тестирование специальных функций ---");
        keyboard.pressButton("9");
        keyboard.pressButton("√");
        keyboard.pressButton("x²");
        keyboard.pressButton("1/x");

        System.out.println("\n--- Переназначение кнопки и тестирование ---");
        // Переназначаем кнопку "x²" на новую команду (например, куб)
        keyboard.reassignButton("x²", new Command() {
            private double previousValue;

            @Override
            public void execute() {
                previousValue = calculator.getCurrentValue();
                calculator.setCurrentValue(Math.pow(calculator.getCurrentValue(), 3));
                System.out.println("Вычислен куб числа");
            }

            @Override
            public void undo() {
                calculator.setCurrentValue(previousValue);
            }
        });

        keyboard.pressButton("2");
        keyboard.pressButton("x²"); // Теперь это вычисление куба
    }
}