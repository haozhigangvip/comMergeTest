package com.hzg.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity     //声明实体类
@Table(name="ContactMerge")
public class ContactMerge {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "AutoID" )
	private int autoID;
	@Column(name = "ContactID_New", columnDefinition = "VARCHAR(50)")
	private String ContactID_New;
	@Column(name = "ContactName_New", columnDefinition = "VARCHAR(250)")
	private String ContactName_New;
	@Column(name = "CompanyID_New", columnDefinition = "VARCHAR(50)")
	private String companyID_New;

	@Column(name = "CompanyName_New", columnDefinition = "VARCHAR(250)")
	private String companyName_New;

	
	@Column(name = "ContactID_Old", columnDefinition = "VARCHAR(50)")
	private String ContactID_Old;
	
	@Column(name = "ContactName_Old", columnDefinition = "VARCHAR(250)")
	private String ContactName_Old;
	@Column(name = "CompanyID_Old", columnDefinition = "VARCHAR(50)")
	private String companyID_Old;

	@Column(name = "CompanyName_Old", columnDefinition = "VARCHAR(250)")
	private String companyName_Old;

	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "creatime")
	private Timestamp  creatime;
	
	@OneToMany(mappedBy="contactMerge", cascade= CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval=true)
    private Set<ContactMergeItem> contactMergeItem = new HashSet<>();

	
	
    public String getCompanyID_New() {
		return companyID_New;
	}

	public void setCompanyID_New(String companyID_New) {
		this.companyID_New = companyID_New;
	}

	public String getCompanyName_New() {
		return companyName_New;
	}

	public void setCompanyName_New(String companyName_New) {
		this.companyName_New = companyName_New;
	}

	public String getCompanyID_Old() {
		return companyID_Old;
	}

	public void setCompanyID_Old(String companyID_Old) {
		this.companyID_Old = companyID_Old;
	}

	public String getCompanyName_Old() {
		return companyName_Old;
	}

	public void setCompanyName_Old(String companyName_Old) {
		this.companyName_Old = companyName_Old;
	}

	public int getAutoID() {
		return autoID;
	}

	public void setAutoID(int autoID) {
		this.autoID = autoID;
	}

	public String getContactID_New() {
		return ContactID_New;
	}

	public void setContactID_New(String contactID_New) {
		ContactID_New = contactID_New;
	}

	public String getContactName_New() {
		return ContactName_New;
	}

	public void setContactName_New(String contactName_New) {
		ContactName_New = contactName_New;
	}

	public String getContactID_Old() {
		return ContactID_Old;
	}

	public void setContactID_Old(String contactID_Old) {
		ContactID_Old = contactID_Old;
	}

	public String getContactName_Old() {
		return ContactName_Old;
	}

	public void setContactName_Old(String contactName_Old) {
		ContactName_Old = contactName_Old;
	}

	public Timestamp getCreatime() {
		return creatime;
	}

	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}

	public Set<ContactMergeItem> getContactMergeItem() {
		return contactMergeItem;
	}

	public void setContactMergeItem(Set<ContactMergeItem> contactMergeItem) {
		this.contactMergeItem = contactMergeItem;
	}

	
	

	
	
	
	
}
