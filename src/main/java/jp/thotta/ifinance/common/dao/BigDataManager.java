package jp.thotta.ifinance.common.dao;

import javax.persistence.EntityManager;

/**
 * Created by thotta on 2016/07/07.
 */
public class BigDataManager<T> {
    Class<T> clazz;

    public BigDataManager(Class<T> clazz) {
        this.clazz = clazz;
    }

    public boolean add(T t) {
        boolean isAdded = false;
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(t);
            em.flush();
            em.getTransaction().commit();
            isAdded = true;
        } catch (Exception e) {
            isAdded = false;
        } finally {
            em.close();
        }
        return isAdded;
    }

    public void remove(Long id) {
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        em.getTransaction().begin();
        T t = em.find(clazz, id);
        em.remove(t);
        em.getTransaction().commit();
        em.close();
    }

    public boolean update(T t) {
        boolean isUpdated = false;
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(t);
            em.flush();
            em.getTransaction().commit();
            isUpdated = true;
        } catch (Exception e) {
            isUpdated = false;
        } finally {
            em.close();
        }
        return isUpdated;
    }

    public T find(Long id) {
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        T t = em.find(clazz, id);
        em.close();
        return t;
    }
}
