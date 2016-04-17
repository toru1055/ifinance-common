package jp.thotta.ifinance.common.dao;

import jp.thotta.ifinance.common.entity.Industry;

import java.util.List;
import javax.persistence.EntityManager;

public class IndustryManager {
  public void add(Industry industry) throws Exception {
    EntityManager em = CommonEntityManager.INSTANCE.createEntityManager();
    em.getTransaction().begin();
    em.persist(industry);
    em.getTransaction().commit();
    em.close();
  }

  public void add(List<Industry> industries) throws Exception {
    EntityManager em = CommonEntityManager.INSTANCE.createEntityManager();
    em.getTransaction().begin();
    for(Industry industry : industries) {
      em.persist(industry);
    }
    em.getTransaction().commit();
    em.close();
  }

  public List<Industry> selectAll() {
    EntityManager em = CommonEntityManager.INSTANCE.createEntityManager();
    em.getTransaction().begin();
    List<Industry> result = em.createQuery(
        "from Industry", Industry.class).getResultList();
    em.getTransaction().commit();
    em.close();
    return result;
  }

  public void initTable() {
    EntityManager em = CommonEntityManager.INSTANCE.createEntityManager();
    em.getTransaction().begin();
    em.createNativeQuery("delete from Industry").executeUpdate();
    em.getTransaction().commit();
    em.close();
  }
}
