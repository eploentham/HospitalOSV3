/*
 * CommonData.java
 *
 * Created on 14 สิงหาคม 2547, 19:18 น.
 */

package com.hospital_os.utility;
import java.util.*;
/**
 *
 * @author  Administrator
 */
public class CommonData extends Vector{
        int column_sort;
        int old_index;
        public CommonData(int col){
            column_sort = col;
        }
        public String toString(){
            return String.valueOf(this.get(column_sort));
        }
        public void setOldIndex(int old){
            old_index = old;
        }
        public int getOldIndex(){
            return old_index;
        }
}
