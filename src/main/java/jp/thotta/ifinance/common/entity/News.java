package jp.thotta.ifinance.common.entity;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
public class News {
  @Id
  @GeneratedValue
  private Long id;
  
  @Column(nullable = false, unique=true)
  private String url;

  @Column(nullable = false)
  private String title;

  private String description;

  @OneToMany(mappedBy = "news")
  private List<NewsIndustry> newsIndustryList = new ArrayList<NewsIndustry>();

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

  public List<NewsIndustry> getNewsIndustryList() {
    return newsIndustryList;
  }

  public void addIndustry(Industry industry) {
    newsIndustryList.add(new NewsIndustry(this, industry));
  }
}
