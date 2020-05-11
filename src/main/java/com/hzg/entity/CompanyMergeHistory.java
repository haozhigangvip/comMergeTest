package com.hzg.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity     //声明实体类
@Table(name="companyMerge_history")  
public class CompanyMergeHistory {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name = "AutoID")
	    private Integer autoID;
		 
	
		@Column(name = "tableName", columnDefinition = "VARCHAR(50)")
		private String tableName;
			
		@Column(name = "tableKeyID")
		private Integer tableID;
		
		@Column(name = "creatime")
		private Timestamp  creatime;
		
		@ManyToOne(targetEntity = CompanyMergeHistory_Total.class) //,cascade=CascadeType.PERSIST,fetch=FetchType.EAGER
	    @JoinColumn(name = "totalID", referencedColumnName = "autoID")
	    private CompanyMergeHistory_Total comMergeHistory_Total;
		
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

		

		public Timestamp getCreatime() {
			return creatime;
		}

		public void setCreatime(Timestamp creatime) {
			this.creatime = creatime;
		}
		
		
		
		
		public CompanyMergeHistory_Total getComMergeHistory_Total() {
			return comMergeHistory_Total;
		}

		public void setComMergeHistory_Total(CompanyMergeHistory_Total comMergeHistory_Total) {
			this.comMergeHistory_Total = comMergeHistory_Total;
		}

		@Override
		public String toString() {
			return "CompanyMergeHistory [autoID=" + autoID + ", totalID="  + ", tableName=" + tableName
					+ ", tableID=" + tableID + ", creatime=" + creatime + ", comMergeHistory_Total="
					+ comMergeHistory_Total + "]";
		}





		
		
		
}

	