import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String fullName;
    private List<Work> works;

    public Employee(String fullName) {
        this.fullName = fullName;
        this.works = new ArrayList<>();
    }

    public void addWork(Work work) {
        works.add(work);
    }

    public String getFullName() {
        return fullName;
    }

    public double calculateSalary() {
        return works.stream().mapToDouble(Work::calculatePayment).sum();
    }

    // деструктор
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Employee finalized: " + fullName);
        super.finalize();
    }
}