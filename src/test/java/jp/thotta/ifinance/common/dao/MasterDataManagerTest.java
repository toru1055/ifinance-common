package jp.thotta.ifinance.common.dao;

import jp.thotta.ifinance.common.entity.MarketIndexCollector;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by thotta on 2016/06/15.
 */
public class MasterDataManagerTest extends TestCase {
    MasterDataManager<MarketIndexCollector> m
            = new MasterDataManager<MarketIndexCollector>(MarketIndexCollector.class);

    public void testBasicUsage() throws Exception {
        assertTrue(m.add(new MarketIndexCollector("coll1")));
        assertTrue(m.add(new MarketIndexCollector("coll2")));
        assertTrue(m.add(new MarketIndexCollector("coll3")));
        assertTrue(!m.add(new MarketIndexCollector("coll1")));
        MarketIndexCollector coll2 = m.find(2);
        assertEquals(coll2.getName(), "coll2");
        assertEquals(coll2.getId(), (Integer)2);
        m.remove(2);
        List<MarketIndexCollector> list = m.selectAll();
        assertEquals(list.size(), 2);
    }

    @Override
    public void tearDown() throws Exception {
        CommonEntityManager.closeFactory();
    }
}