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
