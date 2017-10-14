/*
 * ComboboxControl.java
 *
 * Created on 14 �չҤ� 2549, 10:47 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage.control;

import com.setupreport.manage.control.HosDB;
import java.util.*;
import com.hospital_os.usecase.connection.*;
import com.setupreport.objectdb.*;
import com.setupreport.utility.ComboFix;
import com.setupreport.utility.Language;
import com.hosv3.utility.connection.UpdateStatus;
/**
 *
 * @author Ojika
 */
public class ComboboxControl
{
    
    /** Creates a new instance of ComboboxControl */
    private ConnectionInf theConnectionInf;
    private HosDB theHosDB;
    
    // �����Ţͧ HealthyGroup
    Vector vHealthyGroup;
    // �����Ţͧ EpiAgeGroupType
    Vector vEpiAgeGroupType;
    UpdateStatus theUS;
    
    public ComboboxControl(HosDB hosDB,UpdateStatus us)
    {
        theHosDB = hosDB;
        theUS = us;
        theConnectionInf = theHosDB.theConnectionInf;    
        theConnectionInf.open();
        ComboFix theComboFixHealthyGroup = new ComboFix();
        try
        {  
            // �����Ţͧ HealthyGroup
            vHealthyGroup = theHosDB.theHealthyGroupDB.selectAll();
            // �����Ţͧ AgeGroupType
            vEpiAgeGroupType = theHosDB.theEpiAgeGroupTypeDB.selectAll();
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            theConnectionInf.close();
            theComboFixHealthyGroup = null;
        }
    }
    
    /**
     * �����Ţͧ HealthyGroup
     * @Author Ojika
     * @Date 14/03/2549
     **/
    public Vector listHealthyGroup() 
    {
        if((vHealthyGroup != null))
        {
            return vHealthyGroup;
        }
        theConnectionInf.open();
        try
        {
            vHealthyGroup = new Vector();
            vHealthyGroup = theHosDB.theHealthyGroupDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vHealthyGroup;
    }
    
    /**
     * �����Ţͧ EpiAgeGroupType
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public Vector listEpiAgeGroupType() 
    {
        if((vEpiAgeGroupType != null))
        {
            return vEpiAgeGroupType;
        }
        theConnectionInf.open();
        try
        {
            vEpiAgeGroupType = new Vector();
            vEpiAgeGroupType = theHosDB.theEpiAgeGroupTypeDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vEpiAgeGroupType;
    }
    
}
