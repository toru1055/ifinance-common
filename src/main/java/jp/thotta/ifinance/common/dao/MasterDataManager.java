package jp.thotta.ifinance.common.dao;

import jp.thotta.ifinance.common.entity.MarketIndexCollector;
import jp.thotta.ifinance.common.entity.MasterData;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by thotta on 2016/06/14.
 */
public class MasterDataManager<T extends MasterData> {
    Class<T> clazz;

    public MasterDataManager(Class<T> clazz) {
        this.clazz = clazz;
    }

    public boolean add(T masterData) {
        boolean isAdded = false;
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        T data = findByName(masterData.getName());
        if(data == null) {
            em.getTransaction().begin();
            em.persist(masterData);
            em.getTransaction().commit();
            isAdded = true;
        }
        em.close();
        return isAdded;
    }

    public T findByName(String name) {
        T data = null;
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        try {
            String query = "from "+ clazz.getSimpleName() +" where name = :name";
            data = em.createQuery(query, clazz)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            data = null;
        } finally {
            em.close();
        }
        return data;
    }

    public T find(Integer id) {
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        T t = em.find(clazz, id);
        em.close();
        return t;
    }

    public List<T> selectAll() {
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        List<T> result = em.createQuery("from " + clazz.getSimpleName(), clazz)
                .getResultList();
        em.close();
        return result;
    }

    public void remove(Integer id) {
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
            em.getTransaction().commit();
            isUpdated = true;
        } catch (Exception e) {
            isUpdated = false;
        } finally {
            em.close();
        }
        return isUpdated;
    }

    public boolean _import(List<T> list) {
        boolean isAdded = false;
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            for (T t : list) {
                em.merge(t);
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
}
