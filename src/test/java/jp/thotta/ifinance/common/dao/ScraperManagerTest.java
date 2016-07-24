package jp.thotta.ifinance.common.dao;

import jp.thotta.ifinance.common.entity.Scraper;
import junit.framework.TestCase;

import java.util.List;

public class ScraperManagerTest extends TestCase {
    MasterDataManager<Scraper> scraperManager = new MasterDataManager<Scraper>(Scraper.class);

    public void testBasicUsage() {
        assertTrue(scraperManager.add(new Scraper("001")));
        assertTrue(scraperManager.add(new Scraper("002")));
        assertTrue(scraperManager.add(new Scraper("003")));
        assertTrue(!scraperManager.add(new Scraper("001")));

        Scraper scraper02 = scraperManager.find(2);
        assertEquals(scraper02.getName(), "002");
        assertEquals(scraper02.getId(), (Integer) 2);

        scraperManager.remove(2);

        List<Scraper> result = scraperManager.selectAll();
        assertEquals(result.size(), 2);
    }

    @Override
    protected void tearDown() {
        CommonEntityManager.closeFactory();
    }
}
