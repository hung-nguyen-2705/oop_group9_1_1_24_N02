package gr9;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String choose = null;
        StudentManager studentManager = new StudentManager();
        int studentId;

        // show menu
        showMenu();
        while (true) {
            try {
                choose = scanner.nextLine();  // Nhập lựa chọn từ người dùng
                switch (choose) {
                    case "1":
                        studentManager.add();
                        break;
                    case "2":
                        studentId = studentManager.inputId();
                        studentManager.edit(studentId);
                        break;
                    case "3":
                        studentId = studentManager.inputId();
                        studentManager.delete(studentId);
                        break;
                    case "4":
                        studentManager.sortStudentByGPA();
                        break;
                    case "5":
                        studentManager.sortStudentByName();
                        break;
                    case "6":
                        studentManager.show();
                        break;
                    case "0":
                        System.out.println("exited!");
                        scanner.close();  // Đóng scanner trước khi thoát
                        return;  // Thoát khỏi chương trình
                    default:
                        System.out.println("Invalid input! Please choose a valid action from the menu:");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number for the ID.");
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input type. Please enter a valid choice.");
                scanner.next();  // Xóa đầu vào không hợp lệ để tiếp tục chương trình
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }

            // Show menu lại sau mỗi lựa chọn
            showMenu();
        }
    }

    /**
     * create menu
     */
    public static void showMenu() {
        System.out.println("-----------menu------------");
        System.out.println("1. Add student.");
        System.out.println("2. Edit student by id.");
        System.out.println("3. Delete student by id.");
        System.out.println("4. Sort student by gpa.");
        System.out.println("5. Sort student by name.");
        System.out.println("6. Show student.");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }
}

