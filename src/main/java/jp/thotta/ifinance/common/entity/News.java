package jp.thotta.ifinance.common.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

@Entity
public class News {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(nullable = false, unique=true, columnDefinition="text")
  private String url;

  @Column(nullable = false, columnDefinition="text")
  private String title;

  @Column(columnDefinition="text")
  private String description;

  @Column(nullable = false)
  private Date announcedDate;

  @Column(nullable = false)
  private Date collectedDate;

  @ManyToOne
  private Subscription subscription;

  @OneToMany(mappedBy = "news", fetch = FetchType.EAGER)
  private List<NewsIndustry> newsIndustries = new ArrayList<NewsIndustry>();

  public News() {}

  public Long getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getAnnouncedDate() {
    return announcedDate;
  }

  public void setAnnouncedDate(Date announcedDate) {
    this.announcedDate = announcedDate;
  }

  public Date getCollectedDate() {
    return collectedDate;
  }

  public void setCollectedDate(Date collectedDate) {
    this.collectedDate = collectedDate;
  }

  public Subscription getSubscription() {
    return subscription;
  }

  public void setSubscription(Subscription subscription) {
    this.subscription = subscription;
  }

  public List<NewsIndustry> getNewsIndustries() {
    return newsIndustries;
  }

  public void addIndustry(Industry industry) {
    newsIndustries.add(new NewsIndustry(this, industry));
  }
}
