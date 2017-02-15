package com.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CommonBean {
    @Id
    @Column(name = "id", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Transient
    protected static final String DEFAULT_ID_COLUMN_NAME = "id";
    
    public int getId() {
        return id;
    }
//    public void setId(int id) {
//        this.id = id;
//    }
    
    public abstract String getClassSimpleName();
    public String getIdColumnName(){
	return DEFAULT_ID_COLUMN_NAME;
    }
}
