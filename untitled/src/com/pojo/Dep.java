package com.pojo;

public class Dep {

    private int depid;
    private String depname;

    public int getDepid() {
        return depid;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public void setDepid(int depid) {
        this.depid = depid;
    }

    @Override
    public String toString() {
        // return super.toString();//默认用父类的
        return  depname;
    }
}
