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
