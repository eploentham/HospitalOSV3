/*
 * SystemControl.java
 *
 * Created on 28 กรกฎาคม 2548, 16:48 น.
 * Modified on 25 กันยายน 2551
 */

package com.pcu.control;


import com.hospital_os.usecase.connection.*;
import com.hosv3.utility.connection.UpdateStatus;
import com.hospital_os.object.Site;
import com.hosv3.control.HosControl;

/**
 *
 * @author amp
 *@deprecarted henbe unused
 * @modifier pu
 */
public class SystemControl
{
    ConnectionInf theConnectionInf;
    UpdateStatus theUS;
    HosDB thePcuDB;
    
    public static Site theHospital_Site;

    private HosControl theHC;
    
    /** Creates a new instance of SystemControl */
    public SystemControl()
    {
    }
    
    public SystemControl(ConnectionInf con,HosDB hdb,HosControl hc,UpdateStatus us)
    {
        theConnectionInf = con;
        theHC = hc;
        theUS = us;
        thePcuDB = hdb;
        theHospital_Site = readSite();
    }
    public SystemControl(ConnectionInf con,HosDB hdb,UpdateStatus us)
    {
        theConnectionInf = con;
        theUS = us;
        thePcuDB = hdb;
        theHospital_Site = readSite();
    }
    
    public Site readSite()
    {
        Site site = new Site();
        theConnectionInf.open();
        try
        {
            site = thePcuDB.theSiteDB.selectAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return site;
    }
    
}
