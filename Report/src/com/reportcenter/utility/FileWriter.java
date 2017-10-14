/*
 * FileWriter.java
 *
 * Created on 7 �ѹ��¹ 2548, 15:50 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportcenter.utility;
import java.io.*;
/**
 *
 * @author tong(Padungrat)
 */
public class FileWriter {
    //private String path = "./"+"data.sql";
    private String path = null;
    private String encode = Constant.ENCODE_TH;
    
    private File file;
    FileOutputStream fileOutput;
    OutputStreamWriter output;
    BufferedWriter buffer;
    PrintWriter printWriter;
    
    /** Creates a new instance of FileWriter */
    public FileWriter(){
        //initFile();
    }
    
    public FileWriter(String path_file) {
        if(!path_file.equals("")){
            this.path = path_file;
        }
        initFile();
    }
    
    public FileWriter(File f) {
        this.file = f;
        initFile();
    }
    
    private void initFile() {
        try{
            if(this.file != null){
                fileOutput = new FileOutputStream(file);
            }else{
                fileOutput = new FileOutputStream(path);
            }
            OutputStreamWriter output = new OutputStreamWriter(fileOutput,encode);
            BufferedWriter buffer = new BufferedWriter(output);
            printWriter = new PrintWriter(buffer,true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void writeData(String data){
        printWriter.print(data);
    }
    
    public void closeFile(){
        printWriter.close();
    }
    
    public void setPathFile(String path_file){
        if(!path_file.equals("")){
            this.path = path_file;
        }
    }
    
    public void setPathFile(File f){
        this.file = f;
    }
    
    public String getPathFile(){
        if(file != null){
            return file.getAbsolutePath();
        }
        return path;
    }
    
    public static void main(String args[]){
       FileWriter f = new FileWriter(new File("./xxx.dbf"));
       //f.setPathFile();
       f.writeData("z\tzz\tzzz");
    }
}
