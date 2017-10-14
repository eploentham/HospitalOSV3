/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.report12file.gui;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.connection.UpdateStatus;
import com.report12file.control.DBSuit;
import com.report12file.objdb.Rp12DB2;
import com.report12file.objectnh53.*;

/**
 *
 * @author henbe
 */
public class DBSuit53 extends DBSuit{
 

    public DBSuit53(UpdateStatus us,ConnectionInf con){
         theConnectionInf = con;
         theConnectionInf.open();
         init18FileNhso53(us);
    }

    public static DBSuit getDBSuit(UpdateStatus us,ConnectionInf con){
        return new DBSuit53(us,con);
    }
    private void init18FileNhso53(UpdateStatus us) {
    theAerDB = new Rp12DB2(theConnectionInf,new AerNh53());
    theChaDB = new Rp12DB2(theConnectionInf,new ChaNh53());
    theChtDB = new Rp12DB2(theConnectionInf,new ChtNh53());
    theIopDB = new Rp12DB2(theConnectionInf,new IopNh53());
    theIdxDB = new Rp12DB2(theConnectionInf,new IdxNh53());
    theIrfDB = new Rp12DB2(theConnectionInf,new IrfNh53());
    theIpdDB = new Rp12DB2(theConnectionInf,new IpdNh53());
    theOopDB = new Rp12DB2(theConnectionInf,new OopNh53());
    theOdxDB = new Rp12DB2(theConnectionInf,new OdxNh53());
    theOrfDB = new Rp12DB2(theConnectionInf,new OrfNh53());
    theOpdDB = new Rp12DB2(theConnectionInf,new OpdNh53());
    thePatDB = new Rp12DB2(theConnectionInf,new PatNh53());
    theInsDB = new Rp12DB2(theConnectionInf,new InsNh53());
    theAdpDB = new Rp12DB2(theConnectionInf,new AdpNh53());
    theLvdDB = new Rp12DB2(theConnectionInf,new LvdNh53());
    theDrgDB = new Rp12DB2(theConnectionInf,new DruNh53());
    }
}
