package jp.thotta.ifinance.common.entity;

import javax.persistence.*;

@Entity
public class Industry implements MasterData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    public Industry() {
    }

    public Industry(String name) {
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
        return String.format("[id = %d, name = '%s']", id, name);
    }
}
