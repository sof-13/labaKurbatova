public class Main {
    public static void main(String[] args) {
        try {
            HR_Department hr = new HR_Department("ООО Ромашка", 50, 160, 1500, 0.13);

            System.out.println("Информация о компании:");
            System.out.println(hr);

            // Можно применить изменения через сеттеры
            hr.setEmployeesCount(55);
            hr.setHoursPerMonth(720);
            hr.setPayPerHour(1200);
            hr.setIncomeTax(0.15);

            System.out.println("\nОбновлённая информация о компании:");
            System.out.println(hr);

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}