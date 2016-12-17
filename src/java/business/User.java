
package business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author josepharcelo
 */
@Entity
@Table(name="users")
public class User
{
    @Id
    @Column(name= "userID")
    private long userId;
    
    @Column(name = "userName")
    private String userName;
    
    @Column(name = "userPassword")
    private long userPassword;
    
    @Transient
    private long passwordAttempt;
    
    @Column(name = "storeID")
    private long storeId;
    
    @Column(name = "adminLevel")
    private String adminLevel;

    public User()
    {
        userId = 0;
        userName = "";
        adminLevel = "";
        userPassword = 0;
        passwordAttempt = -1;
        storeId = 0;
    }
    
    public double getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public double getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(long userPassword)
    {
        this.userPassword = userPassword;
    }

    public double getStoreID()
    {
        return storeId;
    }

    public void setStoreID(long storeID)
    {
        this.storeId = storeID;
    }

    public String getAdminLevel()
    {
        return adminLevel;
    }

    public void setAdminLevel(String adminLevel)
    {
        this.adminLevel = adminLevel;
    }

    public double getPasswordAttempt()
    {
        return passwordAttempt;
    }

    public void setPasswordAttempt(long passwordAttempt)
    {
        this.passwordAttempt = passwordAttempt;
    }
    
    public boolean isAuthenticated() {
        if (userPassword  > 0) {
            if (userPassword == this.passwordAttempt) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "User " + this.userId + "-" + this.userName + ", " + this.adminLevel + " Level";
    }
}
