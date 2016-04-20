package jp.thotta.ifinance.common.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import jp.thotta.ifinance.common.entity.Scraper;

public class ScraperManager {
  public boolean add(Scraper scraper) {
    boolean isAdded = false;
    EntityManager em = CommonEntityManager.getFactory().createEntityManager();
    List<Scraper> selectedScrapers = em.createQuery(
        "from Scraper where name = :name", Scraper.class)
      .setParameter("name", scraper.getName())
      .getResultList();
    if(selectedScrapers.size() == 0) {
      em.getTransaction().begin();
      em.persist(scraper);
      em.getTransaction().commit();
      isAdded = true;
    }
    em.close();
    return isAdded;
  }

  public Scraper find(Integer id) {
    EntityManager em = CommonEntityManager.getFactory().createEntityManager();
    Scraper scraper = em.find(Scraper.class, id);
    em.close();
    return scraper;
  }

  public Scraper findByName(String name) {
    Scraper scraper = null;
    EntityManager em = CommonEntityManager.getFactory().createEntityManager();
    try {
      scraper = em.createQuery(
          "from Scraper where name = :name", Scraper.class)
        .setParameter("name", name)
        .getSingleResult();
    } catch(NoResultException e) {
      scraper = null;
    } finally {
      em.close();
    }
    return scraper;
  }

  public void remove(Scraper scraper) {
    EntityManager em = CommonEntityManager.getFactory().createEntityManager();
    em.getTransaction().begin();
    em.remove(em.contains(scraper) ? scraper : em.merge(scraper));
    em.getTransaction().commit();
    em.close();
  }

  public List<Scraper> selectAll() {
    EntityManager em = CommonEntityManager.getFactory().createEntityManager();
    List<Scraper> result = em.createQuery(
        "from Scraper", Scraper.class).getResultList();
    em.close();
    return result;
  }

}
