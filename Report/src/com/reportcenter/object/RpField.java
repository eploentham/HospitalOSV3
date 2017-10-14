/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reportcenter.object;

/**
 *]]]]]]еееее
 * @author henbe
 */
public class RpField {

    public String header;
    public String warning;
    public int length;
    public boolean not_null;
    public boolean fix_length;
    public byte type;
    public int valid;
    public int decimal=0;

    public static RpField initData(String h, String w, int l, boolean nn, boolean fl, byte t) {
        RpField rp = new RpField();
        rp.header = h.trim();
        rp.warning = w.trim();
        rp.length = l;
        rp.not_null = nn;
        rp.fix_length = fl;
        rp.type = t;
        rp.valid = 0;
        return rp;
    }

    public static RpField initData(String h, String w, int l, boolean nn, boolean fl,int vl, byte t) {
        RpField rp = new RpField();
        rp.header = h.trim();
        rp.warning = w.trim();
        rp.length = l;
        rp.not_null = nn;
        rp.fix_length = fl;
        rp.type = t;
        rp.valid = vl;
        return rp;
    }
    public static RpField initData(String h, String w, int l,int d, boolean nn, boolean fl,int vl, byte t) {
        RpField rp = new RpField();
        rp.header = h.trim();
        rp.warning = w.trim();
        rp.length = l;
        rp.decimal = d;
        rp.not_null = nn;
        rp.fix_length = fl;
        rp.type = t;
        rp.valid = vl;
        return rp;
    }
}
