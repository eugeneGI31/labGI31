import java.util.HashMap;
import java.util.Map;

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
