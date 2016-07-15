package com.company.model;

/**
 * Created by iOSDeveloper on 21.06.16.
 */
public class Bump {
    String _filename;
    float _multiplier;

    public void setFilename(String filename) {
        _filename = filename;
    }

    public void setMultiplier(float multiplier) {
        _multiplier = multiplier;
    }

    public String getCString(){
        return "{\"" + _filename + "\"," + _multiplier + "f}";
    }

    public static String getStructureDefinition() {
        return "typedef struct{\n" +
                "\tchar * filename;\n" +
                "\tfloat multiplier;\n" +
                "} Bump;\n";
    }
}
