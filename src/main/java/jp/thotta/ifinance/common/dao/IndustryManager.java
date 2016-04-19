package jp.thotta.ifinance.common.dao;

import java.util.List;
import javax.persistence.EntityManager;

import jp.thotta.ifinance.common.entity.Industry;

public class IndustryManager {
  public boolean add(Industry industry) {
    boolean isAdded = false;
    EntityManager em = CommonEntityManager.INSTANCE.createEntityManager();
    List<Industry> selectedIndustries = em.createQuery(
        "from Industry where name = :name", Industry.class)
      .setParameter("name", industry.getName())
      .getResultList();
    if(selectedIndustries.size() == 0) {
      em.getTransaction().begin();
      em.persist(industry);
      em.getTransaction().commit();
      isAdded = true;
    }
    em.close();
    return isAdded;
  }

  public Industry find(Integer id) {
    EntityManager em = CommonEntityManager.INSTANCE.createEntityManager();
    Industry industry = em.find(Industry.class, id);
    em.close();
    return industry;
  }

  public void remove(Industry industry) {
    EntityManager em = CommonEntityManager.INSTANCE.createEntityManager();
    em.getTransaction().begin();
    em.remove(em.contains(industry) ? industry : em.merge(industry));
    em.getTransaction().commit();
    em.close();
  }

  public List<Industry> selectAll() {
    EntityManager em = CommonEntityManager.INSTANCE.createEntityManager();
    List<Industry> result = em.createQuery(
        "from Industry", Industry.class).getResultList();
    em.close();
    return result;
  }

}
