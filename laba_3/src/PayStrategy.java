public interface PayStrategy {
    double calculatePay() throws InvalidPayException;
    String getBonusInfo();
}