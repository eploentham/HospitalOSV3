/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hospital_os.objdb;
import com.hospital_os.object.OrderSpecified;
import com.hospital_os.object.X39Persistent;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.utility.ComboFix;
import java.sql.ResultSet;
import java.util.Vector;
/**
 *
 * @author LionHearth
 */
public class OrderSpecifiedDB  extends X39DB{
protected OrderSpecified dbObj;
    public OrderSpecifiedDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = OrderSpecified.initConfig();
        dbObjX = dbObj;
    }

    public Vector<X39Persistent> selectAll() throws Exception
    {
        String sql = "select * from " + dbObj.table;
        return eQueryX(sql,null);
    }

    public Vector<ComboFix> selectAllComboxFix() throws Exception
    {
        String sql = "select * from " + dbObj.table ;
        Vector v = veQuery(sql);
        if(v.size() == 0)
            return null;
        else
            return v;
    }

    public Vector<ComboFix> veQuery(String sql) throws Exception
    {
        ComboFix p;
        p = new ComboFix();
        p.code = "";
        p.name = "ไม่ระบุ";
        Vector<ComboFix> list = new Vector<ComboFix>();
        list.add(p);
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
