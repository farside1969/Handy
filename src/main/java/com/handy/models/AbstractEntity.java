package com.handy.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//spring id generator superclass for all to use
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private int uid;

    public int getUid() {
        return this.uid;
    }
}
