package jp.thotta.ifinance.common.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

public abstract class BaseEntityTest extends TestCase {
  EntityManagerFactory emf;

  @Override
  protected void setUp() throws Exception {
    emf = Persistence.createEntityManagerFactory("jp.thotta.ifinance.common.jpa");
  }

  @Override
  protected void tearDown() throws Exception {
    emf.close();
  }

  public abstract void testBasicUsage();
}
