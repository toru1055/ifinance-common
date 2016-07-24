package jp.thotta.ifinance.common.dao;

import org.apache.commons.lang3.time.DateUtils;

import jp.thotta.ifinance.common.entity.DailyMarketIndex;
import jp.thotta.ifinance.common.entity.MarketIndexMaster;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by thotta on 2016/07/07.
 */
public class DailyMarketIndexManager
        extends BigDataManager<DailyMarketIndex> {
    public DailyMarketIndexManager(Class<DailyMarketIndex> clazz) {
        super(clazz);
    }

    public DailyMarketIndex findToday(MarketIndexMaster index) {
        DailyMarketIndex result = null;
        EntityManager em = CommonEntityManager.getFactory().createEntityManager();
        Date targetYmd = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        try {
            result = em.createQuery(
                    "from DailyMarketIndex " +
                            "where marketIndexMaster = :marketIndexMaster " +
                            "and targetYmd = :targetYmd", DailyMarketIndex.class)
                    .setParameter("marketIndexMaster", index)
                    .setParameter("targetYmd", targetYmd)
                    .getSingleResult();
        } catch (NoResultException e) {
            result = null;
        } finally {
            em.close();
        }
        return result;
    }
}
