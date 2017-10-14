/*
 * ComboFix.java
 *
 * Created on 2 สิงหาคม 2548, 11:23 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.utility;

import com.reportcenter.usecase.connection.CommonInf;
/**
 *
 * @author Noom
 */
public class ComboFix implements CommonInf{
   public String code;
    public String name;
    /** Creates a new instance of FixHospital */
    public ComboFix() {
        code=new String();
        name=new String();
    }
    
    public ComboFix(String code,String name) {
        code=new String();
        name=new String();
        this.code = code;
        this.name = name;
    }
    public String toString()
    {
        return name;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
    
}
