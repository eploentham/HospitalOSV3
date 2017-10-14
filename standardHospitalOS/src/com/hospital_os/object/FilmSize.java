package com.hospital_os.object;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.usecase.connection.Persistent;

public class FilmSize extends Persistent  implements CommonInf
{
        public String filmsize = "";
        public String description = "";
        public String price = "0";
   /**

    * @roseuid 3F658BBB036E

    */
   public FilmSize() 
   {

   }
    public String getCode(){return getObjectId();}
    public String getName(){return description;}
    public String getPrice(){return price;}
    public String toString(){return description;}    

}

