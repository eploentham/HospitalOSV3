/*
 * ReportSubject.java
 *
 * Created on 30 มกราคม 2548, 10:37 น.
 */
package com.hosv3.subject;
import java.util.*;
import com.hosv3.usecase.setup.*;
/**
 *
 * @author  tong
 */
public class ReportSubject {
    
    /** Creates a new instance of ReportSubject */
    Vector vcloseFrameReport;
    Vector vquerySQLParam;
    public ReportSubject() {
        vcloseFrameReport = new Vector();
        vquerySQLParam = new Vector();
    }
    public void removeAttach()
    {
        vcloseFrameReport.removeAllElements();
        vquerySQLParam.removeAllElements();
        
    }
    public void querySQLParamReq(ManageSQLTemplateParamReq o)
    {
        vquerySQLParam.add(o);
    }
    
    public void notifyquerySQLParam(String[] columnname, Vector vString) {
        for(int i = 0;i< this.vquerySQLParam.size() ; i++)
        {
            ((ManageSQLTemplateParamReq)this.vquerySQLParam.get(i)).notifyquerySQLParam(columnname,vString);
        }
    }
    
}
