package jp.thotta.ifinance.common.dao;

import jp.thotta.ifinance.common.entity.DailyMarketIndex;

import javax.persistence.EntityManager;

/**
 * Created by thotta on 2016/07/07.
 */
public class DailyMarketIndexManager
        extends BigDataManager<DailyMarketIndex> {
    public DailyMarketIndexManager(Class<DailyMarketIndex> clazz) {
        super(clazz);
    }

    @Override
    public boolean add(DailyMarketIndex dailyMarketIndex) {
        boolean isAdded = false;
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(dailyMarketIndex.getMarketIndexMaster().getMarketIndexCollector());
            em.merge(dailyMarketIndex.getMarketIndexMaster());
            em.persist(dailyMarketIndex);
            em.flush();
            em.getTransaction().commit();
            isAdded = true;
        } catch (Exception e) {
            isAdded = false;
        } finally {
            em.close();
        }
        return isAdded;
    }
}
