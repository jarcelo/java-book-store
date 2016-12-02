
package business;

/**
 *
 * @author josepharcelo
 */
public class Store
{
    private long storeID;
    private String storeName;
    private String storeAddress;
    private long numberOfEmployees;
    
    public Store(){
        storeID = 0;
        storeName = "";
        storeAddress = "";
        numberOfEmployees = 0;
    }
    
    public Store(long storeID, String storeName) {
        this.storeID = storeID;
        this.storeName = storeName;
    }

    public long getStoreID()
    {
        return storeID;
    }

    public void setStoreID(long storeID)
    {
        this.storeID = storeID;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public String getStoreAddress()
    {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress)
    {
        this.storeAddress = storeAddress;
    }

    public long getNumberOfEmployees()
    {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(long numberOfEmployees)
    {
        this.numberOfEmployees = numberOfEmployees;
    }
}
