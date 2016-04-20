package jp.thotta.ifinance.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

// TODO: 最低クロール間隔をカラム追加
@Entity
public class Subscription {
  @Id
  @GeneratedValue
  private Integer id;

  @Column(nullable = false)
  private String name;

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

  public Subscription(String name, String url, Scraper scraper) {
    this(name, url, scraper, false);
  }

  public Subscription(String name, String url, Scraper scraper, Boolean privateFlag) {
    this.name = name;
    this.url = url;
    this.scraper = scraper;
    this.privateFlag = privateFlag;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
