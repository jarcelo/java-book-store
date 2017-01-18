
package business;

/**
 *
 * @author josepharcelo
 */
public class Book
{
    private String bookId;
    private String title;
    private String author;
    private String publisherCode;
    private String bookType;
    private Double price;

    public Book()
    {
        this.bookId = "";
        this.title = "";
        this.author = "";
        this.publisherCode = "";
        this.bookType = "";
        this.price = 0.0;
    }

    public Book(String title, Double price)
    {
        this.title = title;
        this.price = price;
    }

    
    public String getBookId()
    {
        return bookId;
    }

    public void setBookId(String bookId)
    {
        this.bookId = bookId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getPublisherCode()
    {
        return publisherCode;
    }

    public void setPublisherCode(String publisherCode)
    {
        this.publisherCode = publisherCode;
    }

    public String getBookType()
    {
        return bookType;
    }

    public void setBookType(String bookType)
    {
        this.bookType = bookType;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }
    
    @Override
    public String toString(){
        return String.format("Book Code: " + this.bookId + "<br>" +
               "Book Title: " + this.title + "<br>" +
               "Author: " + this.author);
    }
}
