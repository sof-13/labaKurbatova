public class HR_Department {
    // Поля класса
    private String companyName;
    private int employeesCount;
    private double hoursPerMonth;
    private double payPerHour;
    private double incomeTax;

    // Конструктор
    public HR_Department(String companyName, int employeesCount, double hoursPerMonth, double payPerHour, double incomeTax) {
        this.companyName = companyName;
        this.employeesCount = employeesCount;
        this.hoursPerMonth = hoursPerMonth;
        this.payPerHour = payPerHour;
        this.incomeTax = incomeTax;
    }

    // Геттеры
    public String getCompanyName() {
        return companyName;
    }

    public int getEmployeesCount() {
        return employeesCount;
    }

    public double getHoursPerMonth() {
        return hoursPerMonth;
    }

    public double getPayPerHour() {
        return payPerHour;
    }

    // incomeTax — только для записи

    // Сеттеры
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEmployeesCount(int employeesCount) {
        if (employeesCount <= 0) {
            throw new IllegalArgumentException("Число работников должно быть больше 0.");
        }
        this.employeesCount = employeesCount;
    }

    public void setHoursPerMonth(double hoursPerMonth) {
        if (hoursPerMonth <= 0) {
            throw new IllegalArgumentException("Норма часов должна быть больше 0.");
        }
        if (hoursPerMonth > 720) {
            throw new IllegalArgumentException("Норма часов не может превышать 720.");
        }
        this.hoursPerMonth = hoursPerMonth;
    }

    public void setPayPerHour(double payPerHour) {
        if (payPerHour <= 0) {
            throw new IllegalArgumentException("Оплата за час должна быть положительной.");
        }
        this.payPerHour = payPerHour;
    }

    // Сеттер для свойства только для записи
    public void setIncomeTax(double incomeTax) {
        if (incomeTax < 0 || incomeTax > 1) {
            throw new IllegalArgumentException("Ставка налога должна быть в диапазоне [0, 1].");
        }
        this.incomeTax = incomeTax;
    }

    // Метод для подсчета общей выплаты по подоходному налогу
    public double calculateTotalIncomeTax() {
        double totalSalary = employeesCount * hoursPerMonth * payPerHour;
        return totalSalary * incomeTax;
    }

    @Override
    public String toString() {
        return "HRDepartment{" +
                "companyName='" + companyName + '\'' +
                ", employeeCount=" + employeesCount +
                ", hoursPerMonth=" + hoursPerMonth +
                ", payPerHour=" + payPerHour +
                ", incomeTax=скрыто" +  // только для записи
                ", totalIncomeTax=" + calculateTotalIncomeTax() +
                '}';
    }
}