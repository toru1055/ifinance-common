package jp.thotta.ifinance.common.dao;

import jp.thotta.ifinance.common.entity.Subscription;
import jp.thotta.ifinance.common.entity.Scraper;
import jp.thotta.ifinance.common.entity.Industry;

import java.util.List;
import junit.framework.TestCase;

public class SubscriptionManagerTest extends TestCase {
  SubscriptionManager subscriptionManager = new SubscriptionManager();
  ScraperManager scraperManager = new ScraperManager();
  IndustryManager industryManager = new IndustryManager();

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
    Subscription subscription02 = subscriptionManager.find(2);
    assertTrue(subscription02 != null);
    assertEquals(subscription02.getName(), "name2");
    assertEquals(subscription02.getId(), (Integer)2);
    assertEquals(subscription02.getScraper().getName(), "002");
    subscriptionManager.remove(2);
    List<Subscription> result = subscriptionManager.selectAll();
    assertEquals(result.size(), 2);
  }

  public void testFixedIndustry() {
    scraperManager.add(new Scraper("scraper001"));
    scraperManager.add(new Scraper("scraper002"));
    scraperManager.add(new Scraper("scraper003"));
    Industry industry1 = new Industry("industry001");
    industryManager.add(industry1);
    Subscription subscription01 =
      new Subscription("name1", "url1", scraperManager.find(1));
    Subscription subscription02 =
      new Subscription("name2", "url2", scraperManager.find(2));
    Subscription subscription03 =
      new Subscription("name3", "url3", scraperManager.find(3));
    subscription01.setFixedIndustry(industry1);
    assertTrue(subscriptionManager.add(subscription01));
    assertTrue(subscriptionManager.add(subscription02));
    assertTrue(subscriptionManager.add(subscription03));
    Subscription subscription = subscriptionManager.find(1);
    assertEquals(subscription.getFixedIndustry().getName(), "industry001");
  }

  public void testUpdate() {
    Scraper scraper01 = new Scraper("scraper001");
    scraperManager.add(scraper01);
    Scraper scraper02 = new Scraper("scraper002");
    scraperManager.add(scraper02);
    Industry industry1 = new Industry("industry001");
    industryManager.add(industry1);
    Subscription subscription01 = new Subscription("name1", "url1", scraper01);
    subscription01.setFixedIndustry(industry1);
    subscriptionManager.add(subscription01);
    Subscription subscription02 = subscriptionManager.find(1);
    subscription02.setName("updated name");
    subscription02.setUrl("updated url");
    subscription02.setScraper(scraper02);
    assertTrue(subscriptionManager.update(subscription02));
    Subscription subscription03 = subscriptionManager.find(1);
    assertEquals(subscription03.getName(), "updated name");
    assertEquals(subscription03.getScraper().getName(), "scraper002");
  }

  @Override
  protected void tearDown() {
    CommonEntityManager.closeFactory();
  }

}
