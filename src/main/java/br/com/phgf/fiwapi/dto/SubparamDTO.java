package br.com.phgf.fiwapi.dto;

import java.io.Serializable;

public class SubparamDTO implements Serializable {

    private static final long serialVersionUID = 6132766125994451534L;

    private String name;
    private String value;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
