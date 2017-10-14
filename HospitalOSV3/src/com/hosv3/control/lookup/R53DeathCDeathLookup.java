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
public class R53DeathCDeathLookup {

    public static String[][] icd_cdeath = new String[][]{
{"002","A00"}
,{"003","A09"}
,{"004","A01-A08"}
,{"005","A15-A16"}
,{"006","A17-A19"}
,{"007","A20"}
,{"008","A33-A35"}
,{"009","A36"}
,{"010","A37"}
,{"011","A39"}
,{"012","A40-A41"}
,{"013","A50-A64"}
,{"014","A80"}
,{"015","A82"}
,{"016","A95"}
,{"017","A90-A94"}
,{"017","A96-A99"}
,{"018","B05"}
,{"019","B15-B19"}
,{"020","B20-B24"}
,{"021","B50-B54"}
,{"022","B55"}
,{"023","B56-B57"}
,{"024","B65"}
,{"025","A65-A79"}
,{"025","A81"}
,{"025","A83-A89"}
,{"025","B00-B04"}
,{"025","B06-B09"}
,{"025","B25-B49"}
,{"025","B58-B64"}
,{"025","B66-B94"}
,{"025","B99"}
,{"027","C00-C14"}
,{"028","C15"}
,{"029","C16"}
,{"030","C18-C21"}
,{"031","C22"}
,{"032","C25"}
,{"033","C32"}
,{"034","C33-C34"}
,{"035","C43"}
,{"036","C50"}
,{"036","C50"}
,{"037","C53"}
,{"038","C54-C55"}
,{"039","C56"}
,{"040","C61"}
,{"041","C67"}
,{"042","C70-C72"}
,{"043","C82-C85"}
,{"044","C90"}
,{"045","C91-C95"}
,{"046","C17"}
,{"046","C23-C24"}
,{"046","C26-C31"}
,{"046","C37-C41"}
,{"046","C44-C49"}
,{"046","C51-C52"}
,{"046","C57-C60"}
,{"046","C62-C66C68-C69"}
,{"046","C73-C81"}
,{"046","C88"}
,{"046","C96-C97"}
,{"047","D00-D48"}
,{"048","D50-D89"}
,{"049","D50-D64"}
,{"050","D65-D89"}
,{"052","E10-E14"}
,{"053","E40-E46"}
,{"054","E00-E07"}
,{"054","E15-E34"}
,{"054","E50-E88"}
,{"055","F01-F99"}
,{"056","F10-F19"}
,{"057","F01-F09"}
,{"057","F20-F99"}
,{"059","G00"}
,{"060","G03"}
,{"061","G30"}
,{"061","G04-G25"}
,{"061","G31-G98"}
,{"062","H00-H57"}
,{"063","H60-H93"}
,{"065","I00-I09"}
,{"066","I10-I15"}
,{"067","I20-I25"}
,{"068","I26-I52"}
,{"069","I60-I69"}
,{"070","I70"}
,{"071","I71-I99"}
,{"073","J10-J11"}
,{"074","J12-J18"}
,{"075","J20-J22"}
,{"076","J40-J47"}
,{"077","J00-J06"}
,{"077","J30-J39"}
,{"077","J60-J98"}
,{"079","K25-K27"}
,{"080","K70-K76"}
,{"081","K00-K22"}
,{"081","K28-K66"}
,{"081","K80-K92"}
,{"082","L00-L98"}
,{"083","M00-M99"}
,{"085","N00-N15"}
,{"086","N17-N98"}
,{"088","O00-O08"}
,{"089","O10-O92"}
,{"090","O98-O99"}
,{"091","O95-O97"}
,{"092","P00-P96"}
,{"093","Q00-Q99"}
,{"094","R00-R99"}
,{"095","V01-Y89"}
,{"096","V01-V99"}
,{"097","W00-W19"}
,{"098","W65-W74"}
,{"099","X00-X09"}
,{"100","X40-X49"}
,{"101","X60-X84"}
,{"102","X85-Y09"}
,{"103","W20-W64"}
,{"103","W75-W99"}
,{"103","X10-X39"}
,{"103","X50-X59"}
,{"103","Y10-Y89"}
,{"001","A00-B99"}
,{"026","C00-D48"}
,{"051","E00-E88"}
,{"058","G00-G98"}
,{"064","I00-I99"}
,{"072","J00-J98"}
,{"078","K00-K92"}
,{"084","N00-N99"}
,{"087","O00-O99"}

    };
    /** Creates a new instance of AccidentTypeLookup */
    public R53DeathCDeathLookup(ConnectionInf con){
    
    } 
    public static String listData(String icd) {
        icd = icd.toUpperCase();
        for(int i=0;i<icd_cdeath.length;i++){
//            System.out.println(icd_cdeath[i][1]);
            if(icd_cdeath[i][1].indexOf("-")!=-1){
                String[] split = icd_cdeath[i][1].split("-");
                if(split[0].compareTo(icd)<0 && split[1].compareTo(icd)>0)
                    return icd_cdeath[i][0];
            }
            if(icd_cdeath[i][1].equals(icd))
                return icd_cdeath[i][0];
        }
        return null;
    }
    public static void main(String[] args){
        System.out.println(listData("a21"));
//        System.out.println(icd_cdeath[0][1]);
    }
}
