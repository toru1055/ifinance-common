package jp.thotta.ifinance.common.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CommonEntityManager {
  public static final EntityManagerFactory INSTANCE =
    Persistence.createEntityManagerFactory("jp.thotta.ifinance.common.jpa");

  private CommonEntityManager() {}
}
