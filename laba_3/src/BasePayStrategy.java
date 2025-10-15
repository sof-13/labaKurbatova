public class BasePayStrategy implements PayStrategy {
    private double baseRate;

    public BasePayStrategy(double baseRate) throws InvalidPayException {
        if (baseRate <= 0) {
            throw new InvalidPayException("Базовая ставка должна быть положительной!");
        }
        this.baseRate = baseRate;
    }

    @Override
    public double calculatePay() {
        return baseRate;
    }

    @Override
    public String getBonusInfo() {
        return "";
    }
}