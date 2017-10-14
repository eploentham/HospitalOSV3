/*
 * ManageDB.java
 *
 * Created on 13 ÁÔ¶Ø¹ÒÂ¹ 2549, 12:54 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreportnan.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportnan.objdb.ItemNanDB;
import com.reportnan.objdb.NCDItemGroupDB;
import com.reportnan.objdb.NCDItemGroupMapItemDB;
import com.reportnan.objdb.OPDItemDB;
import com.reportnan.objdb.OfficeNanDB;
import com.reportnan.objdb.OperatingItemDB;
import com.reportnan.objdb.ReferHospitalDB;
import com.reportnan.objdb.ReferHospitalTypeDB;
import com.reportnan.objdb.ServicePointNanDB;
import com.reportnan.objdb.ServicePointTypeDB;
import com.reportnan.objdb.ServicePointTypeMapDB;
/**
 *
 * @author pu
 */
public class ManageDB
{
    public ConnectionInf theConnectionInf;
    public ServicePointTypeDB theServicePointTypeDB;
    public ServicePointTypeMapDB theServicePointTypeMapDB;
    public ServicePointNanDB theServicePointNanDB;
    
    public NCDItemGroupDB theNCDItemGroupDB;
    public NCDItemGroupMapItemDB theNCDItemGroupMapItemDB;
    public OPDItemDB theOPDItemDB;
    public ReferHospitalDB theReferHospitalDB;
    public OfficeNanDB theOfficeNanDB;
    public ItemNanDB theItemNanDB;
    public OperatingItemDB theOperatingItemDB;
    public ReferHospitalTypeDB theReferHospitalTypeDB;
        
    public ManageDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theServicePointNanDB = new ServicePointNanDB(theConnectionInf);
        theServicePointTypeDB = new ServicePointTypeDB(theConnectionInf);
        theServicePointTypeMapDB = new ServicePointTypeMapDB(theConnectionInf);
        
        theNCDItemGroupDB = new NCDItemGroupDB(theConnectionInf);
        theNCDItemGroupMapItemDB = new NCDItemGroupMapItemDB(theConnectionInf);
        theOPDItemDB = new OPDItemDB(theConnectionInf);
        theReferHospitalDB = new ReferHospitalDB(theConnectionInf);
        theOfficeNanDB = new OfficeNanDB(theConnectionInf);
        theItemNanDB = new ItemNanDB(theConnectionInf);
        theOperatingItemDB = new OperatingItemDB(theConnectionInf);
        theReferHospitalTypeDB = new ReferHospitalTypeDB(theConnectionInf);
    }
    
    
    
}
