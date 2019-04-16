package com.weaver.police.bean;

/*
 * @Author      :wyl
 * @Date        :2019/4/16  16:29
 * @Version 1.0 :
 * @Description :
 **/
public class UserInfo {

    private String id; //用户id

    private String lastname;//姓名

    private String certificatenum;//省份证号码

    private String departmentname;//部门名称

    private String departmentcode;//部门编码

    private String subcompanyname;//分部名称

    private String subcompanycode;//分部编码


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCertificatenum() {
        return certificatenum;
    }

    public void setCertificatenum(String certificatenum) {
        this.certificatenum = certificatenum;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getDepartmentcode() {
        return departmentcode;
    }

    public void setDepartmentcode(String departmentcode) {
        this.departmentcode = departmentcode;
    }

    public String getSubcompanyname() {
        return subcompanyname;
    }

    public void setSubcompanyname(String subcompanyname) {
        this.subcompanyname = subcompanyname;
    }

    public String getSubcompanycode() {
        return subcompanycode;
    }

    public void setSubcompanycode(String subcompanycode) {
        this.subcompanycode = subcompanycode;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", lastname='" + lastname + '\'' +
                ", certificatenum='" + certificatenum + '\'' +
                ", departmentname='" + departmentname + '\'' +
                ", departmentcode='" + departmentcode + '\'' +
                ", subcompanyname='" + subcompanyname + '\'' +
                ", subcompanycode='" + subcompanycode + '\'' +
                '}';
    }
}
