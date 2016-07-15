package com.company.model;

import com.company.model.primitives.Vector;
import com.company.colors.Color;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by iOSDeveloper on 21.06.16.
 */
public class Material {
    private final String materialName;
    private Color _Ka;
    private Color _Kd;
    private Color _Ks;
    private String map_Kd = null;
    private float _Tr;
    private int _illum;
    private float _Ni;
    private float Ns;
    private float _d;
    private Bump bump;

    private ArrayList<Vector> verticesTN = new ArrayList<>();
    private ArrayList<Vector> normalsTN = new ArrayList<>();
    private ArrayList<Vector> texelsTN = new ArrayList<>();

    private ArrayList<Vector> verticesN = new ArrayList<>();
    private ArrayList<Vector> normalsN = new ArrayList<>();

    private boolean isCurrent;
    public Material(String materialName) {
        this.materialName = materialName;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void setKa(Color Ka) {
        this._Ka = Ka;
    }

    public void setKd(Color Kd) {
        this._Kd = Kd;
    }

    public void setKs(Color Ks) {
        this._Ks = Ks;
    }
    public void setNs(float Ns){
        this.Ns = Ns;
    }

    public void setTr(float Tr) {
        this._Tr = Tr;
    }

    public void setIllum(int illum) {
        this._illum = illum;
    }

    public void setNi(float Ni) {
        this._Ni = Ni;
    }

    public void setd(float d) {
        this._d = d;
    }

    public void setMapKd(String map_Kd){
        this.map_Kd = map_Kd;
    }

    public String getMaterialName() {
        return materialName;
    }
    public void setBump(Bump bump){
        this.bump = bump;
    }
    private void add3TNFace(String[] parts, ArrayList<Vector> vertices, ArrayList<Vector> texels, ArrayList<Vector> normals){
        Vector a = vertices.get(Integer.parseInt(parts[0])-1);
        Vector b = vertices.get(Integer.parseInt(parts[3])-1);
        Vector c = vertices.get(Integer.parseInt(parts[6])-1);
        this.verticesTN.add(a);
        this.verticesTN.add(b);
        this.verticesTN.add(c);
        Vector aT = texels.get(Integer.parseInt(parts[1])-1);
        Vector bT = texels.get(Integer.parseInt(parts[4])-1);
        Vector cT = texels.get(Integer.parseInt(parts[7])-1);
        this.texelsTN.add(aT);
        this.texelsTN.add(bT);
        this.texelsTN.add(cT);
        Vector aN = normals.get(Integer.parseInt(parts[2])-1);
        Vector bN = normals.get(Integer.parseInt(parts[5])-1);
        Vector cN = normals.get(Integer.parseInt(parts[8])-1);
        this.normalsTN.add(aN);
        this.normalsTN.add(bN);
        this.normalsTN.add(cN);
    }
    private void add4TNFace(String[] parts, ArrayList<Vector> vertices, ArrayList<Vector> texels, ArrayList<Vector> normals){
        Vector a = vertices.get(Integer.parseInt(parts[0])-1);
        Vector b = vertices.get(Integer.parseInt(parts[3])-1);
        Vector c = vertices.get(Integer.parseInt(parts[6])-1);
        Vector d = vertices.get(Integer.parseInt(parts[9])-1);
        this.verticesTN.add(a);
        this.verticesTN.add(b);
        this.verticesTN.add(c);
        this.verticesTN.add(a);
        this.verticesTN.add(c);
        this.verticesTN.add(d);
        Vector aT = texels.get(Integer.parseInt(parts[1])-1);
        Vector bT = texels.get(Integer.parseInt(parts[4])-1);
        Vector cT = texels.get(Integer.parseInt(parts[7])-1);
        Vector dT = texels.get(Integer.parseInt(parts[10])-1);
        this.texelsTN.add(aT);
        this.texelsTN.add(bT);
        this.texelsTN.add(cT);
        this.texelsTN.add(aT);
        this.texelsTN.add(cT);
        this.texelsTN.add(dT);
        Vector aN = normals.get(Integer.parseInt(parts[2])-1);
        Vector bN = normals.get(Integer.parseInt(parts[5])-1);
        Vector cN = normals.get(Integer.parseInt(parts[8])-1);
        Vector dN = normals.get(Integer.parseInt(parts[11])-1);
        this.normalsTN.add(aN);
        this.normalsTN.add(bN);
        this.normalsTN.add(cN);
        this.normalsTN.add(aN);
        this.normalsTN.add(cN);
        this.normalsTN.add(dN);
    }

    private void add3NFace(String[] parts, ArrayList<Vector> vertices, ArrayList<Vector> normals){
        Vector a = vertices.get(Integer.parseInt(parts[0])-1);
        Vector b = vertices.get(Integer.parseInt(parts[3])-1);
        Vector c = vertices.get(Integer.parseInt(parts[6])-1);
        this.verticesN.add(a);
        this.verticesN.add(b);
        this.verticesN.add(c);
        Vector aN = normals.get(Integer.parseInt(parts[2])-1);
        Vector bN = normals.get(Integer.parseInt(parts[5])-1);
        Vector cN = normals.get(Integer.parseInt(parts[8])-1);
        this.normalsN.add(aN);
        this.normalsN.add(bN);
        this.normalsN.add(cN);
    }
    private void add4NFace(String[] parts, ArrayList<Vector> vertices, ArrayList<Vector> normals){
        Vector a = vertices.get(Integer.parseInt(parts[0])-1);
        Vector b = vertices.get(Integer.parseInt(parts[3])-1);
        Vector c = vertices.get(Integer.parseInt(parts[6])-1);
        Vector d = vertices.get(Integer.parseInt(parts[9])-1);
        this.verticesN.add(a);
        this.verticesN.add(b);
        this.verticesN.add(c);
        this.verticesN.add(a);
        this.verticesN.add(c);
        this.verticesN.add(d);
        Vector aN = normals.get(Integer.parseInt(parts[2])-1);
        Vector bN = normals.get(Integer.parseInt(parts[5])-1);
        Vector cN = normals.get(Integer.parseInt(parts[8])-1);
        Vector dN = normals.get(Integer.parseInt(parts[11])-1);
        this.normalsN.add(aN);
        this.normalsN.add(bN);
        this.normalsN.add(cN);
        this.normalsN.add(aN);
        this.normalsN.add(cN);
        this.normalsN.add(dN);
    }

    public void AddFace(String faceString, ArrayList<Vector> vertices, ArrayList<Vector> texels, ArrayList<Vector> normals){
        String[] parts = faceString.split("[ /]");
        switch (parts.length){
            case 12:
                if(parts[1].equals("")){
                    add4NFace(parts,vertices,normals);
                }
                else {
                    add4TNFace(parts,vertices,texels,normals);
                }
                break;
            case 9:
                if(parts[1].equals("")){
                    add3NFace(parts,vertices,normals);
                }
                else{
                    add3TNFace(parts,vertices,texels,normals);
                }
                break;
            default:
                System.out.println("i don't knwo that kinda face");
        }

    }
    public void getMaterialString(FileWriter fileWriter) throws IOException {
        String ambientColorString = _Ka==null?"{NULL,NULL,NULL}":_Ka.getCColor();
        String diffuseColorString = _Kd==null?"{NULL,NULL,NULL}":_Kd.getCColor();
        String specularColorString = _Ks==null?"{NULL,NULL,NULL}":_Ks.getCColor();
        float Ns = this.Ns;
        String bumpString = bump==null ?"{NULL,0}":bump.getCString();
        //Bump bump = this.bump==null ? null:this.bump;
        fileWriter.write("{\""+materialName+"\""+",\n"
                +ambientColorString + ",\n"
                +diffuseColorString +",\n"
                +((map_Kd==null||map_Kd.length()==0)?"NULL":("\""+map_Kd+"\""))+",\n"
                +specularColorString + ",\n"
                + Ns +"f,\n"
                + _illum + ",\n"
                + _d + "f,\n"
                + bumpString +",\n"
                + String.valueOf(verticesTN.size()) + ",\n"
        + String.valueOf(verticesN.size()) +"\n"
        +"}");
    }
    public ArrayList<Vector> getVerticesTN(){
        return verticesTN;
    }
    public ArrayList<Vector> getNormalsTN(){
        return normalsTN;
    }
    public ArrayList<Vector> getTexelsTN(){
        return texelsTN;
    }
    public ArrayList<Vector> getVerticesN(){
        return verticesN;
    }
    public ArrayList<Vector> getNormalsN(){
        return normalsN;
    }

    public static String getStructureDefinition(){
        return Bump.getStructureDefinition() +"\n" +
                "typedef struct{\n" +
                "\tchar* name;\n" +
                "\tfloat ambientColor[3];\n" +
                "\tfloat diffuseColor[3];\n" +
                "\tchar* diffuseColorMap;\n" +
                "\tfloat specularColor[3];\n" +
                "\tfloat Ns;\n" +
                "\tint illum;\n" +
                "\tfloat d;\n" +
                "\tBump bump;\n" +
                "\tint facesTNCount;\n" +
                "\tint facesNCount;\n" +
                "} Material;\n";
    }
}
