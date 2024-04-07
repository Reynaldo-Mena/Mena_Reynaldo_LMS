import java.util.Collection;
import java.util.Scanner;

/**
 * Reynaldo Mena
 * Software Development 1 - CEN 3024c
 * 3/3/2024
 * Overall program objective is to create a functioning Library management system with check in/out functions and
 * storing that information in database.
 */
public class Main {
    public static void main(String[] args) {


        System.out.println("Welcome to Library Management System! \n");

        int num;
        do {
            System.out.println("0. Exit\n1. Add Books \n2. Remove Books by barcode \n3. Remove Book by Title \n4. Check Out book \n5. Check In Book \n6. Display Contents of Database ");
            Scanner sc = new Scanner(System.in);
            num = sc.nextInt();

            switch (num) {
             //   case 1: Menu.addBooks(db);
             //   break;
                case 2: Menu.removeBooksByBarcode();
                break;
                case 3: Menu.removeBooksByTitle();
                break;
                case 4: Menu.checkOut();
                break;
                case 5: Menu.checkIn();
                break;
                case 6: Menu.displayContents();
                break;


            }
        } while (num != 0) ;


    }
}