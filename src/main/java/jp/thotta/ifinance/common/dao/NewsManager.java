package jp.thotta.ifinance.common.dao;

import jp.thotta.ifinance.common.entity.News;
import jp.thotta.ifinance.common.entity.NewsIndustry;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.List;

public class NewsManager {
    public boolean add(News news) {
        boolean isAdded = false;
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        List<News> newsList = em.createQuery(
                "from News where url = :url", News.class)
                .setParameter("url", news.getUrl())
                .getResultList();
        if (newsList.size() == 0) {
            em.getTransaction().begin();
            for (NewsIndustry newsIndustry : news.getNewsIndustries()) {
                em.merge(newsIndustry.getIndustry());
            }
            em.persist(news);
            for (NewsIndustry newsIndustry : news.getNewsIndustries()) {
                em.persist(newsIndustry);
            }
            em.getTransaction().commit();
            isAdded = true;
        }
        em.close();
        return isAdded;
    }

  /*
   * ifinance=> select news.title from news, newsindustry
   * where news.announcedDate > '2016-05-01'
   * and news.id = newsindustry.news_id
   * and newsindustry.industry_id = 4
   * order by news.announceddate desc limit 30;
   *
   * ifinance=> select current_timestamp - interval '1 week' as day;
   *
   * http://stackoverflow.com/questions/4378824/adding-in-clause-list-to-a-jpa-query
   */

    /*
     * 業種リストに対応する直近のニュースリストを返却.
     */
    public List<News> recentList(List<Integer> industries) {
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -14);
        List<News> newsList = em.createQuery(
                "select n from News n, NewsIndustry ni " +
                        "where n.id = ni.news.id " +
                        "and ni.industry.id in (:industryList) " +
                        "and n.announcedDate > :announcedDate " +
                        "order by n.announcedDate desc ",
                News.class)
                .setParameter("industryList", industries)
                .setParameter("announcedDate", calendar.getTime())
                .setFirstResult(0)
                .setMaxResults(30)
                .getResultList();
        em.close();
        return newsList;
    }


    public boolean update(News news) {
        boolean isUpdated = false;
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(news);
            em.getTransaction().commit();
            isUpdated = true;
        } catch (Exception e) {
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
