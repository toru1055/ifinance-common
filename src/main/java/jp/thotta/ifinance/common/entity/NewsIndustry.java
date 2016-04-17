package jp.thotta.ifinance.common.entity;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

@Entity
public class NewsIndustry implements Serializable {
  @Id
  @ManyToOne
  private News news;

  @Id
  @ManyToOne
  private Industry industry;

  public NewsIndustry() {}

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
