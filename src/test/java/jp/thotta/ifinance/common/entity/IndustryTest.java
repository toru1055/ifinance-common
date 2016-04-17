package jp.thotta.ifinance.common.entity;

import java.util.List;
import javax.persistence.EntityManager;

public class IndustryTest extends BaseEntityTest {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.createNativeQuery("delete from Industry").executeUpdate();
    em.getTransaction().commit();
    em.close();
  }

  @Override
  public void testBasicUsage() {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(new Industry("i001"));
    em.persist(new Industry("i002"));
    em.getTransaction().commit();
    em.close();
    //
    em = emf.createEntityManager();
    em.getTransaction().begin();
    List<Industry> result = em.createQuery(
        "from Industry", Industry.class).getResultList();
    assertEquals(result.size(), 2);
    em.getTransaction().commit();
    em.close();
  }
}
