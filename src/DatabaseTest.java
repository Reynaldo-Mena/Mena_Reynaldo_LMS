package com.rey.lms;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {



    //create object to be tested

    Database database;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        // supply test data to database object
        database = new Database();
        database.addFromFile("books.txt");

    }



    @org.junit.jupiter.api.Test
    void remove() {

    String originalBooks = database.viewBooks().toString();
    database.remove(3);
    String afterRemove = database.viewBooks().toString();
      //  System.out.println(originalBooks);
      //  System.out.println(afterRemove);
        assert originalBooks != afterRemove;

    /*Collection<Book> afterRemove2 = database.viewBooks();
    database.remove(2);

    assert (database.bookSearch("The Great Gatsby").size() == 0);

     */
     }

    @org.junit.jupiter.api.Test
    void bookSearch() {
        Collection<Book> searchResults = database.bookSearch("1984");

        assert searchResults.size() >= 1;
    }

    @org.junit.jupiter.api.Test
    void checkOut() {
        assert !database.getBook(1).isCheckedOut();

        database.checkOut(1);

        assert database.getBook(1).isCheckedOut();

        assert database.getBook(1).getDueDate() != null;

    }

    @org.junit.jupiter.api.Test
    void checkIn() {
        database.checkOut(1);

        assert database.getBook(1).isCheckedOut();

        database.checkIn(1);

        assert !database.getBook(1).isCheckedOut();

        assert  database.getBook(1).getDueDate() == null;


    }

    @Test
    void addFromFile() {
        Database emptyDb = new Database();
        emptyDb.addFromFile("books.txt");

        assert emptyDb.viewBooks().size() == 6;



    }
}