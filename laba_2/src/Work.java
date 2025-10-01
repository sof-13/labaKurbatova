import java.util.Objects;

public class Work {
    private WorkType type;
    private int quantity;

    public Work(WorkType type, int quantity) {
        this.type = type;
        this.quantity = quantity; // валидация в Main
    }

    public WorkType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public double calculatePayment() {
        return type.getRate() * quantity;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Work finalized: " + type + " x" + quantity);
        super.finalize();
    }
}