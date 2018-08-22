package com.svnb2.name;

public class PhoneRecord {
    private String phoneNum;
    private enum phoneType{
        Mobile,
        Work,
        Home,
        Main,
        Work_Fax,
        Home_Fax,
        Pager,
        Other,
        Custom

    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
