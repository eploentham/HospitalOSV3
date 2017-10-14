//Source file: F:\\HospitalOSUsecase\\com\\hospitalos\\usecase\\deleteItemRep.java
package com.hosv3.usecase.setup;
import com.hospital_os.object.*;
import java.util.*;
/**
UC-Ad027 ลบรายการ item
 */
public interface ManageCategoryGroupItemReq 
{
   
   /**
   @roseuid 3F8025FE015E
    */
 public int deleteCategoryGroupItem(CategoryGroupItem category);
    public int saveCategoryGroupItem(CategoryGroupItem categogy);
    public int editCategoryGroupItem(CategoryGroupItem category);
    public CategoryGroupItem listCategoryGroupItemByCode(String code);
    public Vector listCategoryGroupItemByName(String pk, String active);
    public CategoryGroupItem listCategoryGroupItemByPk(String pk);
}
