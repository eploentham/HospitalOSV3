/*
 * AccidentTypeLookup.java
 *
 * Created on 2 ÁÔ¶Ø¹ÒÂ¹ 2549, 13:28 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.control.lookup;
import com.hospital_os.usecase.connection.*;

/**
 *
 * @not deprecated because use henbe package
 * @author Padungrat(tong)
 * A00-B99
 */
public class R53ChronicLookup {

    public static String[][] icd_chronic = new String[][]{
{"1","I10-I15"}
,{"2","J45"}
,{"3","I20-I25"}
,{"4","C00-D48"}
,{"5","D50-D64"}
,{"6","F30-F39"}
,{"7","I60-I69"}
,{"8","G80-G83"}
,{"9","N17-N19"}
,{"10","E10-E14"}
,{"11","J42"}
,{"12","J43"}
,{"13","I05-I09"}
,{"13","I20-I25"}
,{"13","I26-I28"}
,{"13","I30-I52"}
,{"14","F10.0"}
,{"14","F10.2"}
,{"15","J44"}
,{"16","A15-A19"}


    };
    /** Creates a new instance of AccidentTypeLookup */
    public R53ChronicLookup(ConnectionInf con){
    
    } 
    public static String listData(String icd) {
        icd = icd.toUpperCase();
        for(int i=0;i<icd_chronic.length;i++){
//            System.out.println(icd_chronic[i][1]);
            if(icd_chronic[i][1].indexOf("-")!=-1){
                String[] split = icd_chronic[i][1].split("-");
                if(split[0].compareTo(icd)<0 && split[1].compareTo(icd)>0)
                    return icd_chronic[i][0];
            }
            if(icd_chronic[i][1].equals(icd))
                return icd_chronic[i][0];
        }
        return null;
    }
    public static void main(String[] args){
        System.out.println(listData("i21"));
//        System.out.println(icd_chronic[0][1]);
    }
}
