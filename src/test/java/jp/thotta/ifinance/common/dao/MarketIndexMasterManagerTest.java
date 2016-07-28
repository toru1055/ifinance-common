package jp.thotta.ifinance.common.dao;

import jp.thotta.ifinance.common.entity.MarketIndexCollector;
import jp.thotta.ifinance.common.entity.MarketIndexMaster;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by thotta on 2016/06/22.
 */
public class MarketIndexMasterManagerTest extends TestCase {
    MarketIndexMasterManager mimm = new MarketIndexMasterManager();
    MasterDataManager<MarketIndexCollector> micm
            = new MasterDataManager<MarketIndexCollector>(MarketIndexCollector.class);

    public void testBasicUsage() {
        MarketIndexCollector coll1 = new MarketIndexCollector("coll1");
        MarketIndexCollector coll2 = new MarketIndexCollector("coll2");
        micm.add(coll1);
        micm.add(coll2);
        assertTrue(mimm.add(new MarketIndexMaster("mim1", coll1)));
        assertTrue(mimm.add(new MarketIndexMaster("mim2", coll2)));
        assertTrue(mimm.add(new MarketIndexMaster("mim3", coll1)));
        assertTrue(!mimm.add(new MarketIndexMaster("mim1", coll1)));
        MarketIndexMaster mim = mimm.find(2);
        assertEquals(mim.getName(), "mim2");
        assertEquals(mim.getMarketIndexCollector().getName(), "coll2");
        mimm.remove(2);
        List<MarketIndexMaster> list = mimm.selectAll();
        assertEquals(list.size(), 2);
    }

    @Override
    public void tearDown() throws Exception {
        CommonEntityManager.closeFactory();
    }

}