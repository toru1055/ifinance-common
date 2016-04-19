package jp.thotta.ifinance.common.entity;

import java.util.List;
import javax.persistence.EntityManager;

public class SubscriptionTest extends BaseEntityTest {

  @Override
  public void testBasicUsage() {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    Scraper scraper = new Scraper("AAAAA");
    Industry industry = new Industry("A001");
    Subscription subscription = new Subscription(
        "name", "http://www.yahoo.co.jp", scraper);
    subscription.setFixedIndustry(industry);
    em.persist(industry);
    em.persist(scraper);
    em.persist(subscription);
    em.getTransaction().commit();
    em.close();
    assertEquals(selectAll().size(), 1);
  }

  List<Subscription> selectAll() {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    List<Subscription> result = em.createQuery(
        "from Subscription", Subscription.class)
      .getResultList();
    for(Subscription subscription : result) {
      System.out.println(subscription.getId() + ": " + subscription.getUrl());
    }
    em.getTransaction().commit();
    em.close();
    return result;
  }
}
