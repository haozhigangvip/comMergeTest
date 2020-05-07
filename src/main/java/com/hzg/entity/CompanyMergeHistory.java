package com.hzg.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity     //声明实体类
@Table(name="companyMerge_history")  
public class CompanyMergeHistory {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "AutoID" )
	    private Integer autoID;
		@Column(name = "tableName", columnDefinition = "VARCHAR(50)")
		private String tableName;
		
		@Column(name = "tableID")
		private Integer tableID;
		@Column(name = "CompanyID_Old", columnDefinition = "VARCHAR(50)")
		private String companyID_Old;
		@Column(name = "CompanyID_New", columnDefinition = "VARCHAR(50)")
		private String companyID_New;

		@Column(name = "creatime",updatable = false)
		private Timestamp  creatime;

		public Integer getAutoID() {
			return autoID;
		}

		public void setAutoID(Integer autoID) {
			this.autoID = autoID;
		}

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		public Integer getTableID() {
			return tableID;
		}

		public void setTableID(Integer tableID) {
			this.tableID = tableID;
		}

		public String getCompanyID_Old() {
			return companyID_Old;
		}

		public void setCompanyID_Old(String companyID_Old) {
			this.companyID_Old = companyID_Old;
		}

		public String getCompanyID_New() {
			return companyID_New;
		}

		public void setCompanyID_New(String companyID_New) {
			this.companyID_New = companyID_New;
		}

		public Timestamp getCreatime() {
			return creatime;
		}

		public void setCreatime(Timestamp creatime) {
			this.creatime = creatime;
		}

		@Override
		public String toString() {
			return "CompanyMergeHistory [autoID=" + autoID + ", tableName=" + tableName + ", tableID=" + tableID
					+ ", companyID_Old=" + companyID_Old + ", companyID_New=" + companyID_New + ", creatime=" + creatime
					+ "]";
		}
		
		
}

	