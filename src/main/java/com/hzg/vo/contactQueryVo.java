package com.hzg.vo;

import com.hzg.entity.Contact;

import java.util.List;

public class contactQueryVo {
    private int success;
    private List<Contact> listContact;
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public List<Contact> getListContact() {
		return listContact;
	}
	public void setListContact(List<Contact> listContact) {
		this.listContact = listContact;
	}




}
