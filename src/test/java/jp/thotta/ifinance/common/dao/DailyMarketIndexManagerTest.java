package jp.thotta.ifinance.common.dao;

import jp.thotta.ifinance.common.entity.DailyMarketIndex;
import jp.thotta.ifinance.common.entity.MarketIndexCollector;
import jp.thotta.ifinance.common.entity.MarketIndexMaster;
import junit.framework.TestCase;

import java.util.Date;

/**
 * Created by thotta on 2016/07/14.
 */
public class DailyMarketIndexManagerTest extends TestCase {
    DailyMarketIndexManager dmim =
            new DailyMarketIndexManager(DailyMarketIndex.class);
    MarketIndexMasterManager mimm =
            new MarketIndexMasterManager(MarketIndexMaster.class);
    MasterDataManager<MarketIndexCollector> micm
            = new MasterDataManager<MarketIndexCollector>(MarketIndexCollector.class);

    public void testBasicUsage() {
        MarketIndexCollector coll1 = new MarketIndexCollector("coll1");
        micm.add(coll1);
        MarketIndexMaster mim1 = new MarketIndexMaster("mim1", coll1);
        mimm.add(mim1);
        DailyMarketIndex dmi1 = new DailyMarketIndex(mim1, new Date());
        dmi1.createPrice(0.02);
        dmi1.updatePrice(0.03);
        dmi1.updatePrice(0.01);
        assertTrue(dmim.add(dmi1));
        DailyMarketIndex dailyMarketIndex = dmim.find(1L);
        assertEquals(dailyMarketIndex.getHighestPrice(), 0.03);
        assertEquals(dailyMarketIndex.getClosingPrice(), 0.01);
    }

    @Override
    public void tearDown() throws Exception {
        CommonEntityManager.closeFactory();
    }

}