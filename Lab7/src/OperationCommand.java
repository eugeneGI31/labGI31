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
