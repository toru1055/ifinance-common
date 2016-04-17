package jp.thotta.ifinance.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Industry {
  @Id
  @GeneratedValue
  private Integer id;

  @Column(nullable = false, unique=true)
  private String name;

  public Industry() {}

  public Industry(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
