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



    }







}