/*
 * Office.java
 *
 * Created on 11 ตุลาคม 2546, 11:11 น.
 */

package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.*;
/**
 *
 * @author  tong
 */
public class Office extends Persistent implements CommonInf{
    
    
    /** Creates a new instance of Office */
    
    public String name;
    public String off_name1;
    public String off_name2;
    public String minis;
    public String dep;
    public String off_type;
    public String specific;
    public String bed;
    public String changwat;
    public String ampur;
    public String tambon;
    public String moo;
    
    
    
    public Office() {

    }
    public String getCode(){return getObjectId();}
    public String getName(){return name;}
    public String toString(){return getObjectId() + ":" + name;}
   
}
