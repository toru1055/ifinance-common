package jp.thotta.ifinance.common.entity;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.EntityManager;

public class NewsTest extends BaseEntityTest {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.createNativeQuery("delete from News").executeUpdate();
    em.getTransaction().commit();
    em.close();
  }

  @Override
  public void testBasicUsage() {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    News news1 = new News();
    news1.setUrl("http://www.yahoo.co.jp");
    news1.setTitle("ヤフー");
    em.persist(news1);
    News news2 = new News();
    news2.setUrl("https://www.google.co.jp");
    news2.setTitle("google");
    news2.setDescription("google 日本");
    em.persist(news2);
    em.getTransaction().commit();
    em.close();
    assertEquals(selectAllNews().size(), 2);
  }

  public void testNewsIndustry() {
    Industry industry1 = new Industry("001");
    News news1 = new News();
    news1.setUrl("https://www.google.co.jp");
    news1.setTitle("google");
    news1.setDescription("google 日本");
    news1.addIndustry(industry1);
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
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
    for(News news : result) {
      System.out.println(news.getId() + ": " + news.getTitle());
      List<NewsIndustry> newsIndustryList = news.getNewsIndustryList();
      System.out.println("newsIndustryList.size: " + newsIndustryList.size());
      for(NewsIndustry newsIndustry : newsIndustryList) {
        System.out.println("  >> Industry: " + newsIndustry.getIndustry().getName());
      }
    }
    em.getTransaction().commit();
    em.close();
    return result;
  }
}
