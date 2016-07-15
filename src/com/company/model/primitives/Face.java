package com.company.model.primitives;

import com.company.model.Material;

/**
 * Created by iOSDeveloper on 21.06.16.
 */
public class Face {
    Material material;
    int[] verticesIndices;
    int[] normalIndices;
    int[] texelIndices;

    public Face(String faceString) {
//        faceString.

    }
    public int getVerticesCount(){
        return verticesIndices.length;
    }


}
