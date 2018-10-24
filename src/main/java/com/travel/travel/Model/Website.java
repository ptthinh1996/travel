package com.travel.travel.Model;

import java.io.Serializable;

public class Website implements Serializable {

    private static final long serialVersionUID = -4167618053812053345L;

    private int id;
    private String name;
    private String domainName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    @Override
    public String toString() {
        return "Website{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", domainName='" + domainName + '\'' +
                '}';
    }
}
