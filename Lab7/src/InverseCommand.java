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
