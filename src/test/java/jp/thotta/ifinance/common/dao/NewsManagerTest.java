package jp.thotta.ifinance.common.dao;

import java.util.List;
import java.util.Date;
import junit.framework.TestCase;

import jp.thotta.ifinance.common.entity.News;
import jp.thotta.ifinance.common.entity.Scraper;
import jp.thotta.ifinance.common.entity.Industry;
import jp.thotta.ifinance.common.entity.Subscription;

public class NewsManagerTest extends TestCase {
  NewsManager newsManager = new NewsManager();
  ScraperManager scraperManager = new ScraperManager();
  IndustryManager industryManager = new IndustryManager();
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
    News news1 = newsManager.find(4L);
    assertEquals(news1.getTitle(), "title1");
    assertEquals(news1.getSubscription().getName(), "name1");
    news1.setTitle("updated title");
    assertTrue(newsManager.update(news1));
    News news2 = newsManager.find(4L);
    assertEquals(news2.getTitle(), "updated title");
  }

  @Override
  protected void tearDown() {
    CommonEntityManager.closeFactory();
  }
}
