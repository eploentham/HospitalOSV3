/*
 * PrefixPcu.java
 *
 * Created on 9 �ѹ��¹ 2548, 10:40 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.object;
import com.hospital_os.object.Prefix;
import com.hospital_os.usecase.connection.CommonInf;
/**
 *
 * @author jao
 */
public class PrefixPcu extends Prefix implements CommonInf//,com.henbe.connection.CommonInf
{
    
    /** Creates a new instance of PrefixPcu */
    public PrefixPcu() {
    }
     public String getCode() {
        return this.getObjectId();
    }
    
    public String getName() {
        return this.description;
    }
    public String toString(){
        return getName();
    }
}
