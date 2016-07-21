package jp.thotta.ifinance.common.entity;

import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by thotta on 2016/06/23.
 */
@Entity
@Table(uniqueConstraints =
    @UniqueConstraint(columnNames = {"index_id", "target_ymd"}))
public class DailyMarketIndex implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(columnDefinition = "integer",
            name = "index_id", nullable = false)
    private MarketIndexMaster marketIndexMaster;

    @Column(nullable = false, name = "target_ymd") private Date targetYmd;
    @Column(nullable = false) private Date createDate;
    @Column(nullable = false) private Date updateDate;
    private Double openingPrice;
    private Double closingPrice;
    private Double lowestPrice;
    private Double highestPrice;

    public DailyMarketIndex() {
    }

    public DailyMarketIndex(MarketIndexMaster marketIndexMaster, Date targetYmd) {
        this.marketIndexMaster = marketIndexMaster;
        this.targetYmd = DateUtils.truncate(targetYmd, Calendar.DAY_OF_MONTH);
    }

    public void createPrice(Double price) {
        openingPrice = price;
        closingPrice = price;
        lowestPrice = price;
        highestPrice = price;
        createDate = new Date();
        updateDate = new Date();
    }

    public void updatePrice(Double price) {
        closingPrice = price;
        if(price < lowestPrice) {
            lowestPrice = price;
        }
        if(price > highestPrice) {
            highestPrice = price;
        }
        updateDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public MarketIndexMaster getMarketIndexMaster() {
        return marketIndexMaster;
    }

    public Date getTargetDate() {
        return targetYmd;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public Double getOpeningPrice() {
        return openingPrice;
    }

    public Double getClosingPrice() {
        return closingPrice;
    }

    public Double getLowestPrice() {
        return lowestPrice;
    }

    public Double getHighestPrice() {
        return highestPrice;
    }

    public void setOpeningPrice(Double openingPrice) {
        this.openingPrice = openingPrice;
        this.updateDate = new Date();
    }

    public void setClosingPrice(Double closingPrice) {
        this.closingPrice = closingPrice;
        this.updateDate = new Date();
    }

    public void setLowestPrice(Double lowestPrice) {
        this.lowestPrice = lowestPrice;
        this.updateDate = new Date();
    }

    public void setHighestPrice(Double highestPrice) {
        this.highestPrice = highestPrice;
        this.updateDate = new Date();
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
        this.updateDate = new Date();
    }
}
