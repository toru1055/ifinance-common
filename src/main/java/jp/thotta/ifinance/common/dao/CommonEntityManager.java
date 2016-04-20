package jp.thotta.ifinance.common.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CommonEntityManager {
  private CommonEntityManager() {}

  private static EntityManagerFactory factory = null;

  public static EntityManagerFactory getFactory() {
    if(factory == null || !factory.isOpen()) {
      factory = Persistence.createEntityManagerFactory(
          "jp.thotta.ifinance.common.jpa");
    }
    return factory;
  }

  public static void closeFactory() {
    if(factory != null && factory.isOpen()) {
      factory.close();
      factory = null;
    }
  }
}
