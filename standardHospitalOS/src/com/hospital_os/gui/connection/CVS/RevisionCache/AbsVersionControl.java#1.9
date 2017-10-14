/*
 * InfVersionControl.java
 *
 * Created on 21 ตุลาคม 2549, 8:39 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.gui.connection;

import com.hospital_os.object.Version;
import com.hospital_os.utility.Constant;
import java.util.*;
import com.hospital_os.usecase.connection.*;
/**
 *
 * @author Administrator
 */
public abstract class AbsVersionControl {
    
    protected int current_index = -1;
    protected String current_version = null;
    
    protected abstract int getVersionCount();
    protected abstract String getVersion(int index);
    public abstract String getCurrentVersion();
    private int getCurrentIndex(){
        if(current_index!=-1){
            return current_index;
        }
        String str = getCurrentVersion();
        for(int i=0;i<getVersionCount();i++){
            if(str.equals(getVersion(i))){
                current_index = i;
                break;
            }
        }
        return current_index;
    }
    protected abstract Vector getFileUpdate(int index);
    
    public Vector getFileUpdateV(){
        int index = getCurrentIndex();
        Constant.println("index:" + index);
        return getFileUpdate(index);
    }
    public boolean isVersionCorrect(){
        String str = getCurrentVersion();
        return str.equals(getVersion(getVersionCount()-1));
    }
    public String getWarningMessage(){
        String curr = getCurrentVersion();
        String final_version = getVersion(getVersionCount()-1);
        return curr + " -> " + final_version;
    } 
    
    public static int getHeaderUpdate(StringBuffer sb){
        return getHeaderUpdate(sb,System.getProperty("user.dir"));
    }
    public static int getHeaderUpdate(StringBuffer sb,String app_path)
    {
        StringBuffer command = new StringBuffer();
        if(!app_path.equals("")){
            command.append("\n   ").append("ให้พิมพ์คำสั่งต่อไปนี้บน Command Line").append( " \n\n");
            command.append( app_path.substring(0,2)).append( " \n");
            command.append( "cd \"").append(app_path).append("\" \n");
            command.append("set PATH=%PATH%;C:\\Program Files\\PostgreSQL\\8.0\\bin\n");
        }
        else
            command.append("\n   ").append( "ยืนยันให้โปรแกรมทำการปรับปรุงฐานข้อมูลให้ดังรายการต่อไปนี้ ").append( " \n\n");
        
        sb.append(command);
        return 0;
    }    
    /**
     * @authen henbe
     * complete
     * ทำการ execute file sql จากไฟล์ update ที่มีอยู่
     */
    public static int executeSQL(ConnectionInf cinf,Vector vf,StringBuffer sb)
    {
        for(int i=0;i<vf.size();i++)
        {
            String filename = String.valueOf(vf.get(i));
            int slash2 = cinf.getURL().indexOf("//");
            int slash = cinf.getURL().lastIndexOf('/');
            int colon = cinf.getURL().lastIndexOf(':');
            String dbname = cinf.getURL().substring(slash+1);
            String server = cinf.getURL().substring(slash2+2,colon);
            String command = "psql "  + dbname +
                    " -h " + server +
                    " -U " + cinf.getUsername() +
                    " -f " + filename +
                    " > log.txt \n";
            sb.append(command);
        }
        return -1;
    }    
}
