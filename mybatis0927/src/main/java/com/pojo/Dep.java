package com.pojo;

import java.util.HashSet;
import java.util.Set;

public class Dep {
private  int depid;
private  String depname;
private Set<Emp> emps = new HashSet<Emp>();

    public int getDepid() {
        return depid;
    }

    public void setDepid(int depid) {
        this.depid = depid;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public Set<Emp> getEmps() {
        return emps;
    }

    public void setEmps(Set<Emp> emps) {
        this.emps = emps;
    }
}
