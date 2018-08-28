package com.svnb2.name.Phone;

public class PhoneRecord {
    private String phoneNum;
    protected enum PhoneType{
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
    protected PhoneType phoneType;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

}
