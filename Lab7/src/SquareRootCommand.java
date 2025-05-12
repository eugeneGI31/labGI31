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
