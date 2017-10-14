/*
 * FileWriter.java
 *
 * Created on 28 กรกฎาคม 2548, 13:39 น.
 */

package com.reportnan.utility;
import java.io.*;
import com.reportnan.utility.Constant;
/**
 *
 * @author Noom
 */
public class FileWriter {
    private String path = "./"+"data.sql";
    private String encode = Constant.ENCODE_TH;
    
    private File file;
    FileOutputStream fileOutput;
    OutputStreamWriter output;
    BufferedWriter buffer;
    PrintWriter printWriter;
    
    /** Creates a new instance of FileWriter */
    public FileWriter(){
        initFile();
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
