package com.hzg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity     //声明实体类
@Table(name="contact_address")
public class ContactAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="AutoID")
	private Integer autoID;
	@Column(name="ContID")
	private String contID;
	public Integer getAutoID() {
		return autoID;
	}
	public void setAutoID(Integer autoID) {
		this.autoID = autoID;
	}
	public String getContID() {
		return contID;
	}
	public void setContID(String contID) {
		this.contID = contID;
	}
}
