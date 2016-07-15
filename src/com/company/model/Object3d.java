package com.company.model;

import com.company.model.Group;

import java.util.ArrayList;

/**
 * Created by iOSDeveloper on 21.06.16.
 */
public class Object3d {
    private String name;

    ArrayList<Group> _groups = new ArrayList<>();

    public Object3d(String name) {
        this.name = name;
        Group group = new Group(null);
    }
}
