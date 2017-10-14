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
public class Rp115_2 implements  JRDataSource{

    public static String getReportFn(){
        return "config/rp_standard/rp1105_part2.xml";
    }
    public static Rp115_2 initValue(Vector vGroup2Pre, Vector vGroup2Post, Vector vGroup2Family) {

        Rp115_2 ds = new Rp115_2();
        for (int i = 0; i < vGroup2Pre.size(); i++) {
            RP115Group2Family_2549 theRP115Group2Family_2549 = (RP115Group2Family_2549)vGroup2Family.get(i);
            RP115Group2Pre_2549 theRP115Group2Pre_2549 = (RP115Group2Pre_2549)vGroup2Pre.get(i);
            RP115Group2Post_2549 theRP115Group2Post_2549 = (RP115Group2Post_2549)vGroup2Post.get(i);
            Rp115_2 rp = new Rp115_2();
            rp.setValue(
                theRP115Group2Pre_2549.plan_type,new Integer[]{
                Integer.valueOf(theRP115Group2Pre_2549.abort),
                Integer.valueOf(theRP115Group2Pre_2549.birth_and_death),
                Integer.valueOf(theRP115Group2Pre_2549.baby_death),
                Integer.valueOf(theRP115Group2Pre_2549.mother_death),
                Integer.valueOf(theRP115Group2Pre_2549.ipd_death),
                Integer.valueOf(theRP115Group2Pre_2549.opd_death),
                Integer.valueOf(theRP115Group2Post_2549.before_mch_patient),
                Integer.valueOf(theRP115Group2Post_2549.before_mch_visit),
                Integer.valueOf(theRP115Group2Post_2549.after_mch_patient),
                Integer.valueOf(theRP115Group2Post_2549.after_mch_visit),
                Integer.valueOf(theRP115Group2Post_2549.birth_usual_infant),
                Integer.valueOf(theRP115Group2Post_2549.birth_un_usual_infant),
                Integer.valueOf(theRP115Group2Post_2549.treatment_dental),
                Integer.valueOf(theRP115Group2Family_2549.childless_female),
                Integer.valueOf(theRP115Group2Family_2549.childless_male),
                Integer.valueOf(theRP115Group2Family_2549.childless_drug),
                Integer.valueOf(theRP115Group2Family_2549.childless_other)});
            ds.add(rp);
        }
        return ds;
    }


    public String plan_type;
                public Integer abort;
                public Integer birth_and_death;
                public Integer baby_death;
                public Integer mother_death;
                public Integer ipd_death;
                public Integer opd_death;
                public Integer before_mch_patient;
                public Integer before_mch_visit;
                public Integer after_mch_patient;
                public Integer after_mch_visit;
                public Integer birth_usual_infant;
                public Integer birth_un_usual_infant;
                public Integer treatment_dental;
                public Integer childless_female;
                public Integer childless_male;
                public Integer childless_drug;
                public Integer childless_other;

    Vector vObject;
    int index = -1;
    public boolean next() throws JRException {
        index++;
        if(index>=vObject.size())
            return false;
        Rp115_2 inc = (Rp115_2)vObject.get(index);
        this.update(inc);
        return true;
    }

    public Object getFieldValue(JRField arg0) throws JRException
    {
        if(arg0.getName().equals("plan_type")) return plan_type;
        if(arg0.getName().equals("abort")) return abort;
        if(arg0.getName().equals("birth_and_death")) return birth_and_death;
        if(arg0.getName().equals("baby_death")) return baby_death;
        if(arg0.getName().equals("mother_death")) return mother_death;
        if(arg0.getName().equals("ipd_death")) return ipd_death;
        if(arg0.getName().equals("opd_death")) return opd_death;
        if(arg0.getName().equals("before_mch_patient")) return before_mch_patient;
        if(arg0.getName().equals("before_mch_visit")) return before_mch_visit;
        if(arg0.getName().equals("after_mch_patient")) return after_mch_patient;
        if(arg0.getName().equals("after_mch_visit")) return after_mch_visit;
        if(arg0.getName().equals("birth_usual_infant")) return birth_usual_infant;
        if(arg0.getName().equals("birth_un_usual_infant")) return birth_un_usual_infant;
        if(arg0.getName().equals("treatment_dental")) return treatment_dental;
        if(arg0.getName().equals("childless_female")) return childless_female;
        if(arg0.getName().equals("childless_male")) return childless_male;
        if(arg0.getName().equals("childless_drug")) return childless_drug;
        if(arg0.getName().equals("childless_other")) return childless_other;
        if(arg0.getName().equals("autopsy")) return new Integer(0);
        if(arg0.getName().equals("plan_tab")) return new Integer(0);

        return "";
    }
    public void add(Rp115_2 ipd){
        if(vObject==null)
            vObject = new Vector();
        vObject.add(ipd);
    }

    private void update(Rp115_2 inc) {
        this.setValue(inc.plan_type, inc.getIntegers());
    }
    public void setValue(String type,Integer[] data){
        Rp115_2 rp = this;
        rp.plan_type = type;
                rp.abort = data[0];
                rp.birth_and_death = data[1];
                rp.baby_death = data[2];
                rp.mother_death = data[3];
                rp.ipd_death = data[4];
                rp.opd_death = data[5];
                rp.before_mch_patient = data[6];
                rp.before_mch_visit = data[7];
                rp.after_mch_patient = data[8];
                rp.after_mch_visit = data[9];
                rp.birth_usual_infant = data[10];
                rp.birth_un_usual_infant = data[11];
                rp.treatment_dental = data[12];
                rp.childless_female = data[13];
                rp.childless_male = data[14];
                rp.childless_drug = data[15];
                rp.childless_other = data[16];
    }
    public Integer[] getIntegers(){
        return new Integer[]{
                abort
                ,birth_and_death
                ,baby_death
                ,mother_death
                ,ipd_death
                ,opd_death
                ,before_mch_patient
                ,before_mch_visit
                ,after_mch_patient
                ,after_mch_visit
                ,birth_usual_infant
                ,birth_un_usual_infant
                ,treatment_dental
                ,childless_female
                ,childless_male
                ,childless_drug
                ,childless_other};
    }

}
