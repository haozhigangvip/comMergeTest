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
@Entity     //声明实体类
@Table(name="companyMerge_history_total")
public class CompanyMergeHistory_Total {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "AutoID" )
	private int autoID;
	@Column(name = "CompanyID_New", columnDefinition = "VARCHAR(50)")
	private String CompanyID_New;
	@Column(name = "CompanyID_Old", columnDefinition = "VARCHAR(50)")
	private String CompanyID_Old;
	@Column(name = "creatime")
	@Generated(GenerationTime.INSERT)
	private Timestamp  creatime;
    @OneToMany(targetEntity = CompanyMergeHistory.class, cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
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
	
	
	
	
	
}
