package com.hzg.entity;


import org.hibernate.annotations.Columns;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;


/**
 * 1.实体类和表的映射关系
 * @Eitity  声明实体类
 * @Table   表明实体类和表的映射关旭
 * 2.类中属性和表中字段的映射关系
 * @Id  主键
 * @GeneratedValue  主键的生成策略
 * @Column  实体类中属性和表中字段的映射关系
 * @co
 * @UpdateTimestamp 自动更新日期时间
 *
 */

@Entity     //声明实体类
@Table(name="company_info")     //表明实体类和表的映射关旭,name为数据库中对应的表名
public class Company {

    public void setAutoID(Integer autoID) {
        AutoID = autoID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AutoID")
    private Integer AutoID;

    @Column(name = "comID")
    private String comID;
    public String getComID() {
		return comID;
	}

	public void setComID(String comID) {
		this.comID = comID;
	}

	@Column(name = "companyname")
    private String companyname;
    @Column(name = "comptype")
    private String comptype;
    @Column(name = "comadrID")
    private String comadrID;
    @Column(name = "phone")
    private String phone;
    @Column(name = "abbre")
    private String abbre;
    @Column(name = "note")
    private String note;
    @Column(name = "creatime")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @UpdateTimestamp
    private Timestamp creatime;
//    @Column(name = "comstamp" ,columnDefinition="TIMESTAMP" ,insertable = false, updatable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date comstamp;
    @Column(name = "csalesman")
    private String csalesman;




    public String getComadrID() {
        return comadrID;
    }

    public void setComadrID(String comadrID) {
        this.comadrID = comadrID;
    }
    public Integer getAutoID() {
        return AutoID;
    }



//    public void setComID(String comID) {
//        this.comID = comID;
//    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getComptype() {
        return comptype;
    }

    public void setComptype(String comptype) {
        this.comptype = comptype;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAbbre() {
        return abbre;
    }

    public void setAbbre(String abbre) {
        this.abbre = abbre;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getCreatime() {
        return creatime;
    }

    public void setCreatime(Timestamp creatime) {
        this.creatime = creatime;
    }

//    public Date getComstamp() {
//        return comstamp;
//    }
//
//    public void setComstamp(Date comstamp) {
//        this.comstamp = comstamp;
//    }

    public String getCsalesman() {
        return csalesman;
    }

    public void setCsalesman(String csalesman) {
        this.csalesman = csalesman;
    }

    @Override
    public String toString() {
        return "Company{" +
                "AutoID=" + AutoID +
                ", companyname='" + companyname + '\'' +
                ", comptype='" + comptype + '\'' +
                ", comadrID='" + comadrID + '\'' +
                ", phone='" + phone + '\'' +
                ", abbre='" + abbre + '\'' +
                ", note='" + note + '\'' +
                ", creatime=" + creatime +
                ", csalesman='" + csalesman + '\'' +
                '}';
    }
}
