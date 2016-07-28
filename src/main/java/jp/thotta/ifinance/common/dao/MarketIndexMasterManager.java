package jp.thotta.ifinance.common.dao;

import jp.thotta.ifinance.common.entity.MarketIndexMaster;

import javax.persistence.EntityManager;

/**
 * Created by thotta on 2016/06/20.
 */
public class MarketIndexMasterManager
        extends MasterDataManager<MarketIndexMaster> {
    public MarketIndexMasterManager() {
        super(MarketIndexMaster.class);
    }

    @Override
    public boolean add(MarketIndexMaster marketIndexMaster) {
        boolean isAdded = false;
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(marketIndexMaster.getMarketIndexCollector());
            em.persist(marketIndexMaster);
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
