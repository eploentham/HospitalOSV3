package com.hospital_os.object;
public class Item  extends X39Persistent
{
    final private String idtable = "";
    public String item_id ="";
    public String common_name="";
    public String trade_name="";
    public String nick_name="";
    public String item_group_code_category="";
    public String item_group_code_billing="";
    public String printable="";
    public String active="";
    public String secret = "0";
    private String item_group_code_category_name="";
    public String item_16_group ="";
    public String unit_pack53 = "";
    public String rp_lab_type = "";
    /**
    * ในกรณีที่ไอเท็มเป็นประเภทบริการสามารถระบุ df แพทย์ หรือผู้ปฏิบัติ ได้
    */
    public String specified = "";
   /**
    * @roseuid 3F658BBB036E
    */
   public Item() 
   {
    
   }
   public static Item initConfig() {
        Item dbObj = new Item();
        dbObj.table="b_item";
        dbObj.setObjectId("b_item_id");
        dbObj.pk_field="b_item_id";
        dbObj.item_id   ="item_number";
        dbObj.common_name   ="item_common_name";
        dbObj.trade_name   ="item_trade_name";
        dbObj.nick_name   ="item_nick_name";
        dbObj.active   ="item_active";
        dbObj.item_group_code_category   ="b_item_subgroup_id";
        dbObj.item_group_code_billing   ="b_item_billing_subgroup_id";
        dbObj.printable="item_printable";
        dbObj.secret = "item_secret";
        dbObj.item_16_group = "b_item_16_group_id";
        dbObj.unit_pack53 = "item_unit_packing_qty";
        dbObj.rp_lab_type = "f_item_lab_type_id";
        dbObj.specified = "item_specified";
        return dbObj;
    }
   public void setItemGroupCodeCategoryName(String name)
   {
       item_group_code_category_name = name;
   }
   public String getItemGroupCodeCategoryName()
   {
       return item_group_code_category_name;
   }

    public String getCode()
    {
        return this.getObjectId();
    }

    public String getName()
    {
         return this.common_name;
    }
    public String toString(){
        return getName();
    }
   public void setSecret(boolean b){
       if(b)          secret = "1";
       else           secret = "0";
   }
   public boolean getSecret(){
       return secret.equals("1");
   }
   public void setActive(boolean b){
       if(b)          active = "1";
       else           active = "0";
   }
   public boolean getActive(){
       return active.equals("1");
   }

    @Override
    public X39Persistent getInstant(String[] strd) {
        Item item = new Item();
        item.setStringArray(strd);
        return item;
    }

    @Override
    public String[] getStringArray() {

        return new String[]{
                    getObjectId(), item_id,common_name,trade_name,nick_name,active,item_group_code_category,item_group_code_billing,printable,secret,item_16_group,unit_pack53,rp_lab_type,specified
                };
    }

    @Override
    public void setStringArray(String[] array) {
        setObjectId(array[0]);
        item_id = array[1];
        common_name = array[2];
        trade_name = array[3];
        nick_name = array[4];
        active = array[5];
        item_group_code_category = array[6];
        item_group_code_billing = array[7];
        printable = array[8];
        secret = array[9];
        item_16_group = array[10];
        unit_pack53 = array[11];
        rp_lab_type = array[12];
        specified = array[13];
        // henbe wait for update database
//        primary_report = array[14];
    }

    @Override
    public String getIdTable() {
        return this.idtable;
    }
}
