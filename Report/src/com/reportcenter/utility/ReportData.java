/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reportcenter.utility;

import java.sql.ResultSet;

/**
 *
 * @author henbe
 */
public class ReportData {

   public static final int MAX_INCOMPLETE_ROW = 100;
   public static final int MAX_COLUMN = 140;

    public static int VALID_01=1;

    public static int VALID_12=2;
    public static int VALID_07=3;
    public static int VALID_13=4;
    public static int VALID_17=5;
    public static int VALID_15=6;
    public static int VALID_12_89=7;
    public static int VALID_16=8;

    public static int VALID_01_8=9;
    public static int VALID_14=10;
    public static int VALID_16_9=11;
    public static int VALID_ABO=12;
    public static int VALID_02=13;
    public static int VALID_19=14;
    public static int VALID_13_9=15;

    public static int VALID_N13=16;
    public static int VALID_ALL=0;
    public static int VALID_04=17;
    private static int DD_IS_NULL = 0;
    private static int DD_LENGTH_OVER = 1;
    private static int DD_FIX_LENGTH_FAIL = 2;
    private static int DD_NOT_NULL = 3;
    private static int DD_INVALID_CODE = 4;


/**
 *ช่วยบอกได้มั้ยว่าตอนคิวรีกันเครื่องหมาย \t , ไว้เพื่ออะไรกัน ตอนนี้ขอ comment ไว้ก่อนนะ
 */

    public static String getRsString(ResultSet rs, int index)  throws Exception {
         String str = rs.getString(index);
         if(str==null)
             str = "";
         str = str.trim();
         return str;
    }
    public static String getWarning(boolean[] res) {
        String ret = new String();
        if(res[DD_FIX_LENGTH_FAIL])
            ret += " ความยาวไม่ถูกต้อง";
        if(res[DD_INVALID_CODE])
            ret += " ค่าไม่อยู่ในขอบเขต";
        if(res[DD_IS_NULL])
            ret += " มีค่าเป็น NULL";
        if(res[DD_LENGTH_OVER])
            ret += " ความยาวเกินกำหนด";
        if(res[DD_NOT_NULL])
            ret += " ต้องไม่เป็นค่าว่าง";
        return ret;
    }
    public static Double getDouble(String ga) {

        try{
            return Double.valueOf(ga.replaceAll(",",""));
        }
        catch(Exception e){
//            System.out.println(e.getMessage());
            return null;
        }
    }
    /**
     * ตรวจสอบเทียบกับ datadict
     * หากเป็น not_null จะต้องมีต่าตามเงื่อนไขของข้อมูลคือมีความยาวเท่ากับที่กำหนด
     *   หากผิดจากเงื่อนไขจะไม่นำ record นั้นมาแสดง
     * หากเป็น null ได้ก็จะให้ค่าอยู่ในขอบเขต
     *   หากผิดจากเงื่อนไขจะให้ค่าเป็นค่าว่าง
     */
    public static boolean checkDataDict(String value, int length, boolean not_null)
    {
        return checkDataDict(value,length,not_null,not_null,0);
    }
    public static boolean checkDataDict(String value, int length, boolean not_null,boolean fix_length)
    {
        return checkDataDict(value,length,not_null,fix_length,0);
    }
    public static boolean checkDataDict(String value, int length, boolean not_null
            ,boolean fix_length,int mode)
    {
        return checkDataDict(value,length,not_null,fix_length,0,null);
    }
    public static boolean checkDataDict(String value, int length, boolean not_null
            ,boolean fix_length,int mode,boolean[] theRes)
    {
        if(value==null){
            if(theRes!=null)
                theRes[DD_IS_NULL] = true;
            return false;
        }
        if(value.length()>length){
            if(theRes!=null)
            theRes[DD_LENGTH_OVER] = true;
            return false;
        }
        if(fix_length && value.length()!=length && value.length()!=0){
            if(theRes!=null)
            theRes[DD_FIX_LENGTH_FAIL] = true;
            return false;
        }
        if(value.length()==0 && not_null){
            if(theRes!=null)
            theRes[DD_NOT_NULL] = true;
            return false;
        }
        if(length==1 && !isValid1(value,mode) && value.length()==1){
            if(theRes!=null)
            theRes[DD_INVALID_CODE] = true;
            return false;
        }
        return true;
    }
    public static boolean checkDataDictNumber(String pcucode, int i, boolean not_null) {
        try{
            Double.parseDouble(pcucode);
        }
        catch(Exception e){
            return false;
        }
        return checkDataDict(pcucode,i,not_null,false,0);
    }


    /**
     * ตรวจสอบเทียบกับ datadict
     * หากเป็น not_null จะต้องมีต่าตามเงื่อนไขของข้อมูลคือมีความยาวเท่ากับที่กำหนด
     *   หากผิดจากเงื่อนไขจะไม่นำ record นั้นมาแสดง
     * หากเป็น null ได้ก็จะให้ค่าอยู่ในขอบเขต
     *   หากผิดจากเงื่อนไขจะให้ค่าเป็นค่าว่าง
     */
    public static boolean checkDataDict(String value, int length, boolean not_null,boolean fix_length,int mode
            ,boolean over_length)
    {
        if(value==null)
            return false;
        if(value.length()>length && !over_length)
            return false;
        if(fix_length && value.length()!=length && value.length()!=0)
            return false;
        if(value.length()==0 && not_null)
            return false;
        if(length==1 && !isValid1(value,mode) && value.length()==1)
            return false;
        return true;
    }

    public static boolean checkAppend(String field_name,int check_index_count,StringBuffer sb){
        if(check_index_count>0){
            sb.append("\n "+field_name+" missing:     "+check_index_count);
            return true;
        }
        return false;
    }

    public static boolean checkDataDict(String value, int len1, int len2, boolean not_null) {
        return checkDataDictNumber(value,len1+len2+1,not_null);
    }
    /**
     * 151000999 -> 151   00999
     */
    public static String getVN8(String seq,int length) {
        return seq.substring(0,3)+seq.substring(seq.length()-length+3);
    }

    public static boolean checkDataDictDrug(String did, int i, boolean b)
    {
        if(did.length()<i && did.length()>0)
        {
            if(did.indexOf("/")==-1)
                return false;
            String[] dids = did.split("/");
            if(dids.length!=2)
                return false;
            try{
                int year = Integer.parseInt(dids[1]);
                if(year<20)
                    return false;
                if(year>70)
                    return false;
            }catch(Exception e){
                return false;
            }
            return true;
        }
        else
            return checkDataDict(did,i,b,true,0);
    }

/**
 *
    private static int VALID_07=2;
    private static int VALID_13=2;
    private static int VALID_17=2;
    private static int VALID_15=2;
    private static int VALID_1289=2;
    private static int VALID_16=2;
    public static int VALID_01_8;
    public static int VALID_14;
    public static int VALID_16_9;
    public static int VALID_ABO;
    public static int VALID_02;
    public static int VALID_19
 */
    private static boolean isValid1(String value, int mode) {
        if(mode==VALID_01)        {
            if(value.equals("0") || value.equals("1"))          return true;
        }
        else if(mode==VALID_12)   {
//            System.out.println("else if(mode==VALID_12)   '"+value+"'");
            if(value.equals("1") || value.equals("2")){
//                System.out.println("if(value.equals(1) || value.equals(2)){");
                return true;
            }
        }
        else if(mode==VALID_07)       {
            if(value.equals("1") || value.equals("2")
            ||value.equals("3") || value.equals("4")
            ||value.equals("5") || value.equals("6")
            ||value.equals("7") || value.equals("0"))           return true;
        }
        else if(mode==VALID_13)  {
            if(value.equals("1") || value.equals("2")
            ||value.equals("3"))           return true;
        }
        else if(mode==VALID_15){
            if(value.equals("1") || value.equals("2")
            ||value.equals("3") || value.equals("4")
            ||value.equals("5"))           return true;
        }
        else if(mode==VALID_16) {
            if(value.equals("1") || value.equals("2")
            ||value.equals("3") || value.equals("4")
            ||value.equals("5") || value.equals("6"))           return true;
        }
        else if(mode==VALID_17){
            if(value.equals("1") || value.equals("2")
            ||value.equals("3") || value.equals("4")
            ||value.equals("5") || value.equals("6")
            ||value.equals("7"))           return true;
        }
        else if(mode==VALID_12_89)  {
            if(value.equals("1") || value.equals("2")
            ||value.equals("8") || value.equals("9"))           return true;
        }
        else if(mode==VALID_01_8)  {
            if(value.equals("0") || value.equals("1")
            ||value.equals("8"))           return true;
        }
        else if(mode==VALID_14)  {
            if(value.equals("1") || value.equals("2")
            ||value.equals("3") || value.equals("4"))           return true;
        }
        else if(mode==VALID_16_9)  {
            if(value.equals("1") || value.equals("2")
            ||value.equals("3") || value.equals("4")
            ||value.equals("5") || value.equals("6")
            || value.equals("9"))           return true;
        }
        else if(mode==VALID_ABO)  {
            if(value.equals("A") || value.equals("B")
            ||value.equals("AB") || value.equals("O"))           return true;
        }
        else if(mode==VALID_02)  {
            if(value.equals("1") || value.equals("2")
            ||value.equals("0"))           return true;
        }
        else if(mode==VALID_19)  {
            if(value.equals("1") || value.equals("2")
            ||value.equals("3") || value.equals("4")
            ||value.equals("5") || value.equals("6")
            ||value.equals("7") ||value.equals("8")
            || value.equals("9"))           return true;
        }
        else if(mode==VALID_13_9)  {
            if(value.equals("1") || value.equals("2")
            ||value.equals("3") || value.equals("9"))           return true;
        }
        else if(mode==VALID_N13)  {
            if(value.equals("1") || value.equals("2")
            ||value.equals("3") || value.equals("N"))           return true;
        }
        else if(mode==VALID_04)        {
            if(value.equals("0") || value.equals("1")
            || value.equals("2") || value.equals("3")
            || value.equals("4"))          return true;
        }
        else
            return true;
        return false;
    }

    public static boolean checkDataDictDate(String datein, int i, boolean b)
    {
        if(datein.length()!=0 && !datein.startsWith("2"))
            return false;
        return checkDataDict(datein,i,b,false,0);
    }

}
