import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Reynaldo Mena
 *
 * Database of all books in LMS
 */
public class Database {

   private Map<Integer,Book> collection;


    public Database() {
        this.collection = new HashMap<>();
    }

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
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public Book remove(int id){
       return collection.remove(id);

    }

    public Collection<Book> viewBooks() {

        return collection.values();

    }

    public Collection<Book> bookSearch(String title) {
        Collection<Book> result = new ArrayList<Book>();
        for (Book book : viewBooks()) {
            if (title.equals(book.getTitle())) {
                result.add(book);
            }
        }
        return result;
    }


    public void print() {
        Collection<Book> books = viewBooks();
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    /**
     * Function checks out book from database
     * @param id Books id
     * @return true if successful
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
