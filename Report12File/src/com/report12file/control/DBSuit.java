/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.report12file.control;

import com.report12file.objdb.Rp12DB2;
import com.hospital_os.usecase.connection.ConnectionInf; 

/**
 *
 * @author henbe
 */
public abstract class DBSuit {

    public ConnectionInf theConnectionInf;
    public Rp12DB2 theAerDB;
    public Rp12DB2 theChaDB;
    public Rp12DB2 theChtDB;
    public Rp12DB2 theIopDB;
    public Rp12DB2 theIdxDB;
    public Rp12DB2 theIrfDB;
    public Rp12DB2 theIpdDB;
    public Rp12DB2 theOopDB;
    public Rp12DB2 theOdxDB;
    public Rp12DB2 theOrfDB;
    public Rp12DB2 theOpdDB;
    public Rp12DB2 thePatDB;
    public Rp12DB2 theInsDB;
    public Rp12DB2 theAdpDB;
    public Rp12DB2 theLvdDB;
    public Rp12DB2 theDrgDB;
/**
 *
 * @return
 */
    public String getResult() {
        StringBuffer sb = new StringBuffer();
            sb.append(theAerDB.getResult());
            sb.append(theChaDB.getResult());
            sb.append(theChtDB.getResult());
            sb.append(theIopDB.getResult());
            sb.append(theIdxDB.getResult());
            sb.append(theIrfDB.getResult());
            sb.append(theIpdDB.getResult());
            sb.append(theOopDB.getResult());
            sb.append(theOdxDB.getResult());
            sb.append(theOrfDB.getResult());
            sb.append(theOpdDB.getResult());
            sb.append(thePatDB.getResult());
            sb.append(theInsDB.getResult());
            sb.append(theAdpDB.getResult());
            sb.append(theDrgDB.getResult());
            return sb.toString();
    }

    public boolean isFinish() {
        return    (theAerDB.isFinish()
            && theChaDB.isFinish()
            && theChtDB.isFinish()
            && theIopDB.isFinish()
            && theIdxDB.isFinish()
            && theIrfDB.isFinish()
            && theIpdDB.isFinish()
            && theOopDB.isFinish()
            && theOdxDB.isFinish()
            && theOrfDB.isFinish()
            && theOpdDB.isFinish()
            && thePatDB.isFinish()
            && theInsDB.isFinish()
            && theAdpDB.isFinish()
            && theDrgDB.isFinish());
    }

    void stop() { 
            theAerDB.stop();
            theChaDB.stop();
            theChtDB.stop();
            theIopDB.stop();
            theIdxDB.stop();
            theIrfDB.stop();
            theIpdDB.stop();
            theOopDB.stop();
            theOdxDB.stop();
            theOrfDB.stop();
            theOpdDB.stop();
            thePatDB.stop();
            theInsDB.stop();
            theAdpDB.stop();
            theDrgDB.stop();
    }
 
}
