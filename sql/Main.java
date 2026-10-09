import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryService service = new LibraryService();

        while (true) {
            System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Register Student");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Book title: ");
                        String title = sc.nextLine();

                        System.out.print("Author: ");
                        String author = sc.nextLine();

                        System.out.print("Number of copies: ");
                        int copies = Integer.parseInt(sc.nextLine());

                        if (copies < 1) {
                            System.out.println(
                                "Copies must be at least 1."
                            );
                            break;
                        }

                        service.addBook(title, author, copies);
                        break;

                    case 2:
                        service.viewBooks();
                        break;

                    case 3:
                        System.out.print("Student name: ");
                        String name = sc.nextLine();

                        System.out.print("Student email: ");
                        String email = sc.nextLine();

                        service.addStudent(name, email);
                        break;

                    case 4:
                        System.out.print("Book ID: ");
                        int bookId = Integer.parseInt(sc.nextLine());

                        System.out.print("Student ID: ");
                        int studentId =
                            Integer.parseInt(sc.nextLine());

                        service.issueBook(bookId, studentId);
                        break;

                    case 5:
                        System.out.print("Issue record ID: ");
                        int issueId = Integer.parseInt(sc.nextLine());

                        service.returnBook(issueId);
                        break;

                    case 6:
                        System.out.println("Goodbye!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }
}
