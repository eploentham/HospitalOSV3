package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
import java.util.Vector;
/**
 * Modify sumo 10/08/2549
 *
 **/
public class GuideAfterDxTransaction extends Persistent 
{
        public String visit_id = "";
        public String guide = "";
        public String health_head  ="";
        
   /**
    * @roseuid 3F658BBB036E
    */
   public GuideAfterDxTransaction() 
   {   
   }
   public String toString(){
       StringBuffer sb = new StringBuffer();
       if(health_head.equals(""))
            return sb.append(guide).toString();
       else
            return sb.append(health_head).append(": ").append(guide).toString();
   }
   public static String toString(Vector vHealthEdu){
       
        StringBuffer health_edu = new StringBuffer();
        if(vHealthEdu==null)
            return health_edu.toString();
        
        for(int i=0,size=vHealthEdu.size();i<size;i++)
        {
            GuideAfterDxTransaction gu = (GuideAfterDxTransaction)vHealthEdu.get(i);
            if(health_edu.toString().equals("")) 
                health_edu.append(gu.toString());
            else 
                health_edu.append("\n").append(gu.toString());
        } 
        return health_edu.toString();
   }

}
