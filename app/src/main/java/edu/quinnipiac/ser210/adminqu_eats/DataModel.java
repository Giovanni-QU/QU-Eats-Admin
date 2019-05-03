package edu.quinnipiac.ser210.adminqu_eats;

import java.io.Serializable;

public class DataModel implements Serializable {

    String name;
    String[] items;




    public DataModel(String name, String[] items) {
        this.name = name;
        this.items = items;
        // this.id_ = id_;
        // this.image=image;

    }

    public String getName() {
        return name;
    }



}