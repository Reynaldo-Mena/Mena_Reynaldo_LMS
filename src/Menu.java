import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Menu {

    /**
     * Creating database and scanner object
     * asking for file, whiel loops to make sure
     * file entered is correct
     */
    public static void addBooks(Database db){
        Scanner sc = new Scanner(System.in);
        boolean fileWasAdded = false;
        while (!fileWasAdded) {
            System.out.println("Please input file name.");

            String filename = sc.nextLine();
            fileWasAdded = db.addFromFile(filename);
        }
        System.out.println("Printing all books in database: ");
        db.print();

    }

    /**
     * asking for barcode/id
     * take int input
     * removes desired book then prints database again to confirm
     */
    public static void removeBooksByBarcode(Database db){
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter a barcode number to remove: ");
        int id = Integer.parseInt(sc.nextLine());
        db.remove(id);
        System.out.println("This barcode was succussfully deleted. ");
        db.print();
    }

    /**
     * asks for book by title
     * if multiple of same book it shows option to pick which one by barcode
     */
    public static void removeBooksByTitle(Database db){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the title of book you want to remove: ");
        String bookName = sc.nextLine();
        System.out.println("Printing available books: ");
        Collection<Book> sameName = db.bookSearch(bookName);
        for(Book book:sameName){
            System.out.println(book.toString());

        }

        System.out.println("Please enter a barcode number to remove: ");
        int id = Integer.parseInt(sc.nextLine());
        db.remove(id);
        System.out.println("This barcode was succussfully deleted. ");
        db.print();



    }

    /**
     * asks user for book to check out
     * checks database if multiple select which barcode
     * if successfully check out it lets you know
     */
    public static void checkOut(Database db){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter book title to check out: ");
        String bookName = sc.nextLine();
        System.out.println("Printing available books: ");

        Collection<Book> sameName = db.bookSearch(bookName);
        for(Book book:sameName){
            if(!book.isCheckedOut()){
                System.out.println(book.toString());
            }
        }
        System.out.println("Please select book by barcode number: ");
        int id = Integer.parseInt(sc.nextLine());
        if(db.checkOut(id)){
            System.out.println("Successfully checked out. ");
            db.print();
        }

    }

    /**
     * asks user to check in book
     * print database
     * check in book with barcode
     * prints confirmation
     */
     public static void checkIn(Database db){
         Scanner sc = new Scanner(System.in);
         System.out.println("Please enter book title to check in: ");
         String bookName = sc.nextLine();
         System.out.println("Printing available books: ");
         Collection<Book> sameName = db.bookSearch(bookName);
         for(Book book:sameName){
             if(book.isCheckedOut()){
                 System.out.println(book.toString());
             }
         }
         System.out.println("Please select book by barcode number: ");
         int id = Integer.parseInt(sc.nextLine());
         if(db.checkIn(id)){
             System.out.println("Successfully checked in. ");
             db.print();
         }


     }

     public static void displayContents(Database db){

        db.print();

     }
}
