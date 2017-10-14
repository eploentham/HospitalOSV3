/*
 * MaimType.java
 *
 * Created on 22 กุมภาพันธ์ 2549, 10:18 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class MaimType extends Persistent{
   private static String init = "";
    public String maim_number = init;
    public String description = init;
    public String active = init;

    /** Creates a new instance of MaimType */
    public MaimType() {
    }
    
}
