import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Reynaldo Mena
 *Software Development 1 - CEN 3024c
 * 3/3/2024
 * Database of all books in LMS
 */
public class Database {

//   private Map<Integer,Book> collection;


//    public Database() {
//        this.collection = new HashMap<>();
//    }

    /**
     * addFromFile
     * method to add book to database from file
     * @param filename
     * reads data from a file of books and adds data to collection of books
     * argument - filename
     * @return boolean value
     */
//    public boolean addFromFile(String filename) {
//
//        try {
//            File file = new File(filename);
//            Scanner myReader = new Scanner(file);
//            while (myReader.hasNextLine()) {
//                String bookdata = myReader.nextLine();
//                String[] dataArr = (bookdata.split(","));
//                int id = Integer.parseInt(dataArr[0]);
//                String title = dataArr[1];
//                String author = dataArr[2];
//
//                Book book = new Book(id, author, title, "",false);
//                collection.put(id, book);
//
//            }
//            myReader.close();
//        } catch (Exception e) {
//            System.out.println("File does not exist, Please try again. ");
//
//            return false;
//        }
//        return true;
//    }

    /**
     * remove
     *method called remove, removes books from the collection by barcode/id
     * argument id
     * @return book that was removed
     */
    public static boolean remove(int id){

       return JDBC.removeBook(id);

    }

    /**
     * viewBooks
     * method to view books
     * @return all books in database
     */
    public static Collection<Book> viewBooks() {

        return JDBC.getAllBooks();

    }

    /**
     * booksearch
     * method to search for books
     * argument title
     * @return Collection<Book> of books that match titles
     */
    public static Collection<Book> bookSearch(String title) {
        return JDBC.getBooksByTitle(title);
    }

    /**
     * print
     * method that prints current data
     */
    public static void print() {
        Collection<Book> books = viewBooks();
        StringBuilder output = new StringBuilder();
        output.append("Books in the database:\n");
        for (Book book : books) {
            output.append(book.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, output.toString());
    }



    /**
     * checkOut
     * method checks out book from database
     * arguments id Books id
     * @return true if successfully checked out
     */
    public static boolean checkOut(int id) {
      Book bookToCheckOut = getBook(id);
      if(bookToCheckOut == null){
          System.out.println("This barcode does not exist. ");
          return false;
      }if(bookToCheckOut.isCheckedOut()) {
            System.out.print("This book is already checked out. ");
            return false;
      }
      bookToCheckOut.setCheckedOut(true);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 14);
        bookToCheckOut.setDueDate(cal.getTime());
        JDBC.updateBook(bookToCheckOut);
        return true;

    }

    /**
     * checkIn
     * method to check in books
     * argument id
     * @return true if successfully checked in
     */
    public static boolean checkIn(int id) {
        Book bookToCheckIn = getBook(id);
        if(bookToCheckIn == null){
            System.out.println("This barcode does not exist. ");
            return false;
        }if(!bookToCheckIn.isCheckedOut()) {
            System.out.print("This book is already checked in. ");
            return false;
        }
        bookToCheckIn.setCheckedOut(false);
        bookToCheckIn.setDueDate(null);
        JDBC.updateBook(bookToCheckIn);
        return true;

    }

    /**
     * gets book from collection for testing
     * @param id book Id
     * @return book
     */
    protected static Book getBook(int id){

       return JDBC.getBookById(id);
    }



}
