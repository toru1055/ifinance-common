package jp.thotta.ifinance.common.entity;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by thotta on 2016/06/26.
 */
public class DailyMarketIndexTest extends BaseEntityTest {

    @Override
    public void testBasicUsage() {
        Date ymd = null;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ymd = f.parse("2016-06-28");
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        EntityManager em = emf.createEntityManager();

        MarketIndexCollector coll1 = new MarketIndexCollector("coll1");
        MarketIndexCollector coll2 = new MarketIndexCollector("coll2");
        MarketIndexMaster mim1 = new MarketIndexMaster("idx1", coll1);
        MarketIndexMaster mim2 = new MarketIndexMaster("idx2", coll2);
        DailyMarketIndex dmi1 = new DailyMarketIndex(mim1, ymd);
        DailyMarketIndex dmi2 = new DailyMarketIndex(mim2, ymd);
        DailyMarketIndex dmi3 = new DailyMarketIndex(mim1, ymd);
        dmi1.createPrice(1.0);
        dmi2.createPrice(2.0);
        dmi3.createPrice(3.0);
//
        em.getTransaction().begin();
        em.persist(coll1);
        em.persist(coll2);
        em.persist(mim1);
        em.persist(mim2);
        em.persist(dmi1);
        em.persist(dmi2);
        em.getTransaction().commit();
        em.close();
        //
        em = emf.createEntityManager();
        em.getTransaction().begin();
        List<DailyMarketIndex> result = em.createQuery(
                "from DailyMarketIndex", DailyMarketIndex.class).getResultList();
        assertEquals(result.size(), 2);
        DailyMarketIndex foundDmi1 = em.find(DailyMarketIndex.class, 1L);
        DailyMarketIndex foundDmi2 = em.find(DailyMarketIndex.class, 2L);
        foundDmi2.updatePrice(4.0);
        em.merge(foundDmi2);
        em.remove(foundDmi1);
        em.getTransaction().commit();
        em.close();
        //
        em = emf.createEntityManager();
        result = em.createQuery(
                "from DailyMarketIndex", DailyMarketIndex.class).getResultList();
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getId(), (Long)2L);
        assertEquals(result.get(0).getHighestPrice(), 4.0, 0.001);
        assertEquals(result.get(0).getLowestPrice(), 2.0, 0.001);
        em.close();
    }
}