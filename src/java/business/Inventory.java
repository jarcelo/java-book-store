
package business;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author josepharcelo
 */
public class Inventory
{
    private long inventoryID;
    private String bookId;
    private long storeId;
    private long numberOfBooksOnHand;
    
    private Book book;

    public Inventory()
    {
        inventoryID = 0;
        bookId = "";
        storeId = 0;
        numberOfBooksOnHand = 0;
        book = new Book();
    }

    public Inventory( long storeId, String bookId, long numberOfBooksOnHand)
    {
        this.storeId = storeId;
        this.bookId = bookId;
        this.numberOfBooksOnHand = numberOfBooksOnHand;
    }
    
    

    public long getInventoryID()
    {
        return inventoryID;
    }

    public void setInventoryID(long inventoryID)
    {
        this.inventoryID = inventoryID;
    }

    public String getBookId()
    {
        return bookId;
    }

    public void setBookId(String bookId)
    {
        this.bookId = bookId;
    }

    public long getStoreId()
    {
        return storeId;
    }

    public void setStoreId(long storeId)
    {
        this.storeId = storeId;
    }

    public long getNumberOfBooksOnHand()
    {
        return numberOfBooksOnHand;
    }

    public void setNumberOfBooksOnHand(long numberOfBooksOnHand)
    {
        this.numberOfBooksOnHand = numberOfBooksOnHand;
    }
    
    public String getBookTitle() {
        return book.getTitle();
    }
    
    public void setBookTitle(String title) {
        this.book.setTitle(title);
    }
    
    public String getPrice(){
        NumberFormat curr = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        curr.setMaximumFractionDigits(2);
        curr.setMinimumFractionDigits(2);
        return curr.format(book.getPrice());
    }
    
    public void setPrice(String price) {
        this.book.setPrice(Double.parseDouble(price));
    }
}
