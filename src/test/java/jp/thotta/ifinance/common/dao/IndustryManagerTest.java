package jp.thotta.ifinance.common.dao;

import jp.thotta.ifinance.common.entity.Industry;

import junit.framework.TestCase;

public class IndustryManagerTest extends TestCase {
  public void testBasicUsage() {
    IndustryManager industryManager = new IndustryManager();
    try {
      industryManager.add(new Industry("001"));
      industryManager.add(new Industry("002"));
      industryManager.add(new Industry("003"));
    } catch(Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
    assertEquals(industryManager.selectAll().size(), 3);
    for(Industry industry : industryManager.selectAll()) {
      System.out.println(industry.getId() + ": " + industry.getName());
    }
  }

  @Override
  protected void setUp() {
    new IndustryManager().initTable();
  }

  @Override
  protected void tearDown() {
    new IndustryManager().initTable();
  }
}
