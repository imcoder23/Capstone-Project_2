package com.example.hunter.mybloodcall;

/**
 * Created by HuNter on 4/6/2018.
 */

class firebaseDBhelper {
    private String id;
    private String name;
    private String sex;
    private String city;
    private String bloodGroup;
    private String contactNumber;

    public firebaseDBhelper(){

    }
    public firebaseDBhelper(String id, String name, String sex, String city, String bloodGroup, String contactNumber) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.city = city;
        this.bloodGroup = bloodGroup;
        this.contactNumber = contactNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
