package com.pojo;

public class EmpAndDep {
    private  int d_depid ;
    private  String depname;
    private  int e_depid;
    private  int empid;
    private  String empname;

    public int getD_depid() {
        return d_depid;
    }

    public void setD_depid(int d_depid) {
        this.d_depid = d_depid;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public int getE_depid() {
        return e_depid;
    }

    public void setE_depid(int e_depid) {
        this.e_depid = e_depid;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }
}
