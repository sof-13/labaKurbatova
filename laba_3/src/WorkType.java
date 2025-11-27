public class WorkType {
    private String name;
    private PayStrategy strategy;

    public WorkType(String name, PayStrategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public String getName() {
        return name;
    }

    public double getPay() throws InvalidPayException {
        return strategy.calculatePay();
    }

    @Override
    public String toString() {
        try {
            return String.format("Работа: %s, Оплата: %.2f%s",
                    name, getPay(), strategy.getBonusInfo());
        } catch (InvalidPayException e) {
            return "Ошибка при расчёте оплаты для " + name;
        }
    }
}