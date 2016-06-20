package jp.thotta.ifinance.common.entity;

import jp.thotta.ifinance.common.dao.CommonEntityManager;
import junit.framework.TestCase;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by thotta on 2016/06/16.
 */
public class MarketIndexMasterTest extends BaseEntityTest {

    public void testBasicUsage() {
        MarketIndexCollector mic1 = new MarketIndexCollector("coll1");
        MarketIndexCollector mic2 = new MarketIndexCollector("coll2");
        MarketIndexMaster mim1 = new MarketIndexMaster("mi1", mic1);
        MarketIndexMaster mim2 = new MarketIndexMaster("mi2", mic2);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(mic1);
        em.persist(mic2);
        em.persist(mim1);
        em.persist(mim2);
        em.getTransaction().commit();
        em.close();
        //
        em = emf.createEntityManager();
        List<MarketIndexMaster> result = em.createQuery(
              "from MarketIndexMaster", MarketIndexMaster.class
        ).getResultList();
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getMarketIndexCollector().getName(), "coll1");
        em.close();
    }
}