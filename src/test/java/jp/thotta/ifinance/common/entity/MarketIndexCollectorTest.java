package jp.thotta.ifinance.common.entity;

import junit.framework.TestCase;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by thotta on 2016/06/09.
 */
public class MarketIndexCollectorTest extends BaseEntityTest {

    public void testBasicUsage() {
        EntityManager em = emf.createEntityManager();
        MarketIndexCollector marketIndexCollector1 = new MarketIndexCollector("coll1");
        MarketIndexCollector marketIndexCollector2 = new MarketIndexCollector("coll2");
        em.getTransaction().begin();
        em.persist(marketIndexCollector1);
        em.persist(marketIndexCollector2);
        em.getTransaction().commit();
        em.close();
        //
        em = emf.createEntityManager();
        List<MarketIndexCollector> result = em.createQuery(
                "from MarketIndexCollector", MarketIndexCollector.class)
                .getResultList();
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getName(), "coll1");
        em.close();
    }
}