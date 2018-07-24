package com.ztjy.xxxxserver.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Administrator
 */
@Entity(name = "boy")
@Data
public class BoyPo implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer age;

    public BoyPo() {

    }

    public BoyPo(Integer id, Integer age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
}
