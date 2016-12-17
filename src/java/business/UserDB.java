
package business;

import javax.persistence.EntityManager;

/**
 *
 * @author josepharcelo
 */
public class UserDB
{
    public static User getMemberById(long userid){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            User user = em.find(User.class, userid);
            return user;
        } finally {
            em.close();
        }
    }
}
