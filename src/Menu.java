package com.rey.lms;

import javax.swing.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Menu {

    /**
     * Creating database and scanner object
     * asking for file, whiel loops to make sure
     * file entered is correct
     */
    public static void addBooks(Database db) {
        boolean fileWasAdded = false;
        while (!fileWasAdded) {
            String filename = JOptionPane.showInputDialog(null, "Please input file name:");
            if (filename == null) {

                return;
            }
            fileWasAdded = db.addFromFile(filename);
            if (!fileWasAdded) {
                JOptionPane.showMessageDialog(null, "File not found or error occurred. Please try again.");
            }
        }
        JOptionPane.showMessageDialog(null, "Printing all books in database:");
        db.print();
    }
    /**
     * asking for barcode/id
     * take int input
     * removes desired book then prints database again to confirm
     */
    public static void removeBooksByBarcode(Database db){
        String userInput = JOptionPane.showInputDialog(null, "Please enter a barcode number to remove:");
        if (userInput != null) {
            try {

                int id = Integer.parseInt(userInput);
                Book bookRemoved = db.remove(id);
                if(bookRemoved != null) {
                    JOptionPane.showMessageDialog(null, "Barcode " + id + " was successfully deleted.");
                    displayContents(db);
                }else {
                    JOptionPane.showMessageDialog(null,"Barcode " + id + " was not found please try again.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid barcode number.");
            }
        }
        }



    /**
     * asks for book by title
     * if multiple of same book it shows option to pick which one by barcode
     */
    public static void removeBooksByTitle(Database db){
        String bookName = JOptionPane.showInputDialog(null, "Please enter the title of the book you want to remove:");
        if (bookName != null) {
            StringBuilder booksInfo = new StringBuilder("Printing available books:\n");
            Collection<Book> sameName = db.bookSearch(bookName);
            for (Book book : sameName) {
                booksInfo.append(book.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, booksInfo.toString());

            String idInput = JOptionPane.showInputDialog(null, "Please enter a barcode number to remove:");
            if (idInput != null) {
                try {
                    int id = Integer.parseInt(idInput);
                    Book titleRemoved = db.remove(id);

                    if (titleRemoved != null) {
                        JOptionPane.showMessageDialog(null, "Barcode " + id + " was successfully deleted.");
                        displayContents(db);
                    } else {
                        JOptionPane.showMessageDialog(null,"Barcode " + id + " was not found please try again.");
                    }
                    }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid barcode number.");
                }
                }
            }
        }





    /**
     * asks user for book to check out
     * checks database if multiple select which barcode
     * if successfully check out it lets you know
     */
    public static void checkOut(Database db){
        String bookName = JOptionPane.showInputDialog(null, "Please enter the title of the book you want to check out:");
        if (bookName != null) {
            StringBuilder avaBooks = new StringBuilder("Printing available books:\n");
            Collection<Book> sameName = db.bookSearch(bookName);
            for (Book book : sameName) {
                if (!book.isCheckedOut()) {
                    avaBooks.append(book.toString()).append("\n");
                }
            }
            JOptionPane.showMessageDialog(null, avaBooks.toString());

            String barcodeInput = JOptionPane.showInputDialog(null, "Please select a book by barcode number:");
            if (barcodeInput != null) {
                try {
                    int id = Integer.parseInt(barcodeInput);
                    if (db.checkOut(id)) {
                        JOptionPane.showMessageDialog(null, "Successfully checked out.");
                        db.print();
                    } else {
                        JOptionPane.showMessageDialog(null, "The selected book is not available for checkout.");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid barcode number.");
                }
            }
        }
        }

    /**
     * asks user to check in book
     * print database
     * check in book with barcode
     * prints confirmation
     */
     public static void checkIn(Database db){
         String bookName = JOptionPane.showInputDialog(null, "Please enter the title of the book you want to check in:");
         if (bookName != null) {
             StringBuilder checkedOutBooks = new StringBuilder("Printing checked out books:\n");
             Collection<Book> sameName = db.bookSearch(bookName);
             for (Book book : sameName) {
                 if (book.isCheckedOut()) {
                     checkedOutBooks.append(book.toString()).append("\n");
                 }
             }
             JOptionPane.showMessageDialog(null, checkedOutBooks.toString());

             String barcodeInput = JOptionPane.showInputDialog(null, "Please select a book by barcode number:");
             if (barcodeInput != null) {
                 try {
                     int id = Integer.parseInt(barcodeInput);
                     if (db.checkIn(id)) {
                         JOptionPane.showMessageDialog(null, "Successfully checked in.");
                         displayContents(db);
                     } else {
                         JOptionPane.showMessageDialog(null, "The selected book is not checked out.");
                     }
                 } catch (NumberFormatException e) {
                     JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid barcode number.");
                 }
             }
         }

     }

     public static void displayContents(Database db){
         JOptionPane.showMessageDialog(null, "Printing all books in database:");
         db.print();

     }
}
