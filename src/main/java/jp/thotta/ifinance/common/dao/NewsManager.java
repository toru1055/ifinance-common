package jp.thotta.ifinance.common.dao;

import java.util.List;
import javax.persistence.EntityManager;

import jp.thotta.ifinance.common.entity.News;
import jp.thotta.ifinance.common.entity.Industry;
import jp.thotta.ifinance.common.entity.NewsIndustry;

public class NewsManager {
  public boolean add(News news) {
    boolean isAdded = false;
    EntityManager em = CommonEntityManager.getFactory().createEntityManager();
    List<News> newsList = em.createQuery(
        "from News where url = :url", News.class)
      .setParameter("url", news.getUrl())
      .getResultList();
    if(newsList.size() == 0) {
      em.getTransaction().begin();
      for(NewsIndustry newsIndustry : news.getNewsIndustries()) {
        em.merge(newsIndustry.getIndustry());
      }
      em.persist(news);
      for(NewsIndustry newsIndustry : news.getNewsIndustries()) {
        em.persist(newsIndustry);
      }
      em.getTransaction().commit();
      isAdded = true;
    }
    em.close();
    return isAdded;
  }

  public boolean update(News news) {
    boolean isUpdated = false;
    EntityManager em = CommonEntityManager.getFactory().createEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(news);
      em.getTransaction().commit();
      isUpdated = true;
    } catch(Exception e) {
      isUpdated = false;
    } finally {
      em.close();
    }
    return isUpdated;
  }

  public News find(Long id) {
    EntityManager em = CommonEntityManager.getFactory().createEntityManager();
    News news = em.find(News.class, id);
    em.close();
    return news;
  }
}
