import java.sql.*;
import java.time.LocalDate;

public class LibraryService {

    public void addBook(String title, String author,
                        int copies) throws SQLException {
        String sql = "INSERT INTO books " +
            "(title, author, total_copies, available_copies) " +
            "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, copies);
            ps.setInt(4, copies);
            ps.executeUpdate();

            System.out.println("Book added successfully.");
        }
    }

    public void viewBooks() throws SQLException {
        String sql = "SELECT * FROM books ORDER BY book_id";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println(
                "\nID | Title | Author | Available"
            );

            while (rs.next()) {
                System.out.println(
                    rs.getInt("book_id") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("author") + " | " +
                    rs.getInt("available_copies")
                );
            }
        }
    }

    public void addStudent(String name, String email)
            throws SQLException {
        String sql = "INSERT INTO students " +
            "(student_name, email) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.executeUpdate();

            System.out.println("Student registered successfully.");
        }
    }

    public void issueBook(int bookId, int studentId)
            throws SQLException {

        String updateBook = "UPDATE books " +
            "SET available_copies = available_copies - 1 " +
            "WHERE book_id = ? AND available_copies > 0";

        String insertIssue = "INSERT INTO issue_records " +
            "(book_id, student_id, issue_date) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);

            try {
                try (PreparedStatement ps =
                         con.prepareStatement(updateBook)) {
                    ps.setInt(1, bookId);

                    if (ps.executeUpdate() == 0) {
                        throw new SQLException(
                            "Book unavailable or book ID invalid."
                        );
                    }
                }

                try (PreparedStatement ps =
                         con.prepareStatement(insertIssue)) {
                    ps.setInt(1, bookId);
                    ps.setInt(2, studentId);
                    ps.setDate(3,
                        Date.valueOf(LocalDate.now()));
                    ps.executeUpdate();
                }

                con.commit();
                System.out.println("Book issued successfully.");

            } catch (SQLException e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }

    public void returnBook(int issueId) throws SQLException {

        String findIssue = "SELECT book_id FROM issue_records " +
            "WHERE issue_id = ? AND returned = FALSE";

        String markReturned = "UPDATE issue_records " +
            "SET returned = TRUE, return_date = ? " +
            "WHERE issue_id = ? AND returned = FALSE";

        String updateBook = "UPDATE books " +
            "SET available_copies = available_copies + 1 " +
            "WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);

            try {
                int bookId;

                try (PreparedStatement ps =
                         con.prepareStatement(findIssue)) {
                    ps.setInt(1, issueId);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            throw new SQLException(
                                "Invalid issue ID or book already returned."
                            );
                        }
                        bookId = rs.getInt("book_id");
                    }
                }

                try (PreparedStatement ps =
                         con.prepareStatement(markReturned)) {
                    ps.setDate(1,
                        Date.valueOf(LocalDate.now()));
                    ps.setInt(2, issueId);
                    ps.executeUpdate();
                }

                try (PreparedStatement ps =
                         con.prepareStatement(updateBook)) {
                    ps.setInt(1, bookId);
                    ps.executeUpdate();
                }

                con.commit();
                System.out.println("Book returned successfully.");

            } catch (SQLException e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }
}
