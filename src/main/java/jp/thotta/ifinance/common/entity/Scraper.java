package jp.thotta.ifinance.common.entity;

import javax.persistence.*;

@Entity
public class Scraper implements KeyValueData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    public Scraper() {
    }

    public Scraper(String name) {
        this.name = name;
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
}
