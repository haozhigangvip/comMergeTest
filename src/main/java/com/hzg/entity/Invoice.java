package com.hzg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity     //声明实体类
@Table(name="invoice_info")  
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="AutoID")
	private Integer autoID;
	@Column(name="invocompID")
	private String comID;
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
		return "invoice_info [autoID=" + autoID + ", comID=" + comID + "]";
	}
	

}
