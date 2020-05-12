package com.hzg.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity     //声明实体类
@Table(name="companyMerge_history_total")
public class CompanyMergeHistory_Total {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "AutoID" )
	private int autoID;
	@Column(name = "CompanyID_New", columnDefinition = "VARCHAR(50)")
	private String CompanyID_New;
	@Column(name = "CompanyName_New", columnDefinition = "VARCHAR(250)")
	private String CompanyName_New;
	@Column(name = "CompanyID_Old", columnDefinition = "VARCHAR(50)")
	private String CompanyID_Old;
	
	

	
	@Override
	public String toString() {
		return "CompanyMergeHistory_Total [autoID=" + autoID + ", CompanyID_New=" + CompanyID_New + ", CompanyName_New="
				+ CompanyName_New + ", CompanyID_Old=" + CompanyID_Old + ", CompanyName_Old=" + CompanyName_Old+", creatime=" + creatime.toString()
				+ ", comMergeHistory=" + comMergeHistory + "]";
	}
	@Column(name = "CompanyName_Old", columnDefinition = "VARCHAR(250)")
	private String CompanyName_Old;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "creatime")
	private Timestamp  creatime;
	
	
    @OneToMany(mappedBy="comMergeHistory_Total", cascade= CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval=true)
    private Set<CompanyMergeHistory> comMergeHistory = new HashSet<>();

	
	public int getAutoID() {
		return autoID;
	}
	public void setAutoID(int autoID) {
		this.autoID = autoID;
	}
	public String getCompanyID_New() {
		return CompanyID_New;
	}
	public void setCompanyID_New(String companyID_New) {
		CompanyID_New = companyID_New;
	}
	public String getCompanyID_Old() {
		return CompanyID_Old;
	}
	public void setCompanyID_Old(String companyID_Old) {
		CompanyID_Old = companyID_Old;
	}
	public Timestamp getCreatime() {
		return creatime;
	}
	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}
	public Set<CompanyMergeHistory> getComMergeHistory() {
		return comMergeHistory;
	}
	public void setComMergeHistory(Set<CompanyMergeHistory> comMergeHistory) {
		this.comMergeHistory = comMergeHistory;
	}
	public String getCompanyName_New() {
		return CompanyName_New;
	}
	public void setCompanyName_New(String companyName_New) {
		CompanyName_New = companyName_New;
	}
	public String getCompanyName_Old() {
		return CompanyName_Old;
	}
	public void setCompanyName_Old(String companyName_Old) {
		CompanyName_Old = companyName_Old;
	}
	
	
	
	
	
	
}
