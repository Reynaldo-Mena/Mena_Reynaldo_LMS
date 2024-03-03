import java.util.Collection;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        Scanner sc = new Scanner(System.in);

        System.out.println("Please input file name.");

        String filename = sc.nextLine();
        db.addFromFile(filename);

        System.out.println("Printing all books in database: ");
        db.print();


        System.out.println("Please enter a barcode number to remove: ");
        int id = Integer.parseInt(sc.nextLine());
        db.remove(id);
        System.out.println("This barcode was succussfully deleted. ");
        db.print();

        System.out.println("Please enter the title of book you want to remove: ");
        String bookName = sc.nextLine();
        System.out.println("Printing available books: ");
        Collection<Book> sameName = db.bookSearch(bookName);
        for(Book book:sameName){
            System.out.println(book.toString());

        }
        System.out.println("Please enter a barcode number to remove: ");
        id = Integer.parseInt(sc.nextLine());
        db.remove(id);
        System.out.println("This barcode was succussfully deleted. ");
        db.print();

        System.out.println("Please enter book title to check out: ");
        bookName = sc.nextLine();
        System.out.println("Printing available books: ");

        sameName = db.bookSearch(bookName);
        for(Book book:sameName){
            if(!book.isCheckedOut()){
                System.out.println(book.toString());
            }
        }
        System.out.println("Please select book by barcode number: ");
        id = Integer.parseInt(sc.nextLine());
        if(db.checkOut(id)){
        System.out.println("Successfully checked out. ");
        db.print();
        }

        System.out.println("Please enter book title to check in: ");
        bookName = sc.nextLine();
        System.out.println("Printing available books: ");
        sameName = db.bookSearch(bookName);
        for(Book book:sameName){
            if(book.isCheckedOut()){
                System.out.println(book.toString());
            }
        }
        System.out.println("Please select book by barcode number: ");
        id = Integer.parseInt(sc.nextLine());
        if(db.checkIn(id)){
            System.out.println("Successfully checked in. ");
            db.print();
        }

    }







}