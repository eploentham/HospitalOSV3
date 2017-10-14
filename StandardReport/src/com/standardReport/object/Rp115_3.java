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
public class Rp115_3 implements  JRDataSource{

    public static String getReportFn(){
        return "config/rp_standard/rp1105_part3.xml";
    }
    public static Rp115_3 initValue(Vector vGroup3Healthy, Vector vGroup3Nutrition
            , Vector vGroup3MMR, Vector vGroup3DentalProtect, Vector vGroup3Epi) {

        Rp115_3 ds = new Rp115_3();
        for (int i = 0; i < vGroup3Healthy.size(); i++) {
            RP115Group3Healthy_2549 theRP115Group3Healthy_2549 = (RP115Group3Healthy_2549)vGroup3Healthy.get(i);
            RP115Group3Nutrition_2549 theRP115Group3Nutrition_2549 = (RP115Group3Nutrition_2549)vGroup3Nutrition.get(i);
            RP115Group3MMR_2549 theRP115Group3MMR_2549 = (RP115Group3MMR_2549)vGroup3MMR.get(i);
            RP115Group3DentalProtect_2549 theRP115Group3DentalProtect_2549 = (RP115Group3DentalProtect_2549)vGroup3DentalProtect.get(i);
            RP115Group3Epi_2549 theRP115Group3Epi_2549 = (RP115Group3Epi_2549)vGroup3Epi.get(i);
            Rp115_3 rp = new Rp115_3();
            rp.setValue(
                theRP115Group3Healthy_2549.plan_type,new Integer[]{
                Integer.valueOf(theRP115Group3Healthy_2549.wellbaby)
                ,Integer.valueOf(theRP115Group3Healthy_2549.healthy614)
                ,Integer.valueOf(theRP115Group3Healthy_2549.healthy15Up)
                ,Integer.valueOf(theRP115Group3Nutrition_2549.baby_fail)
                ,Integer.valueOf(theRP115Group3Nutrition_2549.baby_exceed)
                ,Integer.valueOf(theRP115Group3Nutrition_2549.child_fail)
                ,Integer.valueOf(theRP115Group3Nutrition_2549.child_exceed)
                ,Integer.valueOf(theRP115Group3Nutrition_2549.man_fail)
                ,Integer.valueOf(theRP115Group3Nutrition_2549.man_exceed)
                ,Integer.valueOf(theRP115Group3MMR_2549.MMR614)
                ,Integer.valueOf(theRP115Group3DentalProtect_2549.dentalProtect_baby)
                ,Integer.valueOf(theRP115Group3DentalProtect_2549.dentalProtect_child)
                ,Integer.valueOf(theRP115Group3Epi_2549.DPT)
                ,Integer.valueOf(theRP115Group3Epi_2549.BCG)
                ,Integer.valueOf(theRP115Group3Epi_2549.MMR)
                ,Integer.valueOf(theRP115Group3Epi_2549.Hep)});
            ds.add(rp);
        }
        return ds;
    }


    public String plan_type;
                public Integer wellbaby;
                public Integer healthy614;
                public Integer healthy15Up;
                public Integer baby_fail;
                public Integer baby_exceed;
                public Integer child_fail;
                public Integer child_exceed;
                public Integer man_fail;
                public Integer man_exceed;
                public Integer MMR614;
                public Integer dentalProtect_baby;
                public Integer dentalProtect_child;
                public Integer DPT;
                public Integer BCG;
                public Integer MMR;
                public Integer Hep;

    Vector vObject;
    int index = -1;
    public boolean next() throws JRException {
        index++;
        if(index>=vObject.size())
            return false;
        Rp115_3 inc = (Rp115_3)vObject.get(index);
        this.update(inc);
        return true;
    }

    public Object getFieldValue(JRField arg0) throws JRException
    {
        if(arg0.getName().equals("plan_type")) return plan_type;
        if(arg0.getName().equals("wellbaby")) return wellbaby;
        if(arg0.getName().equals("healthy614")) return healthy614;
        if(arg0.getName().equals("healthy15Up")) return healthy15Up;
        if(arg0.getName().equals("baby_fail")) return baby_fail;
        if(arg0.getName().equals("baby_exceed")) return baby_exceed;
        if(arg0.getName().equals("child_fail")) return child_fail;
        if(arg0.getName().equals("child_exceed")) return child_exceed;
        if(arg0.getName().equals("man_fail")) return man_fail;
        if(arg0.getName().equals("man_exceed")) return man_exceed;
        if(arg0.getName().equals("MMR614")) return MMR614;
        if(arg0.getName().equals("dentalProtect_baby")) return dentalProtect_baby;
        if(arg0.getName().equals("dentalProtect_child")) return dentalProtect_child;
        if(arg0.getName().equals("DPT")) return DPT;
        if(arg0.getName().equals("BCG")) return BCG;
        if(arg0.getName().equals("MMR")) return MMR;
        if(arg0.getName().equals("Hep")) return Hep;
        if(arg0.getName().equals("vaccine_dhb")) return new Integer(0);
        if(arg0.getName().equals("vaccine_je")) return new Integer(0);
        if(arg0.getName().equals("vaccine_measles")) return new Integer(0);
        if(arg0.getName().equals("clinic_child_new")) return new Integer(0);
        return "";
    }
    public void add(Rp115_3 ipd){
        if(vObject==null)
            vObject = new Vector();
        vObject.add(ipd);
    }

    private void update(Rp115_3 inc) {
        this.setValue(inc.plan_type, inc.getIntegers());
    }
    public void setValue(String type,Integer[] data){
        plan_type = type;
                wellbaby = data[0];
                healthy614 = data[1];
                healthy15Up = data[2];
                baby_fail = data[3];
                baby_exceed = data[4];
                child_fail = data[5];
                child_exceed = data[6];
                man_fail = data[7];
                man_exceed = data[8];
                MMR614 = data[9];
                dentalProtect_baby = data[10];
                dentalProtect_child = data[11];
                DPT = data[12];
                BCG = data[13];
                MMR = data[14];
                Hep = data[15];
    }

    public Integer[] getIntegers(){
        return new Integer[]{
                wellbaby
                ,healthy614
                ,healthy15Up
                ,baby_fail
                ,baby_exceed
                ,child_fail
                ,child_exceed
                ,man_fail
                ,man_exceed
                ,MMR614
                ,dentalProtect_baby
                ,dentalProtect_child
                ,DPT
                ,BCG
                ,MMR
                ,Hep};
    }
}
