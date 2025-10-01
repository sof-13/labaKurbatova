import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PayrollService {
    private static PayrollService instance;
    private List<Employee> employees;

    private PayrollService() {
        employees = new ArrayList<>();
    }

    public static synchronized PayrollService getInstance() {
        if (instance == null) {
            instance = new PayrollService();
        }
        return instance;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Optional<Employee> findEmployeeByFullName(String fullName) {
        return employees.stream()
                .filter(e -> e.getFullName().equalsIgnoreCase(fullName.trim()))
                .findFirst();
    }

    public double getTotalPayroll() {
        return employees.stream().mapToDouble(Employee::calculateSalary).sum();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("PayrollService finalized.");
        super.finalize();
    }
}