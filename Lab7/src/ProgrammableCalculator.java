import java.util.HashMap;
import java.util.Map;

// Интерфейс команды
interface Command {
    void execute();
    void undo();
}

// Ресивер - калькулятор, который выполняет основные операции
class Calculator {
    private double currentValue;
    private double previousValue;
    private String lastOperation;

    public Calculator() {
        currentValue = 0;
        previousValue = 0;
        lastOperation = "";
    }

    public void inputDigit(int digit) {
        currentValue = currentValue * 10 + digit;
    }

    public void performOperation(String operation) {
        if (!lastOperation.isEmpty()) {
            calculate();
        }
        previousValue = currentValue;
        currentValue = 0;
        lastOperation = operation;
    }

    private void calculate() {
        switch (lastOperation) {
            case "+":
                currentValue = previousValue + currentValue;
                break;
            case "-":
                currentValue = previousValue - currentValue;
                break;
            case "*":
                currentValue = previousValue * currentValue;
                break;
            case "/":
                if (currentValue != 0) {
                    currentValue = previousValue / currentValue;
                }
                break;
        }
        lastOperation = "";
    }

    public void clear() {
        currentValue = 0;
        previousValue = 0;
        lastOperation = "";
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double value) {
        this.currentValue = value;
    }

    public void square() {
        currentValue = Math.pow(currentValue, 2);
    }

    public void squareRoot() {
        if (currentValue >= 0) {
            currentValue = Math.sqrt(currentValue);
        }
    }

    public void inverse() {
        if (currentValue != 0) {
            currentValue = 1 / currentValue;
        }
    }
}

// Конкретные команды
class DigitCommand implements Command {
    private Calculator calculator;
    private int digit;

    public DigitCommand(Calculator calculator, int digit) {
        this.calculator = calculator;
        this.digit = digit;
    }

    @Override
    public void execute() {
        calculator.inputDigit(digit);
    }

    @Override
    public void undo() {
        // Простое undo - сброс значения
        calculator.setCurrentValue(0);
    }
}

class OperationCommand implements Command {
    private Calculator calculator;
    private String operation;

    public OperationCommand(Calculator calculator, String operation) {
        this.calculator = calculator;
        this.operation = operation;
    }

    @Override
    public void execute() {
        calculator.performOperation(operation);
    }

    @Override
    public void undo() {
        calculator.clear();
    }
}

class ClearCommand implements Command {
    private Calculator calculator;
    private double savedValue;

    public ClearCommand(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        savedValue = calculator.getCurrentValue();
        calculator.clear();
    }

    @Override
    public void undo() {
        calculator.setCurrentValue(savedValue);
    }
}

class SquareCommand implements Command {
    private Calculator calculator;
    private double previousValue;

    public SquareCommand(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        previousValue = calculator.getCurrentValue();
        calculator.square();
    }

    @Override
    public void undo() {
        calculator.setCurrentValue(previousValue);
    }
}

class SquareRootCommand implements Command {
    private Calculator calculator;
    private double previousValue;

    public SquareRootCommand(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        previousValue = calculator.getCurrentValue();
        calculator.squareRoot();
    }

    @Override
    public void undo() {
        calculator.setCurrentValue(previousValue);
    }
}

class InverseCommand implements Command {
    private Calculator calculator;
    private double previousValue;

    public InverseCommand(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        previousValue = calculator.getCurrentValue();
        calculator.inverse();
    }

    @Override
    public void undo() {
        calculator.setCurrentValue(previousValue);
    }
}

// Класс кнопки калькулятора
class CalculatorButton {
    private String label;
    private Command command;

    public CalculatorButton(String label, Command command) {
        this.label = label;
        this.command = command;
    }

    public void press() {
        System.out.println("Нажата кнопка: " + label);
        command.execute();
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String getLabel() {
        return label;
    }
}

// Клавиатура калькулятора
class CalculatorKeyboard {
    private Map<String, CalculatorButton> buttons;
    private Calculator calculator;

    public CalculatorKeyboard(Calculator calculator) {
        this.calculator = calculator;
        this.buttons = new HashMap<>();
        initializeButtons();
    }

    private void initializeButtons() {
        // Цифровые кнопки (0-9)
        for (int i = 0; i < 10; i++) {
            String label = String.valueOf(i);
            buttons.put(label, new CalculatorButton(label, new DigitCommand(calculator, i)));
        }

        // Кнопки операций
        buttons.put("+", new CalculatorButton("+", new OperationCommand(calculator, "+")));
        buttons.put("-", new CalculatorButton("-", new OperationCommand(calculator, "-")));
        buttons.put("*", new CalculatorButton("*", new OperationCommand(calculator, "*")));
        buttons.put("/", new CalculatorButton("/", new OperationCommand(calculator, "/")));
        buttons.put("=", new CalculatorButton("=", new OperationCommand(calculator, "=")));

        // Специальные кнопки
        buttons.put("C", new CalculatorButton("C", new ClearCommand(calculator)));
        buttons.put("√", new CalculatorButton("√", new SquareRootCommand(calculator)));
        buttons.put("x²", new CalculatorButton("x²", new SquareCommand(calculator)));
        buttons.put("1/x", new CalculatorButton("1/x", new InverseCommand(calculator)));
    }

    public void pressButton(String label) {
        if (buttons.containsKey(label)) {
            buttons.get(label).press();
            System.out.println("Текущее значение: " + calculator.getCurrentValue());
        } else {
            System.out.println("Кнопка " + label + " не найдена");
        }
    }

    public void reassignButton(String label, Command newCommand) {
        if (buttons.containsKey(label)) {
            buttons.get(label).setCommand(newCommand);
            System.out.println("Кнопка " + label + " переназначена");
        } else {
            System.out.println("Кнопка " + label + " не найдена для переназначения");
        }
    }
}

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