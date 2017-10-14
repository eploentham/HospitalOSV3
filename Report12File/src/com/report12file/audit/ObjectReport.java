package com.report12file.audit;
/*
 * ObjectReport.java
 *
 * Created on 31 สิงหาคม 2550, 16:25 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Abb
 */
import java.io.*;
import java.util.*;

public class ObjectReport
{
    

    String _errCountIPDIDX;
    String _errCountOPDODX;
    String _dsn;
    public String errCountIPDIDX = "";
    public String errCountOPDODX = "";

    public String dsn = "";
    public String errDate = "";
    public String errTotalMoneyHN = "";
    public String errHnINSPAT = "";
    public String errAnIPDIRF = "";
    public String errAnIPDIDX = "";
    public String errAnIPDIOP = "";
    public String errHnOPDORF = "";
    public String errHnOPDODX = "";
    public String errHnOPDOOP = "";
    
    public Vector V_Date = new Vector();

    public Vector V_errCountIPDIDX = new Vector(); 
    public Vector V_errCountOPDODX = new Vector(); 
    public Vector V_TotalMonetHn = new Vector();
    public Vector V_errHnINSPAT = new Vector();
    public Vector V_errAnIPDIRF = new Vector();
    public Vector V_errAnIPDIDX = new Vector();
    public Vector V_errAnIPDIOP = new Vector();
    public Vector V_errHnOPDORF = new Vector();
    public Vector V_errHnOPDODX = new Vector();
    public Vector V_errHnOPDOOP = new Vector();
   
    
    /** Creates a new instance of ObjectReport */
    public ObjectReport(String errHnINSPAT,String errCountIPDIDX,String errCountOPDODX,String errCountOPDIPD, String errTotalMoneyHN,String errDate,String errAnIPDIRF, 
                        String errAnIPDIDX,String errAnIPDIOP,String errHnOPDORF,String errHnOPDODX,String errHnOPDOOP,String dsn)
    {
       
        this.errHnINSPAT = errHnINSPAT;
        this.errCountIPDIDX = errCountIPDIDX;
        this.errCountOPDODX = errCountOPDODX;
        this.errTotalMoneyHN = errTotalMoneyHN;
        this.errDate = errDate;
        this.errAnIPDIRF = errAnIPDIRF;
        this.errAnIPDIDX = errAnIPDIDX;
        this.errAnIPDIOP = errAnIPDIOP;
        this.errHnOPDORF = errHnOPDORF;
        this.errHnOPDODX = errHnOPDODX;
        this.errHnOPDOOP = errHnOPDOOP;
        this.dsn = dsn;
   
    }
    
    public void seterrHnINSPAT(String errHnINSPAT)
    {
        V_errHnINSPAT.addElement(errHnINSPAT);
    }
      
    public void seterrCountIPDIDX(String errCountIPDIDX)
    {
        V_errCountIPDIDX.add(errCountIPDIDX);
    }
    
    public void seterrCountOPDODX(String errCountOPDODX)
    {
        V_errCountOPDODX.add(errCountOPDODX);
    }
     
    public void seterrTotalMoneyHN(String errTotalMoneyHN)
    {
        V_TotalMonetHn.addElement(errTotalMoneyHN);
    }
    
    public void seterrDate(String errDate)
    {
        V_Date.addElement(errDate);
    }
    
    public void seterrAnIPDIRF(String errAnIPDIRF)
    {
        V_errAnIPDIRF.addElement(errAnIPDIRF);
    }
    
    public void seterrAnIPDIDX(String errAnIPDIDX)
    {
        V_errAnIPDIDX.addElement(errAnIPDIDX);
    }
    
    public void seterrAnIPDIOP(String errAnIPDIOP)
    {
        V_errAnIPDIOP.addElement(errAnIPDIOP);
    }
    
    public void seterrHnOPDORF(String errHnOPDORF)
    {
        V_errHnOPDORF.addElement(errHnOPDORF);
    }
    
    public void seterrHnOPDODX(String errHnOPDODX)
    {
        V_errHnOPDODX.addElement(errHnOPDODX);
    }
    
    public void seterrHnOPDOOP(String errHnOPDOOP)
    {
        V_errHnOPDOOP.addElement(errHnOPDOOP);
    }
   
    public Vector geterrHnINSPAT()
    {
        return V_errHnINSPAT;
    }
    
     public Vector geterrCountIPDIDX()
    {
        return V_errCountIPDIDX;
    }
     
    public Vector geterrCountOPDODX()
    {
        return V_errCountOPDODX;
    }
       
    public Vector geterrTotalMoneyHN()
    {
        return V_TotalMonetHn;
    }
    
    public Vector geterrDate()
    {
        return V_Date;
    }
    
    public Vector geterrAnIPDIRF()
    {
        return V_errAnIPDIRF;
    }
    
    public Vector geterrAnIPDIDX()
    {
        return V_errAnIPDIDX;
    }
    
    public Vector geterrAnIPDIOP()
    {
        return V_errAnIPDIOP;
    }
    
       
    public int getSizeerrHnINSPAT()
    {
        return V_errHnINSPAT.size();
    }
    
    
     public Vector geterrHnOPDORF()
    {
        return V_errHnOPDORF;
    } 
     
     
    public Vector geterrHnOPDODX()
    {
        return V_errHnOPDODX;
    }
    
    public Vector geterrHnOPDOOP()
    {
        return V_errHnOPDOOP;
    }
    
        public void setdsn(String dsn) 
    {
        _dsn = dsn;
    }
    
    public String getdsn()
    {
        return _dsn;
    }
    
    
}
