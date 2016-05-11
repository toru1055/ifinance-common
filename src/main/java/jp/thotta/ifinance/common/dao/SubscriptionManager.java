package jp.thotta.ifinance.common.dao;

import jp.thotta.ifinance.common.entity.Subscription;

import javax.persistence.EntityManager;
import java.util.List;

public class SubscriptionManager {
    public boolean add(Subscription subscription) {
        boolean isAdded = false;
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(subscription.getScraper());
            if (subscription.getFixedIndustry() != null) {
                em.merge(subscription.getFixedIndustry());
            }
            em.persist(subscription);
            em.getTransaction().commit();
            isAdded = true;
        } catch (Exception e) {
            isAdded = false;
        } finally {
            em.close();
        }
        return isAdded;
    }

    public boolean _import(List<Subscription> subscriptions) {
        boolean isAdded = false;
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            for (Subscription subscription : subscriptions) {
                em.merge(subscription);
            }
            em.getTransaction().commit();
            isAdded = true;
        } catch (Exception e) {
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

    public boolean update(Subscription subscription) {
        boolean isUpdated = false;
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(subscription);
            em.getTransaction().commit();
            isUpdated = true;
        } catch (Exception e) {
            isUpdated = false;
        } finally {
            em.close();
        }
        return isUpdated;
    }

    public List<Subscription> selectAll() {
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        List<Subscription> result = em.createQuery(
                "from Subscription", Subscription.class).getResultList();
        em.close();
        return result;
    }
}
