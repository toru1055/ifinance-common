package jp.thotta.ifinance.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class NewsIndustry implements Serializable {
    @Id
    @ManyToOne
    private News news;

    @Id
    @ManyToOne
    private Industry industry;

    public NewsIndustry() {
    }

    public NewsIndustry(News news, Industry industry) {
        this.news = news;
        this.industry = industry;
    }

    public News getNews() {
        return news;
    }

    public Industry getIndustry() {
        return industry;
    }
}
