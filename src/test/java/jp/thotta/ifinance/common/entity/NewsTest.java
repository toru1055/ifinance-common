package jp.thotta.ifinance.common.entity;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class NewsTest extends BaseEntityTest {

    @Override
    public void testBasicUsage() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        News news1 = new News();
        news1.setUrl("http://www.yahoo.co.jp");
        news1.setTitle("ヤフー");
        news1.setAnnouncedDate(new Date());
        news1.setCollectedDate(new Date());
        em.persist(news1);
        News news2 = new News();
        news2.setUrl("https://www.google.co.jp");
        news2.setTitle("google");
        news2.setDescription("google 日本");
        news2.setAnnouncedDate(new Date());
        news2.setCollectedDate(new Date());
        em.persist(news2);
        em.getTransaction().commit();
        em.close();
        assertEquals(selectAllNews().size(), 2);
    }

    public void testNewsIndustry() {
        Industry industry1 = new Industry("001");
        Scraper scraper = new Scraper("aaaa");
        Subscription subscription = new Subscription(
                "name", "http://www.yahoo.co.jp", scraper);
        News news1 = new News();
        news1.setUrl("https://www.google.co.jp");
        news1.setTitle("google");
        news1.setDescription("google 日本");
        news1.setAnnouncedDate(new Date());
        news1.setCollectedDate(new Date());
        news1.setSubscription(subscription);
        news1.addIndustry(industry1);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(scraper);
        em.persist(subscription);
        em.persist(industry1);
        em.persist(news1);
        em.persist(new NewsIndustry(news1, industry1));
        em.getTransaction().commit();
        em.close();
        assertEquals(selectAllNews().size(), 1);
    }

    List<News> selectAllNews() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<News> result = em.createQuery(
                "from News", News.class).getResultList();
        for (News news : result) {
            System.out.println(news.getId() + ": " + news.getTitle());
            List<NewsIndustry> newsIndustries = news.getNewsIndustries();
            System.out.println("newsIndustries.size: " + newsIndustries.size());
            for (NewsIndustry newsIndustry : newsIndustries) {
                System.out.println("  >> Industry: " + newsIndustry.getIndustry().getName());
            }
        }
        em.getTransaction().commit();
        em.close();
        return result;
    }
}
