package com.company.colors;

import com.company.model.primitives.Vector;

/**
 * Created by iOSDeveloper on 21.06.16.
 */
public class Color extends Vector {
    public Color(float[] components){
        super(components);
    }

    public String getCColor(){
        return super.toCString();
    }


}
