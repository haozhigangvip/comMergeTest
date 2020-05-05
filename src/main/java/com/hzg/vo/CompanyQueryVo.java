package com.hzg.vo;

import com.hzg.entity.Company;

import java.util.List;

public class CompanyQueryVo {
    private int success;
    private List<Company> listCompany;
    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<Company> getListCompany() {
        return listCompany;
    }

    public void setListCompany(List<Company> listCompany) {
        this.listCompany = listCompany;
    }



}
