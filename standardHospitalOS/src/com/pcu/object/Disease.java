/*
 * Contagious.java
 *
 * Created on 21 กุมภาพันธ์ 2549, 17:20 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.*;
/**
 *
 * @author Administrator
 */
public class Disease extends Persistent implements CommonInf{
    private static String init = "";
    public String number = init;
    public String diseaseName= init;
    public String standardName= init;
    //public String icdCode;
    public String isContagiousDisease= init; 
    public String active= init;
    
    /** Creates a new instance of Contagious */
    public Disease() {
    }

    public String getCode() {
        return diseaseName;
    }
    public String getName() {
        return diseaseName;
    }
    public String toString() {
        return diseaseName;
    }
    
}
