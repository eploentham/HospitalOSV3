/*
 * NameReport.java
 *
 * Created on 03 �Զع�¹ 2549, 15:10 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.utility;
import java.util.*;
/**
 *
 * @author pu
 */
public class NameReport {
    
    HashMap theHashMapReport;
    public NameReport()
    {
        theHashMapReport = new HashMap();
        setObject();
    }
    
    /**
     *  �觢�������§ҹ
     */
    public HashMap getObject()
    {
        return theHashMapReport;
    }
    
    /**
     * �������§ҹ���� ����� ��ͧ������ ��ǹ�����·ء����
     */
    private void setObject()
    {
        Report report = new Report(); 
        
        report.INDEX = "1";
        report.ENG_NAME = "ReportResident";
        report.THAI_NAME = "��§ҹ�ӹǹ��Ъҡ�";
        report.DESCRIPTION = "��§ҹ�ӹǹ��Ъҡ� ��ѧ�����͹ ��ͺ���� �¡��������ҹ";
        
        theHashMapReport.put(report.INDEX,report );        
      
        report = null;
        
        report = new Report(); 
        
        report.INDEX = "2";
        report.ENG_NAME = "ReportPatientInClinic";
        report.THAI_NAME = "��§ҹ�����·������Ѻ��ԡ�ä�Թԡ��ҧ �";
        report.DESCRIPTION = "��§ҹ�����·������Ѻ��ԡ�ä�Թԡ��ҧ �";
        
        theHashMapReport.put(report.INDEX,report );        
      
        report = null;
        
        report = new Report(); 
        
        report.INDEX = "3";
        report.ENG_NAME = "ReportPatientOPDRemain";
        report.THAI_NAME = "��§ҹ�١˹������ѹ�����������¹͡";
        report.DESCRIPTION = "��§ҹ�١˹������ѹ�����������¹͡";
        
        theHashMapReport.put(report.INDEX,report );    
        
        report = null;
        
        report = new Report(); 
        
        report.INDEX = "4";
        report.ENG_NAME = "ReportAccident19Cause";
        report.THAI_NAME = "��§ҹ�غѵ��˵� 19 ���˵�";
        report.DESCRIPTION = "��§ҹ�غѵ��˵� 19 ���˵�Ẻ������ͧ��ҹ";
        
        theHashMapReport.put(report.INDEX,report );    
        
        report = null;
        
        report = new Report(); 
        
        report.INDEX = "5";
        report.ENG_NAME = "ReportEmergencyPatient";
        report.THAI_NAME = "��§ҹ�����©ء�Թ-���ء�Թ";
        report.DESCRIPTION = "��§ҹ�����©ء�Թ-���ء�Թ ��ṡ�����âͧ ER";
        
        theHashMapReport.put(report.INDEX,report ); 
    
        report = null;
        
        report = new Report();
        
        report.INDEX = "6";
        report.ENG_NAME = "ReportPatientOperated";
        report.THAI_NAME = "��§ҹ�����·���Ѻ��ԡ���ѵ����";
        report.DESCRIPTION = "��§ҹ�����·���Ѻ��ԡ���ѵ���� �¡����ش��ԡ��";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        
        report = new Report();
        
        report.INDEX = "7";
        report.ENG_NAME = "ReportDailyOPDPatient";
        report.THAI_NAME = "��§ҹ��Ш��ѹ �ҹ�����¹͡";
        report.DESCRIPTION = "��§ҹ��Ш��ѹ �ҹ�����¹͡ �¡����ش��ԡ��";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        
        report = new Report();
        
        report.INDEX = "8";
        report.ENG_NAME = "ReportIPDPatient";
        report.THAI_NAME = "��§ҹ�������";
        report.DESCRIPTION = "��§ҹ�������";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        
        report = new Report();
        
        report.INDEX = "9";
        report.ENG_NAME = "Report505INClinic";
        report.THAI_NAME = "��§ҹ 505 �¡�����Թԡ";
        report.DESCRIPTION = "��§ҹ�ҵðҹ ������ä 505 �¡�����Թԡ";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        
        report = new Report();
        
        report.INDEX = "10";
        report.ENG_NAME = "ReportPatientNCD";
        report.THAI_NAME = "��§ҹ NCD";
        report.DESCRIPTION = "��§ҹ NCD";
        
        theHashMapReport.put(report.INDEX,report );
    }
    
    
    
}
