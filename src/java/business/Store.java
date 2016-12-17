
package business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author josepharcelo
 */
@Entity(name = "Stores")
@Table(name="stores")
public class Store
{
    @Id
    @Column(name = "storeID")
    private long storeID;
    
    @Column(name = "storeName")
    private String storeName;
    
    @Column(name = "storeAddr")
    private String storeAddress;
    
    @Column(name = "storeEmp")
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

    @Override
    public String toString()
    {
        return String.format("Branch Number: " + this.storeID + "<br>" +
               "Branch Name: " + this.storeName + "<br>" +
               "Branch Location: " + this.storeAddress);
    }
    
    
}
