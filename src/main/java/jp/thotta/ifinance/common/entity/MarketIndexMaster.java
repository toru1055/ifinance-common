package jp.thotta.ifinance.common.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by thotta on 2016/06/16.
 */
@Entity
public class MarketIndexMaster implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(columnDefinition = "integer",
            name = "collector_id", nullable = false)
    private MarketIndexCollector marketIndexCollector;

    @Column(nullable = false)
    private Integer interval = 600;

    private Date lastReadDate = null;

    private Boolean activeFlag = true;

    public MarketIndexMaster() {
    }

    public MarketIndexMaster(String name,
                             MarketIndexCollector marketIndexCollector) {
        this.name = name;
        this.marketIndexCollector = marketIndexCollector;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MarketIndexCollector getMarketIndexCollector() {
        return marketIndexCollector;
    }

    public void setMarketIndexCollector(MarketIndexCollector marketIndexCollector) {
        this.marketIndexCollector = marketIndexCollector;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Date getLastReadDate() {
        return lastReadDate;
    }

    public void setLastReadDate() {
        this.lastReadDate = new Date();
    }

    public boolean isReadable() {
        Date now = new Date();
        int t = Integer.MAX_VALUE;
        if (lastReadDate != null) {
            t = (int) ((now.getTime() - lastReadDate.getTime()) / 1000L);
        }
        return (t > this.interval);
    }

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Boolean isActive() {
        return (activeFlag == null || activeFlag);
    }
}
