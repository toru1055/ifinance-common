package jp.thotta.ifinance.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@Entity
public class Subscription implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  @Column(nullable = false)
  private Integer interval = 60;

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

  public Integer getInterval() {
    return interval;
  }

  public void setInterval(Integer interval) {
    this.interval = interval;
  }

  public Industry getFixedIndustry() {
    return fixedIndustry;
  }

  public void setFixedIndustry(Industry fixedIndustry) {
    this.fixedIndustry = fixedIndustry;
  }
}
