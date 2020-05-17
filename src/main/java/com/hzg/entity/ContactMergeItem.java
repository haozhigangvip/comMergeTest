package com.hzg.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity     //声明实体类
@Table(name="ContactMergeItem")  
public class ContactMergeItem {
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
		
		@ManyToOne(targetEntity = ContactMerge.class) //,cascade=CascadeType.PERSIST,fetch=FetchType.EAGER
	    @JoinColumn(name = "contMergeID", referencedColumnName = "autoID")
	    private ContactMerge contactMerge;

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

		public ContactMerge getContactMerge() {
			return contactMerge;
		}

		public void setContactMerge(ContactMerge contactMerge) {
			this.contactMerge = contactMerge;
		}
	
		

		





		
		
		
}

	