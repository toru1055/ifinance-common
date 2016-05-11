package jp.thotta.ifinance.common.entity;

import javax.persistence.EntityManager;
import java.util.List;

public class IndustryTest extends BaseEntityTest {

    @Override
    public void testBasicUsage() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Industry("i001"));
        em.persist(new Industry("i002"));
        em.getTransaction().commit();
        em.close();
        //
        em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Industry> result = em.createQuery(
                "from Industry", Industry.class).getResultList();
        assertEquals(result.size(), 2);
        em.getTransaction().commit();
        em.close();
    }
}
