package jp.thotta.ifinance.common.dao;

import jp.thotta.ifinance.common.entity.Industry;
import jp.thotta.ifinance.common.entity.News;
import jp.thotta.ifinance.common.entity.Scraper;
import jp.thotta.ifinance.common.entity.Subscription;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsManagerTest extends TestCase {
    NewsManager newsManager = new NewsManager();
    MasterDataManager<Scraper> scraperManager = new MasterDataManager<Scraper>(Scraper.class);
    MasterDataManager<Industry> industryManager = new MasterDataManager<Industry>(Industry.class);
    SubscriptionManager subscriptionManager = new SubscriptionManager();

    public void testAdd() {
        Scraper scraper = new Scraper("scraper1");
        scraperManager.add(scraper);
        Industry industry = new Industry("industry1");
        industryManager.add(industry);
        Subscription subscription = new Subscription(
                "name1", "url1", scraper);
        subscriptionManager.add(subscription);
        News news = new News();
        news.setUrl("url1");
        news.setTitle("title1");
        news.setAnnouncedDate(new Date());
        news.setCollectedDate(new Date());
        news.setSubscription(subscription);
        news.addIndustry(industry);
        assertTrue(newsManager.add(news));
        News news1 = newsManager.find(1L);
        assertEquals(news1.getNewsIndustries().size(), 1);
        assertEquals(news1.getNewsIndustries().get(0).getIndustry().getName(), "industry1");
        assertEquals(news1.getTitle(), "title1");
        assertEquals(news1.getSubscription().getName(), "name1");

        news1.setTitle("updated title");
        assertTrue(newsManager.update(news1));
        News news2 = newsManager.find(1L);
        assertEquals(news2.getNewsIndustries().get(0).getIndustry().getName(), "industry1");
        assertEquals(news2.getTitle(), "updated title");

        List<Integer> ids = new ArrayList<Integer>();
        ids.add(industry.getId());
        List<News> newsList = newsManager.recentList(ids);
        assertEquals(newsList.size(), 1);
    }

    @Override
    protected void tearDown() {
        CommonEntityManager.closeFactory();
    }
}
