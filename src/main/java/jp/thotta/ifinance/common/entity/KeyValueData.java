package jp.thotta.ifinance.common.entity;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by thotta on 2016/06/14.
 */
public interface KeyValueData extends Serializable {
    Integer getId();
    String getName();
    void setName(String name);
}
