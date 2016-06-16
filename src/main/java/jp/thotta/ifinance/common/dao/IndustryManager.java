package jp.thotta.ifinance.common.dao;

import jp.thotta.ifinance.common.entity.Industry;

import javax.persistence.EntityManager;
import java.util.List;

@Deprecated
public class IndustryManager {
    public boolean add(Industry industry) {
        boolean isAdded = false;
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        List<Industry> selectedIndustries = em.createQuery(
                "from Industry where name = :name", Industry.class)
                .setParameter("name", industry.getName())
                .getResultList();
        if (selectedIndustries.size() == 0) {
            em.getTransaction().begin();
            em.persist(industry);
            em.getTransaction().commit();
            isAdded = true;
        }
        em.close();
        return isAdded;
    }

    public boolean _import(List<Industry> industries) {
        boolean isAdded = false;
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            for (Industry industry : industries) {
                em.merge(industry);
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

    public Industry find(Integer id) {
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        Industry industry = em.find(Industry.class, id);
        em.close();
        return industry;
    }

    public void remove(Integer id) {
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        em.getTransaction().begin();
        Industry industry = em.find(Industry.class, id);
        em.remove(industry);
        em.getTransaction().commit();
        em.close();
    }

    public boolean update(Industry industry) {
        boolean isUpdated = false;
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(industry);
            em.getTransaction().commit();
            isUpdated = true;
        } catch (Exception e) {
            isUpdated = false;
        } finally {
            em.close();
        }
        return isUpdated;
    }

    public List<Industry> selectAll() {
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        List<Industry> result = em.createQuery(
                "from Industry", Industry.class).getResultList();
        em.close();
        return result;
    }

}
