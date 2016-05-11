package jp.thotta.ifinance.common.entity;

import javax.persistence.EntityManager;
import java.util.List;

public class ScraperTest extends BaseEntityTest {

    @Override
    public void testBasicUsage() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Scraper("i001"));
        em.persist(new Scraper("i002"));
        em.getTransaction().commit();
        em.close();
        //
        em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Scraper> result = em.createQuery(
                "from Scraper", Scraper.class).getResultList();
        assertEquals(result.size(), 2);
        em.getTransaction().commit();
        em.close();
    }
}
