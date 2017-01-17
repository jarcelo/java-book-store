
package business;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author josepharcelo
 */
public class InventoryDB
{  
    private static EntityManager getNewEntityManager() {
        return DBUtil.getEmFactory().createEntityManager();
    }
    
    private static EntityManager entityManager;
      
    public static Store getStoreById(long storeID){
        entityManager = getNewEntityManager();
        try {
            Store store = entityManager.find(Store.class, storeID);
            return store;
        } finally {
            entityManager.close();
        }
    }
    
    public static List<Inventory> getInventory(long storeID) {
        entityManager = getNewEntityManager();
        String queryString = "SELECT i.storeId, i.bookId, b.title, b.price, i.numberOfBooksOnHand " +
                                "FROM Inventory i, Book b " +
                                "WHERE i.bookId = b.bookId AND i.storeId = :storeID " +
                                "ORDER BY i.bookId ";
        TypedQuery<Inventory> query = entityManager.createQuery(queryString, Inventory.class);
        query.setParameter("storeID", storeID);
        
        List<Inventory> inventory = null;
        try {
            inventory = query.getResultList();
            if (inventory == null || inventory.isEmpty()) {
                inventory = null;
            }
        } catch(NoResultException e) {
            inventory = null;
        } finally {
            entityManager.close();
        }
        return inventory;
    }
    
    public static Book getBookById(String id) {
        entityManager =  getNewEntityManager();
        try {
            Book book = entityManager.find(Book.class, id);
            return book;
        } finally {
            entityManager.close();
        }
    }
    
    public static long getBookOnHandInventory(String bID, long sID) {
        entityManager = getNewEntityManager();
        String queryString = "SELECT i.numberOfBooksOnHand from Inventory i " +
                        "WHERE i.bookId = ?1 AND i.storeId = ?2 "; 
        TypedQuery<Long> query = entityManager.createQuery(queryString, Long.class);
        query.setParameter(1, bID).setParameter(2, sID);
        
        long bookCount = 0;
        try {
            bookCount = query.getSingleResult();
        } catch(NoResultException e) {
            bookCount = 0;
        } finally {
            entityManager.close();
        }
        return bookCount;
    }
    
    public static int updateBookOnHandInventory(long quantity, String bID, long sID){
        entityManager = getNewEntityManager();
        EntityTransaction trans = entityManager.getTransaction();
        String queryString = "UPDATE Inventory i SET " +
                                 "i.numberOfBooksOnHand = :quantity " + 
                                 "WHERE i.bookId = :bID AND i.storeId = :sID";
        TypedQuery<Integer> query = entityManager.createQuery(queryString, Integer.class);
        query.setParameter("quantity", quantity);
        query.setParameter("bID", bID);
        query.setParameter("sID", sID);
        
        int updatedRows;
        try {
            trans.begin();
            updatedRows = query.executeUpdate();
            trans.commit();
        }
        catch (Exception e) {
            trans.rollback();
            updatedRows = 0;
        }
        finally {
            entityManager.close();
        }
        return updatedRows;
    }
}
