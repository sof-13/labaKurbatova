import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SalaryDepartment department = new SalaryDepartment();

        try {
            department.addWorkType("Тестирование", new BasePayStrategy(2500.0));
            department.addWorkType("Анализ данных", new BonusPayStrategy(4000.0, 15.0));
            department.addWorkType("Разработка", new BonusPayStrategy(6000.0, 10.0));
            department.addWorkType("Документирование", new BasePayStrategy(2000.0));
        } catch (InvalidPayException e) {
            System.err.println("Ошибка при инициализации данных: " + e.getMessage());
        }

        while (true) {
            showMenu();
            int choice = getIntInput();
            switch (choice) {
                case 1 -> addWorkType(department);
                case 2 -> displayWorkTypes(department);
                case 3 -> calculateAveragePay(department);
                case 0 -> {
                    System.out.println("Выход из программы.");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== Отдел расчёта зарплаты ===");
        System.out.println("1. Добавить новый тип работы");
        System.out.println("2. Показать все типы работ");
        System.out.println("3. Рассчитать среднюю оплату");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private static void addWorkType(SalaryDepartment department) {
        System.out.print("Введите название работы: ");
        String name = scanner.nextLine();

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Ошибка: Название работы не может быть пустым!");
            return;
        }
        String trimmedName = name.trim();
        if (department.isWorkExists(trimmedName)) {
            System.out.println("Ошибка: Работа с названием '" + trimmedName + "' уже существует!");
            return;
        }

        System.out.print("Есть ли надбавка? (да/нет): ");
        String bonusAnswer = scanner.nextLine().trim().toLowerCase();

        try {
            if (bonusAnswer.equals("да")) {
                System.out.print("Введите базовую ставку: ");
                double base = getDoubleInput();
                System.out.print("Введите процент надбавки (только от 0 до 100, не включая границы): ");
                double bonus = getDoubleInput();
                department.addWorkTypeUnsafe(trimmedName, new BonusPayStrategy(base, bonus));
                System.out.println("Тип работы с надбавкой добавлен.");
            } else if (bonusAnswer.equals("нет")) {
                System.out.print("Введите базовую ставку: ");
                double base = getDoubleInput();
                department.addWorkTypeUnsafe(trimmedName, new BasePayStrategy(base));
                System.out.println("Тип работы без надбавки добавлен.");
            } else {
                System.out.println("Некорректный ответ. Добавление отменено.");
            }
        } catch (InvalidPayException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void displayWorkTypes(SalaryDepartment department) {
        System.out.println("\n--- Список типов работ ---");
        department.displayAllWorkTypes();
    }

    private static void calculateAveragePay(SalaryDepartment department) {
        try {
            double avg = department.getAveragePay();
            System.out.printf("Средняя оплата: %.2f руб.\n", avg);
        } catch (InvalidPayException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Введите число: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }

    private static double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Введите корректное число: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        return value;
    }
}