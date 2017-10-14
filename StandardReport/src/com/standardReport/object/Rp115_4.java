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
public class Rp115_4 implements  JRDataSource{

    public static String getReportFn(){
        return "config/rp_standard/rp1105_part4.xml";
    }
    public static Rp115_4 initValue(Vector vSchoolOutSite, Vector vHomeAmount, Vector vPersonAmount
            , Vector vEfficiency, Vector vTreatInfant, Vector vAbortPregnant
            , Vector vAssort, Vector vCheckConfirm) {
        Rp115_4 ds = new Rp115_4();
        for (int i = 0; i < vAssort.size(); i++) {
               RP115Group4SchoolOutSite_2549 theRP115Group4SchoolOutSite_2549 = (RP115Group4SchoolOutSite_2549)vSchoolOutSite.get(0);
               RP115Group4Assort_2549 theRP115Group4Assort_2549 = (RP115Group4Assort_2549)vAssort.get(i);
               RP115Group4CheckConfirm_2549 theRP115Group4CheckConfirm_2549 = (RP115Group4CheckConfirm_2549)vCheckConfirm.get(i);
               RP115Group4AbortPregnant_2549 theRP115Group4AbortPregnant_2549 = (RP115Group4AbortPregnant_2549)vAbortPregnant.get(i);
               RP115Group4TreatInfant_2549 theRP115Group4TreatInfant_2549 = (RP115Group4TreatInfant_2549)vTreatInfant.get(i);
               RP115Group4Efficiency_2549 theRP115Group4Efficiency_2549 = (RP115Group4Efficiency_2549)vEfficiency.get(i);
               RP115Group4PersonAmount_2549 theRP115Group4PersonAmount_2549 = (RP115Group4PersonAmount_2549)vPersonAmount.get(i);
               RP115Group4HomeAmount_2549 theRP115Group4HomeAmount_2549 = (RP115Group4HomeAmount_2549)vHomeAmount.get(0);
            Rp115_4 rp = new Rp115_4();
            rp.setValue(
                theRP115Group4Assort_2549.plan_type,new Integer[]{
                Integer.valueOf(theRP115Group4HomeAmount_2549.homeAmount),
                Integer.valueOf(theRP115Group4PersonAmount_2549.person),
                Integer.valueOf(theRP115Group4Efficiency_2549.efficiency),
                Integer.valueOf(theRP115Group4TreatInfant_2549.treat_thiroid),
                Integer.valueOf(theRP115Group4AbortPregnant_2549.terminated_pregnance),
                Integer.valueOf(theRP115Group4CheckConfirm_2549.thalassemia_infant),
                Integer.valueOf(theRP115Group4CheckConfirm_2549.thiroid),
                Integer.valueOf(theRP115Group4Assort_2549.womb_cancer),
                Integer.valueOf(theRP115Group4Assort_2549.breast_cancer),
                Integer.valueOf(theRP115Group4Assort_2549.thalassemia),
                Integer.valueOf(theRP115Group4Assort_2549.thalassemia_mom),
                Integer.valueOf(theRP115Group4Assort_2549.iodine_thiroid),
                Integer.valueOf(theRP115Group4SchoolOutSite_2549.visit_school)});
            ds.add(rp);
        }
        return ds;
    }


    public String plan_type;
                public Integer homeAmount;
                public Integer person;
                public Integer efficiency;
                public Integer treat_thiroid;
                public Integer terminated_pregnance;
                public Integer thalassemia_infant;
                public Integer thiroid;;
                public Integer womb_cancer;
                public Integer breast_cancer;
                public Integer thalassemia;
                public Integer thalassemia_mom;
                public Integer iodine_thiroid;
                public Integer visit_school;

    Vector vObject;
    int index = -1;
    public boolean next() throws JRException {
        index++;
        if(index>=vObject.size())
            return false;
        Rp115_4 inc = (Rp115_4)vObject.get(index);
        this.update(inc);
        return true;
    }

    public Object getFieldValue(JRField arg0) throws JRException
    { 
        if(arg0.getName().equals("plan_type")) return plan_type;
        if(arg0.getName().equals("homeAmount")) return homeAmount;
        if(arg0.getName().equals("person")) return person;
        if(arg0.getName().equals("efficiency")) return efficiency;
        if(arg0.getName().equals("treat_thiroid")) return treat_thiroid;
        if(arg0.getName().equals("terminated_pregnance")) return terminated_pregnance;
        if(arg0.getName().equals("thalassemia_infant")) return thalassemia_infant;
        if(arg0.getName().equals("thiroid")) return thiroid;
        if(arg0.getName().equals("womb_cancer")) return womb_cancer;
        if(arg0.getName().equals("breast_cancer")) return breast_cancer;
        if(arg0.getName().equals("thalassemia")) return thalassemia;
        if(arg0.getName().equals("thalassemia_mom")) return thalassemia_mom;
        if(arg0.getName().equals("iodine_thiroid")) return iodine_thiroid;
        if(arg0.getName().equals("visit_school")) return visit_school;
        return new Integer(0);
    }
    public void add(Rp115_4 ipd){
        if(vObject==null)
            vObject = new Vector();
        vObject.add(ipd);
    }

    private void update(Rp115_4 inc) {
        this.setValue(inc.plan_type, inc.getIntegers());
    }
    public void setValue(String type,Integer[] data){
        plan_type = type;
                homeAmount = data[0];
                person = data[1];
                efficiency = data[2];
                treat_thiroid = data[3];
                terminated_pregnance = data[4];
                thalassemia_infant = data[5];
                thiroid = data[6];
                womb_cancer = data[7];
                breast_cancer = data[8];
                thalassemia = data[9];
                thalassemia_mom = data[10];
                iodine_thiroid = data[11];
                visit_school = data[12];
    }

    public Integer[] getIntegers(){
        return new Integer[]{
                homeAmount
                ,person
                ,efficiency
                ,treat_thiroid
                ,terminated_pregnance
                ,thalassemia_infant
                ,thiroid
                ,womb_cancer
                ,breast_cancer
                ,thalassemia
                ,thalassemia_mom
                ,iodine_thiroid
                ,visit_school};
    }
}
