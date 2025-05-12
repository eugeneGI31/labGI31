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
