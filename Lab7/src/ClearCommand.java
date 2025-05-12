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
