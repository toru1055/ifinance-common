package jp.thotta.ifinance.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class Subscription {
  @Id
  @GeneratedValue
  private Integer id;

  @Column(nullable = false)
  private String url;

  @ManyToOne
  @JoinColumn(columnDefinition="integer", name="scraper_id", nullable = false)
  private Scraper scraper;

  @Column(nullable = false)
  private Boolean privateFlag;

  @ManyToOne
  private Industry fixedIndustry;

  public Subscription() {}

  public Subscription(String url, Scraper scraper) {
    this(url, scraper, false);
  }

  public Subscription(String url, Scraper scraper, Boolean privateFlag) {
    this.url = url;
    this.scraper = scraper;
    this.privateFlag = privateFlag;
  }

  public Integer getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Scraper getScraper() {
    return scraper;
  }

  public void setScraper(Scraper scraper) {
    this.scraper = scraper;
  }

  public Boolean getPrivateFlag() {
    return privateFlag;
  }

  public void setPrivateFlag(Boolean privateFlag) {
    this.privateFlag = privateFlag;
  }

  public Industry getFixedIndustry() {
    return fixedIndustry;
  }

  public void setFixedIndustry(Industry fixedIndustry) {
    this.fixedIndustry = fixedIndustry;
  }
}
