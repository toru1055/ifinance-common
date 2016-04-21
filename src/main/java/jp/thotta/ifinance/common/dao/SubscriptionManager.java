package jp.thotta.ifinance.common.dao;

import java.util.List;
import javax.persistence.EntityManager;

import jp.thotta.ifinance.common.entity.Subscription;

public class SubscriptionManager {
  public boolean add(Subscription subscription) {
    boolean isAdded = false;
    EntityManager em = CommonEntityManager.getFactory().createEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(subscription.getScraper());
      em.persist(subscription);
      em.getTransaction().commit();
      isAdded = true;
    } catch(Exception e) {
      isAdded = false;
    } finally {
      em.close();
    }
    return isAdded;
  }

  public Subscription find(Integer id) {
    EntityManager em = CommonEntityManager.getFactory().createEntityManager();
    Subscription subscription = em.find(Subscription.class, id);
    em.close();
    return subscription;
  }

  public void remove(Integer id) {
    EntityManager em = CommonEntityManager.getFactory().createEntityManager();
    em.getTransaction().begin();
    Subscription subscription = em.find(Subscription.class, id);
    em.remove(subscription);
    em.getTransaction().commit();
    em.close();
  }

  public List<Subscription> selectAll() {
    EntityManager em = CommonEntityManager.getFactory().createEntityManager();
    List<Subscription> result = em.createQuery(
        "from Subscription", Subscription.class).getResultList();
    em.close();
    return result;
  }
}
