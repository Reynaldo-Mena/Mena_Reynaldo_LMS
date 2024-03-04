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

   private Map<Integer,Book> collection;


    public Database() {
        this.collection = new HashMap<>();
    }

    /**
     * addFromFile
     * method to add book to database from file
     * @param filename
     * reads data from a file of books and adds data to collection of books
     * argument - filename
     * @return boolean value
     */
    public boolean addFromFile(String filename) {

        try {
            File file = new File(filename);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String bookdata = myReader.nextLine();
                String[] dataArr = (bookdata.split(","));
                int id = Integer.parseInt(dataArr[0]);
                String title = dataArr[1];
                String author = dataArr[2];

                Book book = new Book(id, author, title,false);
                collection.put(id, book);

            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("File does not exist, Please try again. ");

            return false;
        }
        return true;
    }

    /**
     * remove
     *method called remove, removes books from the collection by barcode/id
     * argument id
     * @return book that was removed
     */
    public Book remove(int id){
       return collection.remove(id);

    }

    /**
     * viewBooks
     * method to view books
     * @return all books in database
     */
    public Collection<Book> viewBooks() {

        return collection.values();

    }

    /**
     * booksearch
     * method to search for books
     * argument title
     * @return Collection<Book> of books that match titles
     */
    public Collection<Book> bookSearch(String title) {
        Collection<Book> result = new ArrayList<Book>();
        for (Book book : viewBooks()) {
            if (title.equals(book.getTitle())) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * print
     * method that prints current data
     */
    public void print() {
        Collection<Book> books = viewBooks();
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    /**
     * checkOut
     * method checks out book from database
     * arguments id Books id
     * @return true if successfully checked out
     */
    public boolean checkOut(int id) {
      Book bookToCheckOut = collection.get(id);
      if(bookToCheckOut == null){
          System.out.println("This barcode does not exist. ");
          return false;
      }if(bookToCheckOut.isCheckedOut()) {
            System.out.print("This book is already checked out. ");
            return false;
      }
      bookToCheckOut.setCheckedOut(true);
        return true;

    }

    /**
     * checkIn
     * method to check in books
     * argument id
     * @return true if successfully checked in
     */
    public boolean checkIn(int id) {
        Book bookToCheckIn = collection.get(id);
        if(bookToCheckIn == null){
            System.out.println("This barcode does not exist. ");
            return false;
        }if(!bookToCheckIn.isCheckedOut()) {
            System.out.print("This book is already checked in. ");
            return false;
        }
        bookToCheckIn.setCheckedOut(false);
        return true;

    }



}
