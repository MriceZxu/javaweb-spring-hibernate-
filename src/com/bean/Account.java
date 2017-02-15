package com.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_account")
public class Account extends CommonBean {
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    
    public Account() {
	
    }
    
    public Account(Account account){
	this.id = account.id;
	this.name = account.name;
	this.password = account.password;
    }

    @Override
    public String toString() {
	return "Account [name=" + name + ", password=" + password + ", id=" + id + "]";
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public String getClassSimpleName() {
	return Account.class.getSimpleName();
    }

}
