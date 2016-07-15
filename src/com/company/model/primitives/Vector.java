package com.company.model.primitives;

/**
 * Created by iOSDeveloper on 04.07.16.
 */
public class Vector {
    final float[] _dimentionValues;
    public Vector(float[] dimentionValues){
        _dimentionValues = dimentionValues;
    }

    public String toCString(){
        String returningString="{";
        for (int i=0;i<_dimentionValues.length;i++){
            if(i==0) {
                returningString += _dimentionValues[i] + "f";
                continue;
            }
            returningString +=","+_dimentionValues[i]+"f";
        }
        return returningString+"}";
    }
}
