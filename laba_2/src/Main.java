import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Запуск программы...\n");

        // SINGLETON
        System.out.println("1. Первый вызов getInstance()...");
        PayrollService dept1 = PayrollService.getInstance();

        System.out.println("2. Второй вызов getInstance()...");
        PayrollService dept2 = PayrollService.getInstance();

        System.out.println("3. Проверяем: один ли это объект?");
        if (dept1 == dept2) {
            System.out.println("ДА! Это один и тот же отдел.");
        } else {
            System.out.println("НЕТ! Это разные отделы. Ошибка!");
        }

        System.out.println("\n--- Начинаем работу программы ---\n");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            showMenu();
            int choice = getIntInput("Выберите пункт меню: ", scanner);
            switch (choice) {
                case 1 -> addWorkType();
                case 2 -> addEmployeeAndWork(scanner, dept1);
                case 3 -> getEmployeeSalary(scanner, dept1);
                case 4 -> showTotalPayroll(dept1);
                case 0 -> {
                    System.out.println("Выход из программы.");
                    running = false;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
        scanner.close();
    }


    private static void showMenu() {
        System.out.println("\n=== Отдел Расчёта Зарплаты ===");
        System.out.println("1. Показать виды работ");
        System.out.println("2. Добавить работника и его работы");
        System.out.println("3. Узнать зарплату конкретного работника");
        System.out.println("4. Показать общую сумму выплат");
        System.out.println("0. Выход");
    }

    private static void addWorkType() {
        System.out.println("Доступные виды работ:");
        for (WorkType wt : WorkType.values()) {
            System.out.printf("- %s: %.2f руб/ед.\n", wt.name(), wt.getRate());
        }
    }

    private static void addEmployeeAndWork(Scanner scanner, PayrollService payroll) {
        System.out.print("Введите имя работника: ");
        String fullName = scanner.nextLine().trim();

        if (fullName.isEmpty()) {
            System.out.println("Ошибка: имя не может быть пустым.");
            return;
        }

        if (payroll.employeeExists(fullName)) {
            System.out.println("Ошибка: работник с именем '" + fullName + "' уже существует в базе.");
            return;
        }

        Employee emp = new Employee(fullName);

        System.out.println("Доступные виды работ:");
        WorkType[] types = WorkType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.printf("%d. %s (%.2f руб/ед.)\n", i + 1, types[i].name(), types[i].getRate());
        }

        boolean adding = true;
        while (adding) {
            int typeIndex = getIntInput("Выберите номер вида работы (или 0 для завершения): ", scanner) - 1;
            if (typeIndex == -1) break;
            if (typeIndex < 0 || typeIndex >= types.length) {
                System.out.println("Неверный номер вида работы.");
                continue;
            }

            int quantity = getIntInput("Введите количество выполненных работ: ", scanner);
            if (quantity <= 0) {
                System.out.println("Ошибка: количество работ должно быть положительным.");
                continue;
            }

            Work work = new Work(types[typeIndex], quantity);
            emp.addWork(work);
            System.out.println("Работа добавлена.");

            System.out.print("Добавить ещё одну работу? (y/n): ");
            String cont = scanner.nextLine().trim().toLowerCase();
            adding = cont.equals("y") || cont.equals("yes");
        }

        payroll.addEmployee(emp);
        System.out.println("Работник '" + fullName + "' успешно добавлен.");
    }

    private static void getEmployeeSalary(Scanner scanner, PayrollService payroll) {
        System.out.print("Введите имя работника: ");
        String fullName = scanner.nextLine().trim();
        if (fullName.isEmpty()) {
            System.out.println("Имя не может быть пустым.");
            return;
        }

        var employeeOpt = payroll.findEmployeeByFullName(fullName);
        if (employeeOpt.isPresent()) {
            double salary = employeeOpt.get().calculateSalary();
            System.out.printf("Зарплата работника '%s': %.2f руб.\n", fullName, salary);
        } else {
            System.out.println("Работник с таким именем не найден.");
        }
    }

    private static void showTotalPayroll(PayrollService payroll) {
        double total = payroll.getTotalPayroll();
        System.out.printf("Общая сумма выплат всем работникам: %.2f руб.\n", total);
    }

    private static int getIntInput(String prompt, Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }
}