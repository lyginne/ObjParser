package com.company;

import com.company.model.*;
import com.company.model.primitives.Vector;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by iOSDeveloper on 21.06.16.
 */
public class ObjParser {
    private final File _objFile;
    private final String _sceneName;
    private final ArrayList<Vector> _verticies=new ArrayList<>();
    private final ArrayList<Vector> _normals = new ArrayList<>();
    private final ArrayList<Vector> _texels = new ArrayList<>();
    private final ArrayList<Float> _faces = new ArrayList<>();

    private ArrayList<Material> _materials;
    private ArrayList<Group> _groups;
    private ArrayList<Object3d> _objects;

    public ObjParser(File objFile, String sceneName) {
        _sceneName=sceneName;
        _objFile = objFile;
        _materials = new ArrayList<>();
        _groups = new ArrayList<>();
        _objects = new ArrayList<>();
        Object3d object3d = new Object3d(null);
        _objects.add(object3d);
    }
    private void parseMaterialsLib(String fileName){
        String absolutePath = _objFile.getAbsolutePath();
        String PathToMTL = absolutePath.substring(0,absolutePath.lastIndexOf(File.separator));
        File mtlFile = new File(PathToMTL+File.separator+fileName);
        MtlParser mtlParser = new MtlParser(mtlFile);
        ArrayList<Material> materialsFromLib = mtlParser.parse();
        _materials.addAll(materialsFromLib);
    }

    private boolean tryParseVector(ArrayList<Vector> verctorArray, String vectorString, int expectedDimentions){
        String[] parts = vectorString.split("[ /]");
        if(parts.length==expectedDimentions){
            float normalDimentions[] = new float[expectedDimentions];
            for (int i = 0; i < parts.length; i++) {
                normalDimentions[i] = Float.parseFloat(parts[i]);
            }
            Vector normal = new Vector(normalDimentions);
            verctorArray.add(normal);
        }
        else {
            System.out.println("vector should have had "+expectedDimentions+" dimentions");
            return false;
        }
        return true;
    }
    private boolean tryParseNormal(String normalString){
        if(!tryParseVector(_normals, normalString,3)){
            System.out.println("Error parsing normals");
            return false;
        }
        return true;
    }
    private boolean tryParseTexel(String texelString){
        if(!tryParseVector(_texels, texelString,2)){
            System.out.println("Error parsing texels");
            return false;
        }
        return true;
    }
    private boolean tryParsePosition(String positionString){
        if(!tryParseVector(_verticies, positionString,3)){
            System.out.println("Error parsing positions");
            return false;
        }
        return true;
    }

    public Scene parse(){
        Scene scene = new Scene(_sceneName);
        try (BufferedReader br = new BufferedReader(new FileReader(_objFile))) {
            String line;
            Material currentMaterial=null;

            while ((line = br.readLine()) != null) {

                if(line.isEmpty()||line.charAt(0)=='#'||line.trim().isEmpty()) {
                    continue;
                }
                String[] arguments = line.split(" ",2);
                switch (arguments[0]) {
                    case "v": {
                        if(!tryParsePosition(arguments[1])){
                            return null;
                        }
                        break;
                    }
                    case "vt": {
                        if(!tryParseTexel(arguments[1])){
                            return null;
                        }
                        break;
                    }
                    case "vn": {
                        if(!tryParseNormal(arguments[1])){
                            return null;
                        }
                        break;
                    }
                    case "mtllib":
                        parseMaterialsLib(arguments[1]);
                        break;
                    case "f": {
                        if (currentMaterial != null) {
                            currentMaterial.AddFace(arguments[1],_verticies,_texels,_normals);
                        }
                        else{
                            System.out.println("Trying to add face without material");
                            return null;
                        }
                        break;
                    }
                    case "usemtl":
                        if (_materials != null && _materials.size() != 0) {
                            //currentMaterial
                            currentMaterial = _materials.stream()
                                    .filter(material -> material.getMaterialName().equals(arguments[1]))
                                    .findFirst()
                                    .orElse(null);
                            if(currentMaterial == null){
                                System.out.println("material: \""+arguments[1] + "used before it's lib");
                                return null;
                            }
                        }
                        break;
                    case "g":
                            break;
                    case "o":
                            break;
                    default:
                        System.out.println("Unknown .obj line: \"" + line+"\"");
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File \"" + _objFile.getPath() + "\" is not exist");
            return null;
        } catch (IOException e) {
            System.out.println("File \"" + _objFile.getPath() + "\": IOException.");
            return null;
        }
        scene.AddMaterials(_materials);
        return scene;
    }
}
