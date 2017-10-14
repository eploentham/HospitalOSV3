/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.standardReport.object;

import java.util.Vector;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author henbe
 */
public class Rp115_1 implements  JRDataSource{


    public String plan_type;
    public Integer pcu_new_patient_incup;
    public Integer pcu_new_patient_outcup;
    public Integer pcu_visit_incup;
    public Integer pcu_visit_outcup;
    public Integer refer_in_incup;
    public Integer refer_in_inchangwat;
    public Integer refer_in_outchangwat;
    public Integer refer_out_incup;
    public Integer refer_out_inchangwat;
    public Integer refer_out_outchangwat;
    public Integer ipd_discharge_incup;
    public Integer ipd_discharge_outcup;
    public Integer ipd_day_stay_incup;
    public Integer ipd_day_stay_outcup;
    public Integer opd_new_patient_incup;
    public Integer opd_new_patient_outcup;
    public Integer opd_visit_incup;
    public Integer opd_visit_outcup;

    Vector vObject;
    int index = -1;
    public static String getReportFn(){
        return "config/rp_standard/rp1105_part1.xml";
    }
    public boolean next() throws JRException {
        index++;
        if(index>=vObject.size())
            return false;
        Rp115_1 inc = (Rp115_1)vObject.get(index);
        this.update(inc);
        return true;
    }
    /**
     * รอให้ iReport เสร็จก่อนจะได้เอามาดูชื่อฟิลด์
     * @param arg0
     * @return
     * @throws net.sf.jasperreports.engine.JRException
     */
    public Object getFieldValue(JRField arg0) throws JRException
    {
    if(arg0.getName().equals("plan_type")) return plan_type;
    if(arg0.getName().equals("pcu_new_patient_incup")) return pcu_new_patient_incup;
    if(arg0.getName().equals("pcu_new_patient_outcup")) return pcu_new_patient_outcup;
    if(arg0.getName().equals("pcu_visit_incup")) return pcu_visit_incup;
    if(arg0.getName().equals("pcu_visit_outcup")) return pcu_visit_outcup;
    if(arg0.getName().equals("refer_in_incup")) return refer_in_incup;
    if(arg0.getName().equals("refer_in_inchangwat")) return refer_in_inchangwat;
    if(arg0.getName().equals("refer_in_outchangwat")) return refer_in_outchangwat;
    if(arg0.getName().equals("refer_out_incup")) return refer_out_incup;
    if(arg0.getName().equals("refer_out_inchangwat")) return refer_out_inchangwat;
    if(arg0.getName().equals("refer_out_outchangwat")) return refer_out_outchangwat;
    if(arg0.getName().equals("ipd_discharge_incup")) return ipd_discharge_incup;
    if(arg0.getName().equals("ipd_discharge_outcup")) return ipd_discharge_outcup;
    if(arg0.getName().equals("ipd_day_stay_incup")) return ipd_day_stay_incup;
    if(arg0.getName().equals("ipd_day_stay_outcup")) return ipd_day_stay_outcup;
    if(arg0.getName().equals("opd_new_patient_incup")) return opd_new_patient_incup;
    if(arg0.getName().equals("opd_new_patient_outcup")) return opd_new_patient_outcup;
    if(arg0.getName().equals("opd_visit_incup")) return opd_visit_incup;
    if(arg0.getName().equals("opd_visit_outcup")) return opd_visit_outcup;

        return "";
    }
    public void add(Rp115_1 ipd){
        if(vObject==null)
            vObject = new Vector();
        vObject.add(ipd);
    }

    private void update(Rp115_1 inc) {
        plan_type = inc.plan_type;
        pcu_new_patient_incup = inc.pcu_new_patient_incup;
        pcu_new_patient_outcup = inc.pcu_new_patient_outcup;
        pcu_visit_incup = inc.pcu_visit_incup;
        pcu_visit_outcup = inc.pcu_visit_outcup;
        refer_in_incup = inc.refer_in_incup;
        refer_in_inchangwat = inc.refer_in_inchangwat;
        refer_in_outchangwat = inc.refer_in_outchangwat;
        refer_out_incup = inc.refer_out_incup;
        refer_out_inchangwat = inc.refer_out_inchangwat;
        refer_out_outchangwat = inc.refer_out_outchangwat;
        ipd_discharge_incup = inc.ipd_discharge_incup;
        ipd_discharge_outcup = inc.ipd_discharge_outcup;
        ipd_day_stay_incup = inc.ipd_day_stay_incup;
        ipd_day_stay_outcup = inc.ipd_day_stay_outcup;
        opd_new_patient_incup = inc.opd_new_patient_incup;
        opd_new_patient_outcup = inc.opd_new_patient_outcup;
        opd_visit_incup = inc.opd_visit_incup;
        opd_visit_outcup = inc.opd_visit_outcup;
    }
    public static Rp115_1 initValue(String type,Integer[] data){
        Rp115_1 rp = new Rp115_1();
        rp.plan_type = type;
        rp.pcu_new_patient_incup = data[0];
        rp.pcu_new_patient_outcup = data[1];
        rp.pcu_visit_incup = data[2];
        rp.pcu_visit_outcup = data[3];
        rp.refer_in_incup = data[4];
        rp.refer_in_inchangwat = data[5];
        rp.refer_in_outchangwat = data[6];
        rp.refer_out_incup = data[7];
        rp.refer_out_inchangwat = data[8];
        rp.refer_out_outchangwat = data[9];
        rp.ipd_discharge_incup = data[10];
        rp.ipd_discharge_outcup = data[11];
        rp.ipd_day_stay_incup = data[12];
        rp.ipd_day_stay_outcup = data[13];
        rp.opd_new_patient_incup = data[14];
        rp.opd_new_patient_outcup = data[15];
        rp.opd_visit_incup = data[16];
        rp.opd_visit_outcup = data[17];
        return rp;
    }
    public static Rp115_1 initValue(Vector vopd, Vector vipd, Vector vrefer, Vector vpcu) {
        Rp115_1 ds = new Rp115_1();
        for (int i = 0; i < vrefer.size(); i++) {
            RP115Group1PCU theRP115Group1PCU = (RP115Group1PCU)vpcu.get(i);
            RP115Group1Refer theRP115Group1Refer = (RP115Group1Refer)vrefer.get(i);
            RP115Group1IPD theRP115Group1IPD = (RP115Group1IPD) vipd.get(i);
            RP115Group1OPD theRP115Group1OPD = (RP115Group1OPD)vopd.get(i);
            Rp115_1 rp = Rp115_1.initValue(
            theRP115Group1Refer.refer_plan_type,new Integer[]{
            Integer.valueOf(theRP115Group1PCU.pcu_new_patient_incup),
            Integer.valueOf(theRP115Group1PCU.pcu_new_patient_outcup),
            Integer.valueOf(theRP115Group1PCU.pcu_visit_incup),
            Integer.valueOf(theRP115Group1PCU.pcu_visit_outcup),
            Integer.valueOf(theRP115Group1Refer.refer_in_incup),
            Integer.valueOf(theRP115Group1Refer.refer_in_inchangwat),
            Integer.valueOf(theRP115Group1Refer.refer_in_outchangwat),
            Integer.valueOf(theRP115Group1Refer.refer_out_incup),
            Integer.valueOf(theRP115Group1Refer.refer_out_inchangwat),
            Integer.valueOf(theRP115Group1Refer.refer_out_outchangwat),
            Integer.valueOf(theRP115Group1IPD.ipd_discharge_incup),
            Integer.valueOf(theRP115Group1IPD.ipd_discharge_outcup),
            Integer.valueOf(theRP115Group1IPD.ipd_day_stay_incup),
            Integer.valueOf(theRP115Group1IPD.ipd_day_stay_outcup),
            Integer.valueOf(theRP115Group1OPD.opd_new_patient_incup),
            Integer.valueOf(theRP115Group1OPD.opd_new_patient_outcup),
            Integer.valueOf(theRP115Group1OPD.opd_visit_incup),
            Integer.valueOf(theRP115Group1OPD.opd_visit_outcup)});
            ds.add(rp);
        }
        return ds;
    }
}
