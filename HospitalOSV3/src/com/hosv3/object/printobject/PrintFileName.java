/*
 * PrintFileName.java
 *
 * Created on 13 ¡’π“§¡ 2550, 21:48 π.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hosv3.object.printobject;

/**
 *
 * @author henbe
 */
public class PrintFileName {
    
    /** Creates a new instance of PrintFileName */
    public PrintFileName() {
    }
    public static String getFileName(int choose)
    {
        String file = "";
        switch(choose)
        {
            case 1 :    file = "x_ray_card";
                        break;
            case 2 :    file = "Report_item";
                        break;
            case 3 :    file = "Drug_Sticker";
                        break;
            case 4 :    file = "appointment";
                        break;
            case 5 :    file = "appointmentList";
                        break;
            case 6 :    file = "refer";
                        break;
            case 7 :    file = "index";
                        break;
            case 8 :    file = "visitslipNew";
                        break;
            case 9 :    file = "receipt";
                        break;
            case 10 :   file = "resultLab";
                        break;
            case 11 :    file = "drugRx";
                        break;
            case 12 :   file = "Report_Order";
                        break;
            case 13 :   file = "OPD_Card"; //file = "OPD_Head";
                        break;
            case 14 :   file = "drugFund_receipt";
                        break;
            case 15 :   file = "chronic_report";
                        break;
            case 16 :   file = "Billing_Report";
                        break;
            case 17 :   file = "summary";
                        break;
            case 18 :   file = "surveil_report";
                        break;
            case 19 :   file = "IpdNameCard";
                        break; 
            case 20 :   file = "guide";
                        break;
            case 21 :   file = "Report_16Group";
                        break;
            case 22 :   file = "Report_OrderGroupByItemName";
                        break;
            case 23 :   file = "ncd";
                        break;
            default : 
                        break;
        }
        return file;
        
    }
    
}
