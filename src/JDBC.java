import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Reynaldo Mena
 *Software Development 1 - CEN 3024c
 * 4/7/2024
 * Establishing connection to SQlite database
 * as well as executing query's into database.
 */
public class JDBC {

    /**
     * getConn
     * Method that coonnects IDE to SQLite database
     *
     */
    private static Connection getConn() {
        // connect();
        Connection connection = null;
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Connect to the SQLite database
            String url = "jdbc:sqlite:lms.db";
            connection = DriverManager.getConnection(url);

            if (connection != null) {
                System.out.println("Connected to the SQLite database.");
            } else {
                System.out.println("Failed to connect to the SQLite database.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the SQLite database.");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Method that executes connection
     * @param conn
     * @param sql
     *
     */
    private static Statement execute(Connection conn, String sql) {
        try {
            Statement statement = conn.createStatement();
            createDb(statement);
            statement.execute(sql);
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method/query that creats the database in SQlite
     * @param statement
     * @throws SQLException
     */
    private static void createDb(Statement statement) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS books (\n"
                + " id integer PRIMARY KEY,\n"
                + " title text NOT NULL,\n"
                + " author text NOT NULL,\n"
                + " genre text NOT NULL,\n"
                + " checkedOut boolean NOT NULL,\n"
                + " dueDate text\n"
                + ");";

        statement.execute(sql);
    }

    /**
     * Method that gets all books in database
     *
     */
    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try (Connection conn = getConn()) {
            String sql = "SELECT * FROM books;";
            ResultSet result = execute(conn, sql).getResultSet();

            while(result.next()) {
                int id = result.getInt("id");
                String title = result.getString("title");
                String author = result.getString("author");
                String genre = result.getString("genre");
                boolean checkedOut = Boolean.parseBoolean(result.getString("checkedOut"));
                String dueDateStr = result.getString("dueDate");
                java.util.Date dueDate = dueDateStr.equals("null") ? null : new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(dueDateStr);;

                books.add(new Book(id, author, title, genre, checkedOut, dueDate));
            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * Query that searches database for book by ID/barcode
     * @param id
     * @return
     */
    public static Book getBookById(int id) {
        try (Connection conn = getConn()) {
            String sql = String.format("SELECT * FROM books WHERE id = %d;", id);
            ResultSet result = execute(conn, sql).getResultSet();

            if (result.next()) {
                String title = result.getString("title");
                String author = result.getString("author");
                String genre = result.getString("genre");
                boolean checkedOut = Boolean.parseBoolean(result.getString("checkedOut"));
                String dueDateStr = result.getString("dueDate");
             //   Date dueDate = dueDateStr.equals("null") ? null : Date.valueOf(dueDateStr);
                java.util.Date dueDate = dueDateStr.equals("null") ? null : new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(dueDateStr);;

                return new Book(id, author, title, genre, checkedOut, dueDate);
            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Query that searches database to book by title
     * @param title
     * @return
     */
    public static List<Book> getBooksByTitle(String title) {
        List<Book> books = new ArrayList<>();

        try (Connection conn = getConn()) {
            String sql = String.format("SELECT * FROM books WHERE title = \"%s\";", title);
            ResultSet result = execute(conn, sql).getResultSet();

            while(result.next()) {
                int id = result.getInt("id");
                String author = result.getString("author");
                String genre = result.getString("genre");
                boolean checkedOut = Boolean.parseBoolean(result.getString("checkedOut"));
                String dueDateStr = result.getString("dueDate");
               // Date dueDate = dueDateStr.equals("null") ? null : Date.valueOf(dueDateStr);
                java.util.Date dueDate = dueDateStr.equals("null") ? null : new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(dueDateStr);;

                books.add(new Book(id, author, title, genre, checkedOut, dueDate));
            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * Query that allows you to add book to database
     * @param book
     * @return boolean
     */
    public static boolean addBook(Book book) {
        try (Connection conn = getConn()) {
            String sql = String.format("INSERT INTO books (id, title, author, genre, checkedOut, dueDate) VALUES (\n"
                            + " \"%d\",\n"
                            + " \"%s\",\n"
                            + " \"%s\",\n"
                            + " \"%s\",\n"
                            + " \"%s\",\n"
                            + " \"%s\"\n"
                            + ");",

                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.isCheckedOut(),
                    book.getDueDate());
            return execute(conn, sql).getUpdateCount() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Query that updates database
     * Allows for update to check in/ check out
     * @param book
     * @return boolean
     */
    public static boolean updateBook(Book book) {
        try (Connection conn = getConn()) {
            String sql = String.format("UPDATE books SET \n"
                            + " id = \"%d\",\n"
                            + " title = \"%s\",\n"
                            + " author = \"%s\",\n"
                            + " genre = \"%s\",\n"
                            + " checkedOut = \"%s\",\n"
                            + " dueDate = \"%s\"\n"
                            + "WHERE id = \"%d\" \n"
                            + ";",
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    book.isCheckedOut(),
                    book.getDueDate(),
                    book.getId());
            return execute(conn, sql).getUpdateCount() > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Query to remove book by ID/barcode
     * @param id
     * @return boolean
     */
    // TODO: IMPLEMENT THIS
    public static boolean removeBook(int id) {
        try (Connection conn = getConn()) {

            String sql = String.format("DELETE FROM books WHERE id = %d;", id );
        return execute(conn, sql).getUpdateCount() > 0;
    } catch(SQLException e) {
        e.printStackTrace();
        return false;
    }
    }

    /**
     * Main just used for Testing
     * @param args
     */
    public static void main(String[] args) {
        // for testing
//        System.out.println(getBooksByTitle("tuesday"));
   //    Book book = new Book(14, "jhrt", "dujg", "uher", false, null);


    }
}