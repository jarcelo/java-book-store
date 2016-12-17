
package business;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author josepharcelo
 */
public class StoreDB
{
    public static List<Store> getStores(){
        EntityManager entityManager =  DBUtil.getEmFactory().createEntityManager();
        String queryString = "SELECT p FROM Stores p";
        Query query = entityManager.createQuery(queryString);
        List<Store> storelist = null;
        try {
            storelist = (List<Store>) query.getResultList();
            if (storelist == null || storelist.isEmpty()) {
                storelist = null;
            }
        } catch(NoResultException e) {
            storelist = null;
        } finally {
            entityManager.close();
        }
        return storelist;
    }
}
