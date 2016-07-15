package com.company;

import com.company.colors.Color;
import com.company.model.Bump;
import com.company.model.Material;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by iOSDeveloper on 21.06.16.
 */
public class MtlParser {
    private File _mtlFile;

    public MtlParser(File mtlFile) {
        _mtlFile = mtlFile;
    }
    public ArrayList<Material> parse(){
        try (BufferedReader br = new BufferedReader(new FileReader(_mtlFile))) {
            String line;
            Material material=null;
            ArrayList<Material> materials = new ArrayList<>();
            while ((line = br.readLine()) != null) {

                if(line.length()==0|| line.charAt(0)=='#') {
                    continue;
                }
                String[] arguments = line.split(" ",2);
                switch (arguments[0]) {
                    case "newmtl":
                        if(material!=null){
                            materials.add(material);
                        }
                        material = new Material(arguments[1]);
                        break;
                    case "Kd": {
                        String[] parts = arguments[1].split(" ");
                        float[] kdComponents = new float[parts.length];
                        for (int i = 0; i < parts.length; i++) {
                            kdComponents[i] = Float.parseFloat(parts[i]);
                        }
                        if (material == null) {
                            System.out.println("color before material definition");
                        } else {
                            material.setKd(new Color(kdComponents));
                        }
                        break;
                    }
                    case "Ka": {
                        String[] parts = arguments[1].split(" ");
                        float[] kaComponents = new float[parts.length];
                        for (int i = 0; i < parts.length; i++) {
                            kaComponents[i] = Float.parseFloat(parts[i]);
                        }
                        if (material == null) {
                            System.out.println("color before material definition");
                        } else {
                            material.setKa(new Color(kaComponents));
                        }
                        break;
                    }
                    case "Ks": {
                        String[] parts = arguments[1].split(" ");
                        float[] ksComponents = new float[parts.length];
                        for (int i = 0; i < parts.length; i++) {
                            ksComponents[i] = Float.parseFloat(parts[i]);
                        }
                        if (material == null) {
                            System.out.println("color before material definition");
                        } else {
                            material.setKs(new Color(ksComponents));
                        }
                        break;
                    }
                    case "Ns":
                        if (material == null) {
                            System.out.println("color before material definition");
                        } else {
                            material.setNs(Float.parseFloat(arguments[1]));
                        }
                        break;
                    case "illum":
                        if (material == null) {
                            System.out.println("color before material definition");
                        } else {
                            material.setIllum(Integer.parseInt(arguments[1]));
                        }
                        break;
                    case "bump":
                        Bump bump = new Bump();
                        String[] bumpArguments = arguments[1].split(" ");
                        for (int i=0;i<bumpArguments.length;i++){
                            if(bumpArguments[i].charAt(0)=='-'){
                                if(bumpArguments[i].equals("-bm")){
                                    i++;
                                    bump.setMultiplier(Float.parseFloat(bumpArguments[i]));
                                }
                            }
                            else{
                                bump.setFilename(bumpArguments[i].trim());
                            }
                        }

                        if (material == null) {
                            System.out.println("color before material definition");
                        } else {
                            material.setBump(bump);
                        }
                        break;
                    case "map_Kd":
                        if (material == null) {
                            System.out.println("map_Kd");
                        } else {
                            material.setMapKd(arguments[1].trim());
                        }

                }
                // process the line.
            }
            materials.add(material);
            return materials;
        } catch (FileNotFoundException e) {
            System.out.println("File \"" + _mtlFile.getPath() + "\" is not exist");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
