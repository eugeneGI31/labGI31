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
