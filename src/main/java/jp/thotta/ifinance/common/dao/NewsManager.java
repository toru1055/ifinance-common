package jp.thotta.ifinance.common.dao;

import java.util.List;
import javax.persistence.EntityManager;

import jp.thotta.ifinance.common.entity.News;

public class NewsManager {
  public void add(News news) throws Exception {
    EntityManager em = CommonEntityManager.INSTANCE.createEntityManager();
    em.getTransaction().begin();
    em.persist(news);
    em.getTransaction().commit();
    em.close();
  }
}
