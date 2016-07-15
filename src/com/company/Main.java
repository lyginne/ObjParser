package com.company;

import com.company.model.Scene;

import java.io.*;

public class Main {

    private static final String sourceDir = "source";
    private static final String targetDir = "target";
    public static void main(String[] args) {
        if (args.length!=1){
            System.out.println("Incoorect arguments");
            return;
        }
        File sourcedir = new File(sourceDir);
        if(!sourcedir.exists()){
            System.out.println("Source directory \""+sourceDir+"\" not found");
            return;
        }
        if(!sourcedir.isDirectory()){
            System.out.println("Source directory \""+sourceDir+"\" is not actually a directory");
            return;
        }
        File targetdir = new File(targetDir);
        if(!targetdir.exists()){
            System.out.println("Target directory \""+targetDir+"\" is not exist, trying to create one");
            if(targetdir.mkdir())
                System.out.println("Target directory \""+targetDir+"\" cannot be created");
            else{
                System.out.println("Target directory \""+targetDir+"\" created");
            }
            return;
        }
        if(!targetdir.isDirectory()){
            System.out.println("Target directory \""+targetDir+"\" is not actually a directory");
            return;
        }

        File objFile = new File(sourceDir+"/"+args[0]+".obj");
        if(!objFile.exists()) {
            System.out.println("File \"" + args[0]+".obj" + "\" is not exist in a directory" +sourceDir);
            return;
        }
        if(!objFile.isFile()) {
            System.out.println("File \"" + args[0]+".obj" + "\" is not actually a file" +sourceDir);
            return;
        }
        ObjParser objParser = new ObjParser(objFile, args[0]);
        Scene scene = objParser.parse();
        if(scene==null){
            System.out.println("Error: parser returned null scene");
            return;
        }

//        FileWriter fileWriter = null;
        try {
            File outputHeader = new File(targetDir +"/" +args[0]+".h");
            if(!outputHeader.exists())
                outputHeader.createNewFile();
            FileWriter fileHWriter = new FileWriter(outputHeader);
//            objParser.getHString(fileWriter);

            File outputC = new File(targetDir +"/" +args[0]+".c");
            if(!outputC.exists())
                outputC.createNewFile();
            FileWriter fileWriter = new FileWriter(outputC);
            scene.writeCFile(args[0]+".h",fileWriter,fileHWriter);
            fileHWriter.flush();
            fileHWriter.close();
//            fileWriter.write(getCString);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // write your code here
    }
}
