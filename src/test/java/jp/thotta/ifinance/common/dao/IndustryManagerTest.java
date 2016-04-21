package jp.thotta.ifinance.common.dao;

import jp.thotta.ifinance.common.entity.Industry;

import java.util.List;
import junit.framework.TestCase;

public class IndustryManagerTest extends TestCase {
  IndustryManager industryManager = new IndustryManager();

  public void testBasicUsage() {
    assertTrue(industryManager.add(new Industry("001")));
    assertTrue(industryManager.add(new Industry("002")));
    assertTrue(industryManager.add(new Industry("003")));
    assertTrue(!industryManager.add(new Industry("001")));
    Industry industry02 = industryManager.find(2);
    assertEquals(industry02.getName(), "002");
    assertEquals(industry02.getId(), (Integer)2);
    industryManager.remove(2);

    List<Industry> result = industryManager.selectAll();
    assertEquals(result.size(), 2);
  }

  @Override
  protected void tearDown() {
    CommonEntityManager.closeFactory();
  }

}
