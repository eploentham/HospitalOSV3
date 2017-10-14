/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hosv3.object.printobject;

import java.util.Vector;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author henbe
 */
public class IpdNameCard implements JRDataSource {
    public String pHn;
    public String pAn;
    public String pPrefix;
    public String pFName;
    public String pLName;
    public String pName;
    public String pAge;
    public String pMonth;
    public String pDay;
    public String pBed;
    public String pDate;
    public String pWard;
    public String pYear;
    public String pPid;
    public String pCardId;

    Vector vObject;
    int index = -1;
    public boolean next() throws JRException {
        index++;
        if(index>=vObject.size())
            return false;
        IpdNameCard inc = (IpdNameCard)vObject.get(index);
        this.update(inc);
        return true;
    }

    public Object getFieldValue(JRField arg0) throws JRException
    {
        if(arg0.getName().equals("Hn")) return pHn;
        if(arg0.getName().equals("An")) return pAn;
        if(arg0.getName().equals("Prefix")) return pPrefix;
        if(arg0.getName().equals("FName")) return pFName;
        if(arg0.getName().equals("LName")) return pLName;
        if(arg0.getName().equals("Name")) return pName;
        if(arg0.getName().equals("Age")) return pAge;
        if(arg0.getName().equals("Month")) return pMonth;
        if(arg0.getName().equals("Day")) return pDay;
        if(arg0.getName().equals("Bed")) return pBed;
        if(arg0.getName().equals("Date")) return pDate;
        if(arg0.getName().equals("Ward")) return pWard;
        if(arg0.getName().equals("Year")) return pYear;
        if(arg0.getName().equals("Pid")) return pPid;
        if(arg0.getName().equals("CardId")) return pCardId;
        return "";
    }
    public void add(IpdNameCard ipd){
        if(vObject==null)
            vObject = new Vector();
        vObject.add(ipd);
    }

    private void update(IpdNameCard inc) {
        pHn = inc.pHn;
        pAn = inc.pAn;
        pPrefix = inc.pPrefix;
        pFName = inc.pFName;
        pLName = inc.pLName;
        pName = inc.pName;
        pAge = inc.pAge;
        pMonth = inc.pMonth;
        pDay = inc.pDay;
        pBed = inc.pBed;
        pDate = inc.pDate;
        pWard = inc.pWard;
        pYear = inc.pYear;
        pPid = inc.pPid;
        pCardId = inc.pCardId;

    }

}
