package jp.thotta.ifinance.common.entity;

import javax.persistence.*;

/**
 * Created by thotta on 2016/06/09.
 */
@Entity
public class MarketIndexCollector implements MasterData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    public MarketIndexCollector() {
    }

    public MarketIndexCollector(String name) {
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

    @Override
    public String toString() {
        return "MarketIndexCollector{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
