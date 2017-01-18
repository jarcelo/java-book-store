
package business;

import java.text.NumberFormat;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author josepharcelo
 */
@Entity(name = "Inventory")
@Table(name = "bookinv")
public class Inventory
{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inventoryID;
    
    @Column(name = "bookID")
    private String bookId;
    
    @Column(name = "storeID")
    private long storeId;
    
    @Column(name = "OnHand")
    private long numberOfBooksOnHand;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookID", insertable = false, updatable = false)
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
