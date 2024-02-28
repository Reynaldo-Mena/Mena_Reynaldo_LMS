import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

                Book book = new Book(id, author, title);
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

    public void print() {
        Collection<Book> books = viewBooks();
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }
}
