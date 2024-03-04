/**
 * Reynaldo Mena
 * Software Development 1 - CEN 3024c
 *  3/3/2024
 * Book class full of setters and getters for info
 */
public class Book {
    private boolean checkedOut;
    private int id;
    private String author;
    private String title;


    public Book(int id, String author, String title, boolean checkedOut) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.checkedOut = checkedOut;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString(){

        return "Id: " + id + ", Title: " + title + ", Author: " + author + ",  checkedOut? " + checkedOut;
    }
}
