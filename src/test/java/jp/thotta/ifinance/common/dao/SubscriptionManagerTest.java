package jp.thotta.ifinance.common.dao;

import jp.thotta.ifinance.common.entity.Subscription;
import jp.thotta.ifinance.common.entity.Scraper;

import java.util.List;
import junit.framework.TestCase;

public class SubscriptionManagerTest extends TestCase {
  SubscriptionManager subscriptionManager = new SubscriptionManager();
  ScraperManager scraperManager = new ScraperManager();

  public void testBasicUsage() {
    scraperManager.add(new Scraper("001"));
    scraperManager.add(new Scraper("002"));
    scraperManager.add(new Scraper("003"));
    assertTrue(subscriptionManager.add(new Subscription(
            "name1", "url1", scraperManager.find(1))));
    assertTrue(subscriptionManager.add(new Subscription(
            "name2", "url2", scraperManager.find(2))));
    assertTrue(subscriptionManager.add(new Subscription(
            "name3", "url3", scraperManager.find(3))));
    Subscription subscription02 = subscriptionManager.find(5);
    assertTrue(subscription02 != null);
    assertEquals(subscription02.getName(), "name2");
    assertEquals(subscription02.getId(), (Integer)5);
    assertEquals(subscription02.getScraper().getName(), "002");
    subscriptionManager.remove(5);

    List<Subscription> result = subscriptionManager.selectAll();
    assertEquals(result.size(), 2);
  }

  @Override
  protected void tearDown() {
    CommonEntityManager.closeFactory();
  }

}
