package com.hzg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity     //声明实体类
@Table(name="contact")  
public class Contact {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="AutoID")
private Integer autoID;
@Column(name="ContID" ,updatable = false)
private String contID;
@Column(name="companyID")
private String comID;
@Column(name="name")
private String name;
@Column(name="email")
private String email;
@Column(name="salesman")
private String salesman;
@Column(name="delTag" ,updatable = false)
private Integer delTag;





public Integer getDelTag() {
	return delTag;
}
public void setDelTag(Integer delTag) {
	this.delTag = delTag;
}
public String getContID() {
	return contID;
}
public void setContID(String contID) {
	this.contID = contID;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getSalesman() {
	return salesman;
}
public void setSalesman(String salesman) {
	this.salesman = salesman;
}

public Integer getAutoID() {
	return autoID;
}
public void setAutoID(Integer autoID) {
	this.autoID = autoID;
}
public String getComID() {
	return comID;
}
public void setComID(String comID) {
	this.comID = comID;
}
@Override
public String toString() {
	return "Contact [autoID=" + autoID + ", comID=" + comID + "]";
}

}
