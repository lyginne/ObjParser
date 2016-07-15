package com.company.model;

import com.company.model.primitives.Vector;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by iOSDeveloper on 04.07.16.
 */
public class Scene {
    private final ArrayList<Material> _materials = new ArrayList<>();
    private ArrayList<Object3d> _objects3d;
    private final String _name;
    public Scene(String name){
        _name=name;
    }

    public void AddMaterial(Material material){
        _materials.add(material);
    }
    public void AddMaterials(ArrayList<Material> materials){
        _materials.addAll(materials);
    }
    public void AddObject(Object3d object3d){
        _objects3d.add(object3d);
    }
    public void writeHeader(int p3tncount, int p3ncount, int n3tncount, int n3ncount, int t3tncount, FileWriter fileWriter) throws IOException {

        fileWriter.write("extern float "+_name +"positions3tn["+p3tncount+"][3];\n");

        fileWriter.write("extern float "+_name +"positions3n["+p3ncount + "][3];\n");
        fileWriter.write("extern float "+_name +"normals3tn[" +n3tncount + "][3];\n");
        fileWriter.write("extern float "+_name +"normals3n[" +n3ncount +"][3];\n");
        fileWriter.write("extern float "+_name +"texels3tn["+t3tncount +"][2];\n");

        fileWriter.write(Material.getStructureDefinition()+"\n");
        fileWriter.write("extern const int "+_name +"materialsCount;"
                + "extern const Material "+_name +"materials["+_materials.size() + "];");
    }
    public void writeCFile(String headerName, FileWriter fileCWriter, FileWriter fileHWriter) throws IOException {

        fileCWriter.write("#include <stddef.h>\n" +
                "#include \"" + headerName +"\"\n");
        ArrayList<Vector> verticesTN = new ArrayList<>();
        ArrayList<Vector> verticesN = new ArrayList<>();
        ArrayList<Vector> normalN = new ArrayList<>();
        ArrayList<Vector> normalTN = new ArrayList<>();
        ArrayList<Vector> texelsTN = new ArrayList<>();

        for(Material material: _materials){
            verticesTN.addAll(material.getVerticesTN());
            verticesN.addAll(material.getVerticesN());
            normalN.addAll(material.getNormalsN());
            normalTN.addAll(material.getNormalsTN());
            texelsTN.addAll(material.getTexelsTN());
        }
        writeHeader(verticesTN.size(),verticesN.size(),normalTN.size(),normalN.size(),texelsTN.size(),fileHWriter);

        fileCWriter.write("float "+_name +"positions3tn["+verticesTN.size()+"][3] = {");
        boolean firstString = true;
        for(Vector vertice: verticesTN){
            if(!firstString){
                fileCWriter.write(",");
            }
            fileCWriter.write(vertice.toCString());
            firstString=false;
        }
        fileCWriter.write("};\n");

        fileCWriter.write("float "+_name +"positions3n["+verticesN.size()+"][3] = {");
        firstString = true;
        for(Vector vertice: verticesN){
            if(!firstString){
                fileCWriter.write(",");
            }
            fileCWriter.write(vertice.toCString());
            firstString=false;
        }
        fileCWriter.write("};\n");

        fileCWriter.write("float "+_name +"normals3tn["+normalTN.size()+"][3] = {");
        firstString = true;
        for(Vector normal: normalTN){
            if(!firstString){
                fileCWriter.write(",");
            }
            fileCWriter.write(normal.toCString());
            firstString=false;
        }
        fileCWriter.write("};\n");

        fileCWriter.write("float "+_name +"normals3n["+normalN.size()+"][3] = {");
        firstString = true;
        for(Vector normal: normalN){
            if(!firstString){
                fileCWriter.write(",");
            }
            fileCWriter.write(normal.toCString());
            firstString=false;
        }
        fileCWriter.write("};\n");

        fileCWriter.write("float "+_name +"texels3tn["+texelsTN.size()+"][2] = {");
        firstString = true;
        for(Vector texel: texelsTN){
            if(!firstString){
                fileCWriter.write(",");
            }
            fileCWriter.write(texel.toCString());
            firstString=false;
        }
        fileCWriter.write("};\n");

        fileCWriter.write("const int "+_name +"materialsCount=" + _materials.size()+";\n"
                +"const Material "+_name +"materials["+_materials.size() + "] = ");
        fileCWriter.write("{");
        firstString =true;
        for(Material material: _materials){
            if(!firstString){
                fileCWriter.write(",");
            }
            material.getMaterialString(fileCWriter);
            firstString=false;
        }
        fileCWriter.write("};\n");
    }
}
