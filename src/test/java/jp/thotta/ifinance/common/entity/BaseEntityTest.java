package jp.thotta.ifinance.common.entity;

import junit.framework.TestCase;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
