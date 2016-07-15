package com.company.model;

import com.company.model.Material;

import java.util.ArrayList;

/**
 * Created by iOSDeveloper on 21.06.16.
 */
public class Group {
    private String name;
    ArrayList<Material> materials = new ArrayList<>();

    Group(String groupName){
        name = groupName;

    }
    private void addMaterial(Material material){
        materials.add(material);
    }

    public String getName() {
        return name;
    }
}
