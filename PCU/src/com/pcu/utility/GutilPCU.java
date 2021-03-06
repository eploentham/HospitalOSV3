/*
 * GutilPCU.java
 *
 * Created on 6 �Զع�¹ 2548, 13:31 �.
 */

package com.pcu.utility;

import com.hospital_os.usecase.connection.CommonInf;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JTabbedPane;
import com.hosv3.utility.DateUtil;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.DateComboBoxOld1;
import com.hospital_os.utility.Gutil;
import com.hosv3.gui.component.HJTableSort;
import com.hosv3.utility.GuiLang;
import java.util.*;
/**
 *
 * @author amp
 */
public class GutilPCU extends GuiLang  {
//
//    private static Properties data = Language.getProperties("pcu",Config.LANGUAGE_FILE,Config.LANGUAGE_PATH);
//    static String language;
//    private static String old_str;
//    /** Creates a new instance of GutilPCU */
    static double nutrtion_boy[][] = new double[][]{
        {2.5, 2.7, 3.5, 3.7}// ���� 0 ��͹
        ,{2.8, 3.2, 4.3, 4.9}// ���� 1 ��͹
        ,{3.3, 3.6, 5, 5.8}// ���� 2 ��͹
        ,{3.6, 4.1, 5.7, 6.5}// ���� 3 ��͹
        ,{4, 4.5, 6.4, 7.2}// ���� 4 ��͹
        ,{4.3, 5, 7, 7.8}// ���� 5 ��͹
        ,{4.7, 5.4, 7.5, 8.4}// ���� 6 ��͹
        ,{5, 5.7, 8, 8.9}// ���� 7 ��͹
        ,{5.3, 6.2, 8.5, 9.4}// ���� 8 ��͹
        ,{5.6, 6.5, 8.9, 9.8}// ���� 9 ��͹
        ,{6, 6.8, 9.4, 10.3}// ���� 10 ��͹
        ,{6.3, 7.2, 9.7, 10.6}// ���� 11 ��͹
        ,{6.5, 7.5, 10, 11}// ���� 12 ��͹
        ,{6.8, 7.8, 10.4, 11.4}// ���� 13 ��͹
        ,{7, 8.1, 10.7, 11.7}// ���� 14 ��͹
        ,{7.3, 8.4, 11, 12}// ���� 15 ��͹
        ,{7.5, 8.7, 11.4, 12.4}// ���� 16 ��͹
        ,{7.7, 8.9, 11.6, 12.6}// ���� 17 ��͹
        ,{8, 9.2, 12, 12.8}// ���� 18 ��͹
        ,{8.2, 9.4, 12.3, 13.2}// ���� 19 ��͹
        ,{8.4, 9.6, 12.5, 13.5}// ���� 20 ��͹
        ,{8.6, 9.8, 12.8, 13.8}// ���� 21 ��͹
        ,{8.8, 10, 13, 14}// ���� 22 ��͹
        ,{9, 10.3, 13.4, 14.4}// ���� 23 ��͹
        ,{9.2, 10.4, 13.6, 14.6}// ���� 24 ��͹
        ,{9.3, 10.6, 13.9, 14.8}// ���� 25 ��͹
        ,{9.5, 10.7, 14.2, 15.2}// ���� 26 ��͹
        ,{9.7, 10.8, 14.4, 15.5}// ���� 27 ��͹
        ,{9.8, 11, 14.5, 15.8}// ���� 28 ��͹
        ,{10, 11.3, 14.8, 16}// ���� 29 ��͹
        ,{10.1, 11.4, 15, 16.3}// ���� 30 ��͹
        ,{10.3, 11.5, 15.3, 16.5}// ���� 31 ��͹
        ,{10.5, 11.7, 15.5, 16.8}// ���� 32 ��͹
        ,{10.6, 11.8, 15.8, 17.2}// ���� 33 ��͹
        ,{10.7, 12, 16, 17.5}// ���� 34 ��͹
        ,{10.8, 12.2, 16.3, 17.8}// ���� 35 ��͹
        ,{11, 12.4, 16.5, 18}// ���� 36 ��͹
        ,{11, 12.5, 16.7, 18.3}// ���� 37 ��͹
        ,{11.3, 12.6, 17, 18.5}// ���� 38 ��͹
        ,{11.4, 12.8, 17.3, 18.8}// ���� 39 ��͹
        ,{11.5, 12.9, 17.6, 19.2}// ���� 40 ��͹
        ,{11.7, 13, 17.8, 19.4}// ���� 41 ��͹
        ,{11.8, 13.2, 18, 19.7}// ���� 42 ��͹
        ,{12, 13.3, 18.3, 20}// ���� 43 ��͹
        ,{12.1, 13.4, 18.5, 20.3}// ���� 44 ��͹
        ,{12.3, 13.5, 18.8, 20.5}// ���� 45 ��͹
        ,{12.4, 13.7, 19, 20.7}// ���� 46 ��͹
        ,{12.5, 13.8, 19.4, 21}// ���� 47 ��͹
        ,{12.6, 13.9, 19.6, 21.4}// ���� 48 ��͹
        ,{12.7, 14, 19.8, 21.6}// ���� 49 ��͹
        ,{12.8, 14.2, 20.1, 21.8}// ���� 50 ��͹
        ,{13, 14.3, 20.3, 22.1}// ���� 51 ��͹
        ,{13.1, 14.4, 20.5, 22.4}// ���� 52 ��͹
        ,{13.2, 14.5, 20.8, 22.7}// ���� 53 ��͹
        ,{13.3, 14.7, 21, 22.9}// ���� 54 ��͹
        ,{13.4, 14.8, 21.4, 23.2}// ���� 55 ��͹
        ,{13.5, 14.9, 21.6, 23.4}// ���� 56 ��͹
        ,{13.6, 15, 21.8, 23.7}// ���� 57 ��͹
        ,{13.8, 15.2, 22, 23.8}// ���� 58 ��͹
        ,{13.9, 15.3, 22.3, 24}// ���� 59 ��͹
        ,{13.9, 15.4, 22.5, 24.3}// ���� 60 ��͹
        ,{14, 15.5, 22.8, 24.5}// ���� 61 ��͹
        ,{14.2, 15.6, 23, 24.5}// ���� 62 ��͹
        ,{14.3, 15.7, 23.3, 25}// ���� 63 ��͹
        ,{14.4, 15.8, 23.5, 25.3}// ���� 64 ��͹
        ,{14.5, 15.9, 23.7, 25.5}// ���� 65 ��͹
        ,{14.6, 16, 23.9, 25.7}// ���� 66 ��͹
        ,{14.8, 16.2, 24.2, 26}// ���� 67 ��͹
        ,{15, 16.3, 24.4, 26.3}// ���� 68 ��͹
        ,{15.1, 16.4, 24.7, 26.5}// ���� 69 ��͹
        ,{15.2, 16.4, 24.9, 26.7}// ���� 70 ��͹
        ,{15.4, 16.5, 25.1, 27}// ���� 71 ��͹
        ,{15.5, 16.6, 25.4, 27.2}// ���� 72 ��͹
    };
    static double nutrition_girl[][] = new double[][]{
        {2.4, 2.7, 3.5, 3.7}// ���� 0 ��͹
        ,{2.8, 3.1, 4.3, 5}// ���� 1 ��͹
        ,{3.2, 3.5, 5.1, 5.9}// ���� 2 ��͹
        ,{3.5, 3.9, 5.8, 6.6}// ���� 3 ��͹
        ,{3.9, 4.3, 6.4, 7.3}// ���� 4 ��͹
        ,{4.3, 4.7, 7, 7.8}// ���� 5 ��͹
        ,{4.6, 5, 7.5, 8.4}// ���� 6 ��͹
        ,{5, 5.4, 8, 8.4}// ���� 7 ��͹
        ,{5.3, 5.8, 8.5, 9.4}// ���� 8 ��͹
        ,{5.6, 6.1, 9, 9.8}// ���� 9 ��͹
        ,{6, 6.4, 9.3, 10.2}// ���� 10 ��͹
        ,{6.3, 6.8, 9.7, 10.6}// ���� 11 ��͹
        ,{6.5, 7.1, 10, 11}// ���� 12 ��͹
        ,{6.8, 7.4, 10.4, 11.4}// ���� 13 ��͹
        ,{7, 7.7, 10.7, 11.6}// ���� 14 ��͹
        ,{7.3, 8, 11, 12}// ���� 15 ��͹
        ,{7.5, 8.3, 11.3, 12.2}// ���� 16 ��͹
        ,{7.8, 8.5, 11.5, 12.5}// ���� 17 ��͹
        ,{8, 8.8, 11.9, 12.8}// ���� 18 ��͹
        ,{8.2, 9, 12.2, 13}// ���� 19 ��͹
        ,{8.4, 9.3, 12.5, 13.3}// ���� 20 ��͹
        ,{8.6, 9.5, 12.7, 13.6}// ���� 21 ��͹
        ,{8.8, 9.7, 13, 13.8}// ���� 22 ��͹
        ,{9, 9.9, 13.3, 14.1}// ���� 23 ��͹
        ,{9.2, 10, 13.5, 14.4}// ���� 24 ��͹
        ,{9.4, 10.2, 13.8, 14.7}// ���� 25 ��͹
        ,{9.5, 10.4, 14, 15}// ���� 26 ��͹
        ,{9.6, 10.5, 14.3, 15.2}// ���� 27 ��͹
        ,{9.8, 10.7, 14.5, 15.5}// ���� 28 ��͹
        ,{10, 10.8, 14.8, 15.8}// ���� 29 ��͹
        ,{10.1, 11, 15, 16}// ���� 30 ��͹
        ,{10.3, 11.2, 15.2, 16.3}// ���� 31 ��͹
        ,{10.4, 11.4, 15.4, 16.5}// ���� 32 ��͹
        ,{10.5, 11.5, 15.6, 16.8}// ���� 33 ��͹
        ,{10.7, 11.6, 15.9, 17.1}// ���� 34 ��͹
        ,{10.9, 11.8, 16.1, 17.4}// ���� 35 ��͹
        ,{11, 11.9, 16.4, 17.7}// ���� 36 ��͹
        ,{11.2, 12, 16.5, 17.8}// ���� 37 ��͹
        ,{11.3, 12.2, 16.7, 18.1}// ���� 38 ��͹
        ,{11.4, 12.4, 17, 18.4}// ���� 39 ��͹
        ,{11.5, 12.5, 17.2, 18.7}// ���� 40 ��͹
        ,{11.7, 12.6, 17.5, 19}// ���� 41 ��͹
        ,{11.9, 12.7, 17.7, 19.3}// ���� 42 ��͹
        ,{12, 12.8, 18, 19.5}// ���� 43 ��͹
        ,{12.1, 13, 18.3, 19.8}// ���� 44 ��͹
        ,{12.3, 13.1, 18.5, 20}// ���� 45 ��͹
        ,{12.4, 13.3, 18.7, 20.2}// ���� 46 ��͹
        ,{12.5, 13.4, 19, 20.5}// ���� 47 ��͹
        ,{12.6, 13.5, 19.2, 20.8}// ���� 48 ��͹
        ,{12.7, 13.7, 19.5, 21}// ���� 49 ��͹
        ,{12.9, 13.8, 19.8, 21.3}// ���� 50 ��͹
        ,{13, 13.9, 19.9, 21.5}// ���� 51 ��͹
        ,{13.1, 14, 20.2, 21.7}// ���� 52 ��͹
        ,{13.2, 14.2, 20.4, 22}// ���� 53 ��͹
        ,{13.3, 14.3, 20.6, 22.2}// ���� 54 ��͹
        ,{13.4, 14.4, 20.9, 22.5}// ���� 55 ��͹
        ,{13.5, 14.5, 21.1, 22.7}// ���� 56 ��͹
        ,{13.6, 14.6, 21.4, 22.9}// ���� 57 ��͹
        ,{13.7, 14.8, 21.5, 23.1}// ���� 58 ��͹
        ,{13.8, 14.9, 21.8, 23.4}// ���� 59 ��͹
        ,{13.9, 15, 22, 23.6}// ���� 60 ��͹
        ,{14, 15, 22.3, 23.8}// ���� 61 ��͹
        ,{14.1, 15.1, 22.5, 24.1}// ���� 62 ��͹
        ,{14.2, 15.3, 22.7, 24.4}// ���� 63 ��͹
        ,{14.3, 15.4, 22.9, 24.6}// ���� 64 ��͹
        ,{14.4, 15.5, 23.2, 24.8}// ���� 65 ��͹
        ,{14.5, 15.6, 23.4, 25}// ���� 66 ��͹
        ,{14.6, 15.7, 23.6, 25.2}// ���� 67 ��͹
        ,{14.7, 15.8, 23.8, 25.4}// ���� 68 ��͹
        ,{14.8, 16, 24.3, 26}// ���� 69 ��͹
        ,{14.8, 16, 24.3, 26}// ���� 70 ��͹
        ,{14.9, 16.1, 24.5, 26.3}// ���� 71 ��͹
        ,{15, 16.2, 24.8, 26.5}// ���� 72 ��͹
    };
    static double height_girl[][] = new double[][]{
        {46, 49, 53, 56}// ���� 0 ��͹
        ,{51, 53, 54, 62}// ���� 1 ��͹
        ,{54, 56, 63, 66}// ���� 2 ��͹
        ,{57, 58.5, 66, 69}// ���� 3 ��͹
        ,{59, 61, 68, 71}// ���� 4 ��͹
        ,{61, 62.5, 70.5, 73.5}// ���� 5 ��͹
        ,{62, 64, 72, 76}// ���� 6 ��͹
        ,{63.5, 65.5, 74.5, 78}// ���� 7 ��͹
        ,{65, 67, 76, 79}// ���� 8 ��͹
        ,{66, 68, 77, 80.5}// ���� 9 ��͹
        ,{67, 69, 79.5, 82}// ���� 10 ��͹
        ,{68, 70, 81, 83}// ���� 11 ��͹
        ,{69, 71, 82, 84}// ���� 12 ��͹
        ,{69.5, 71.5, 82.5, 84.5}// ���� 13 ��͹
        ,{70, 72, 83, 85}// ���� 14 ��͹
        ,{71, 73, 84.5, 86}// ���� 15 ��͹
        ,{71.7, 74, 85, 87}// ���� 16 ��͹
        ,{72.5, 74.5, 86, 88}// ���� 17 ��͹
        ,{73, 75.5, 86.5, 88.5}// ���� 18 ��͹
        ,{74, 76.5, 87.5, 89.5}// ���� 19 ��͹
        ,{74.5, 77, 88, 90}// ���� 20 ��͹
        ,{75.5, 78, 89, 91}// ���� 21 ��͹
        ,{76, 78.5, 89.5, 92}// ���� 22 ��͹
        ,{76.5, 80.5, 90, 92.5}// ���� 23 ��͹
        ,{78, 81, 91, 93}// ���� 24 ��͹
        ,{79, 82, 92, 94}// ���� 25 ��͹
        ,{80, 82.5, 92.5, 95}// ���� 26 ��͹
        ,{81, 83.5, 93, 95.5}// ���� 27 ��͹
        ,{81.5, 84, 93.5, 96}// ���� 28 ��͹
        ,{82, 85, 94, 97}// ���� 29 ��͹
        ,{83, 85.5, 95, 98}// ���� 30 ��͹
        ,{83.5, 86, 95.5, 98.5}// ���� 31 ��͹
        ,{84, 87, 96, 99}// ���� 32 ��͹
        ,{85, 88, 97, 100}// ���� 33 ��͹
        ,{86, 88.5, 97.5, 100.5}// ���� 34 ��͹
        ,{86.5, 89, 98, 101}// ���� 35 ��͹
        ,{87, 90, 99, 102}// ���� 36 ��͹
        ,{87.5, 90.5, 99.5, 102.5}// ���� 37 ��͹
        ,{88, 91, 100, 103}// ���� 38 ��͹
        ,{89, 91.5, 101, 104}// ���� 39 ��͹
        ,{89.5, 92, 101.5, 104.5}// ���� 40 ��͹
        ,{90, 93, 102, 105}// ���� 41 ��͹
        ,{91, 93.5, 102.5, 106}// ���� 42 ��͹
        ,{91.5, 94, 103, 106.5}// ���� 43 ��͹
        ,{92, 94.5, 104, 107}// ���� 44 ��͹
        ,{92.5, 95, 104.5, 107.5}// ���� 45 ��͹
        ,{93, 96, 105, 108}// ���� 46 ��͹
        ,{94, 96.5, 106, 109}// ���� 47 ��͹
        ,{94.5, 97, 106.5, 109.5}// ���� 48 ��͹
        ,{95, 97.5, 107, 110}// ���� 49 ��͹
        ,{95.5, 98, 107.5, 111}// ���� 50 ��͹
        ,{96, 98.5, 108, 111.2}// ���� 51 ��͹
        ,{96.5, 99, 109, 112}// ���� 52 ��͹
        ,{97, 99.5, 109.5, 112.5}// ���� 53 ��͹
        ,{98, 100, 110, 113}// ���� 54 ��͹
        ,{98.5, 100.5, 111, 114}// ���� 55 ��͹
        ,{98.8, 101, 11.5, 114.5}// ���� 56 ��͹
        ,{99, 101.5, 112, 115}// ���� 57 ��͹
        ,{100, 102, 112.5, 115.5}// ���� 58 ��͹
        ,{100.5, 102.5, 113, 116}// ���� 59 ��͹
        ,{100.7, 103, 114, 116.5}// ���� 60 ��͹
        ,{101, 103.2, 114.5, 117}// ���� 61 ��͹
        ,{101.5, 103.5, 115, 117.5}// ���� 62 ��͹
        ,{102, 104, 116, 118}// ���� 63 ��͹
        ,{102.5, 104.5, 116.5, 119}// ���� 64 ��͹
        ,{103, 105, 117, 119.5}// ���� 65 ��͹
        ,{103.2, 105.2, 117.5, 120}// ���� 66 ��͹
        ,{103.5, 105.5, 118, 120.5}// ���� 67 ��͹
        ,{104, 106, 118.5, 121}// ���� 68 ��͹
        ,{104.2, 106.2, 119, 121.5}// ���� 69 ��͹
        ,{104.5, 106.5, 120, 122}// ���� 70 ��͹
        ,{105, 107, 120.5, 122.5}// ���� 71 ��͹
        ,{105.5, 107.2, 121, 123}// ���� 72 ��͹
    };
    static double height_boy[][] = new double[][]{
        {46.5, 47, 52, 53}// ���� 0 ��͹
        ,{48, 48.5, 53, 54.5}// ���� 1 ��͹
        ,{49.5, 50, 55, 56}// ���� 2 ��͹
        ,{51, 51.5, 56.5, 58.5}// ���� 3 ��͹
        ,{52.5, 53, 58, 59}// ���� 4 ��͹
        ,{54, 54.5, 59.5, 60.5}// ���� 5 ��͹
        ,{55, 55.5, 61, 62}// ���� 6 ��͹
        ,{56.5, 57, 62, 63}// ���� 7 ��͹
        ,{58, 58.5, 63.5, 64.5}// ���� 8 ��͹
        ,{59.5, 60, 65, 66}// ���� 9 ��͹
        ,{60.5, 61, 66, 67}// ���� 10 ��͹
        ,{61, 61.5, 67.5, 68.5}// ���� 11 ��͹
        ,{63, 63.5, 69, 70}// ���� 12 ��͹
        ,{64, 64.5, 70, 71}// ���� 13 ��͹
        ,{65, 65.5, 71, 72}// ���� 14 ��͹
        ,{66.5, 67, 72.5, 73.5}// ���� 15 ��͹
        ,{67.5, 68, 73.5, 74.5}// ���� 16 ��͹
        ,{68.5, 69, 75, 76}// ���� 17 ��͹
        ,{69.5, 70, 76, 77}// ���� 18 ��͹
        ,{70.5, 71.5, 77, 78}// ���� 19 ��͹
        ,{71.5, 72.5, 77.5, 78.5}// ���� 20 ��͹
        ,{72.5, 73.5, 80, 81.5}// ���� 21 ��͹
        ,{73.5, 74.5, 81.5, 83}// ���� 22 ��͹
        ,{74.5, 75.5, 82.5, 84}// ���� 23 ��͹
        ,{75.5, 76.5, 83.5, 85}// ���� 24 ��͹
        ,{76.5, 77.5, 84.5, 86}// ���� 25 ��͹
        ,{77.5, 78.5, 85.5, 87}// ���� 26 ��͹
        ,{78, 80, 86.5, 88}// ���� 27 ��͹
        ,{80, 81, 87.5, 89}// ���� 28 ��͹
        ,{81, 82, 88.7, 90}// ���� 29 ��͹
        ,{81.5, 83, 89.5, 91}// ���� 30 ��͹
        ,{82.5, 83.5, 90.5, 92}// ���� 31 ��͹
        ,{83.5, 84.5, 91.5, 93}// ���� 32 ��͹
        ,{84, 85.5, 92.5, 94}// ���� 33 ��͹
        ,{85, 86, 93, 95}// ���� 34 ��͹
        ,{85.7, 87, 94, 96}// ���� 35 ��͹
        ,{86.5, 87.5, 95, 96.5}// ���� 36 ��͹
        ,{87, 88.5, 96, 97.5}// ���� 37 ��͹
        ,{88, 89, 97, 98.5}// ���� 38 ��͹
        ,{88.5, 90, 98, 99.5}// ���� 39 ��͹
        ,{89.5, 91, 99, 100}// ���� 40 ��͹
        ,{90, 91.5, 99.5, 101}// ���� 41 ��͹
        ,{90.5, 92, 100.5, 102}// ���� 42 ��͹
        ,{91.5, 93, 101.5, 103}// ���� 43 ��͹
        ,{92, 93.5, 102, 104}// ���� 44 ��͹
        ,{92.5, 94.5, 103, 104.5}// ���� 45 ��͹
        ,{93, 95, 103.5, 105}// ���� 46 ��͹
        ,{94, 95.5, 104.5, 106}// ���� 47 ��͹
        ,{94.5, 96, 105, 107}// ���� 48 ��͹
        ,{95, 97, 106, 108}// ���� 49 ��͹
        ,{95.5, 97.5, 107, 108.5}// ���� 50 ��͹
        ,{96, 98, 107.5, 109.5}// ���� 51 ��͹
        ,{96.5, 99, 108.2, 110}// ���� 52 ��͹
        ,{97, 99.5, 109, 111}// ���� 53 ��͹
        ,{98, 100, 110, 111.5}// ���� 54 ��͹
        ,{98.5, 100.5, 110.5, 112.5}// ���� 55 ��͹
        ,{99, 101, 111, 113}// ���� 56 ��͹
        ,{99.5, 101.5, 112, 114}// ���� 57 ��͹
        ,{100, 102, 112.5, 114.5}// ���� 58 ��͹
        ,{100.5, 102.5, 113, 115}// ���� 59 ��͹
        ,{101, 103, 114, 116}// ���� 60 ��͹
        ,{101.5, 103.5, 115, 117}// ���� 61 ��͹
        ,{102.7, 104, 115.5, 117.5}// ���� 62 ��͹
        ,{102, 104.5, 116, 118}// ���� 63 ��͹
        ,{102.5, 105, 116.5, 118.5}// ���� 64 ��͹
        ,{103, 105.5, 117, 119}// ���� 65 ��͹
        ,{103.5, 106, 117.7, 120}// ���� 66 ��͹
        ,{103.8, 106.5, 118.5, 120.5}// ���� 67 ��͹
        ,{104, 107, 119, 121}// ���� 68 ��͹
        ,{104.5, 107.5, 119.5, 122}// ���� 69 ��͹
        ,{105, 108, 120, 122.5}// ���� 70 ��͹
        ,{105.2, 108.2, 120.5, 123}// ���� 71 ��͹
        ,{105.5, 108.5, 121, 123}// ���� 72 ��͹
    };
    static double weight_height_girl[][] = new double[][]{
        {2.8, 3, 4,3.7,4.1}// ��ǹ�٧ 50cm.
        ,{2.6, 2.8, 3.9,3.7,4.1}// ��ǹ�٧ 51cm.
        ,{2.4, 2.7, 3.7,3.7,4.1}// ��ǹ�٧ 52cm.
        ,{2.3, 2.6, 3.5,3.7,4.1}// ��ǹ�٧ 53cm.
        ,{2.2, 2.4, 3.4,3.7,4.2}// ��ǹ�٧ 54cm.
        ,{2.1, 2.3, 3.3,3.8,4.2}// ��ǹ�٧ 55cm.
        ,{2, 2.2, 3.2,3.8,4.3}// ��ǹ�٧ 56cm.
        ,{1.9, 2.2, 3.1,3.8,4.4}// ��ǹ�٧ 57cm.
        ,{1.9, 2.2, 3,3.9,4.5}// ��ǹ�٧ 58cm.
        ,{1.9, 2.2, 3,3.9,4.6}// ��ǹ�٧ 59cm.
        ,{2, 2.2, 3,3.9,4.7}// ��ǹ�٧ 60cm.
        ,{2.1, 2.2, 3.1,3.9,4.8}// ��ǹ�٧ 61cm.
        ,{2.2, 2.3, 3.2,4,4.85}// ��ǹ�٧ 62cm.
        ,{2.3, 2.4, 3.2,4.1,4.9}// ��ǹ�٧ 63cm.
        ,{2.4, 2.5, 3.2,4.2,5}// ��ǹ�٧ 64cm.
        ,{2.5, 2.6, 3.3,4.3,5.1}// ��ǹ�٧ 65cm.
        ,{2.6, 2.7, 3.4,4.5,5.2}// ��ǹ�٧ 66cm.
        ,{2.8, 2.9, 3.5,4.6,5.3}// ��ǹ�٧ 67cm.
        ,{2.9, 2.95, 3.6,4.7,5.5}// ��ǹ�٧ 68cm.
        ,{3, 3.05, 3.8,4.9,5.7}// ��ǹ�٧ 69cm.
        ,{3.1, 3.15, 3.9,5,5.9}// ��ǹ�٧ 70cm.
        ,{3.2, 3.25, 4,5.1,6}// ��ǹ�٧ 71cm.
        ,{3.3, 3.35, 4.2,5.3,6.2}// ��ǹ�٧ 72cm.
        ,{3.5, 3.55, 4.4,5.5,6.5}// ��ǹ�٧ 73cm.
        ,{3.7, 3.75, 4.6,5.7,6.7}// ��ǹ�٧ 74cm.
        ,{3.9, 3.95, 4.8,5.9,6.9}// ��ǹ�٧ 75cm.
        ,{4, 4.05, 5,6,7.1}// ��ǹ�٧ 76cm.
        ,{4.1, 4.15, 5.1,6.2,7.3}// ��ǹ�٧ 77cm.
        ,{4.3, 4.35, 5.3,6.5,7.7}// ��ǹ�٧ 78cm.
        ,{4.6, 4.65, 5.7,6.8,8}// ��ǹ�٧ 79cm.
        ,{4.8, 4.85, 5.9,7,8.2}// ��ǹ�٧ 80cm.
        ,{4.9, 4.95, 6,7.2,8.6}// ��ǹ�٧ 81cm.
        ,{5, 5.05, 6.3,7.7,8.9}// ��ǹ�٧ 82cm.
        ,{5.3, 5.35, 6.8,7.9,9.1}// ��ǹ�٧ 83cm.
        ,{5.5, 5.55, 7,8.1,9.6}// ��ǹ�٧ 84cm.
        ,{5.7, 5.75, 7.3,8.5,9.9}// ��ǹ�٧ 85cm.
        ,{5.9, 6, 7.7,8.9,10.1}// ��ǹ�٧ 86cm.
        ,{6, 6.1, 8,9.1,10.5}// ��ǹ�٧ 87cm.
        ,{6.1, 6.3, 8.3,9.5,10.9}// ��ǹ�٧ 88cm.
        ,{6.3, 6.5, 8.8,9.9,11.2}// ��ǹ�٧ 89cm.
        ,{6.7, 6.9, 9,10.2,11.7}// ��ǹ�٧ 90cm.
        ,{6.9, 7.2, 9.4,10.5,12}// ��ǹ�٧ 91cm.
        ,{7.1, 7.4, 9.9,11,12.6}// ��ǹ�٧ 92cm.
        ,{7.4, 7.7, 10.2,11.3,13}// ��ǹ�٧ 93cm.
        ,{7.7, 8, 10.8,11.8,13.5}// ��ǹ�٧ 94cm.
        ,{7.9, 8.2, 11.1,12.2,14}// ��ǹ�٧ 95cm.
        ,{8.1, 8.6, 11.5,12.6,14.3}// ��ǹ�٧ 96cm.
        ,{8.3, 8.8, 12,13,14.8}// ��ǹ�٧ 97cm.
        ,{8.7, 9, 12.5,13.5,15.3}// ��ǹ�٧ 98cm.
        ,{8.9, 9.3, 13,14,15.8}// ��ǹ�٧ 99cm.
        ,{9, 9.7, 13.4,14.5,16.3}// ��ǹ�٧ 100cm.
        ,{9.3, 9.9, 14,15,16.9}// ��ǹ�٧ 101cm.
        ,{9.6, 10.2, 14.4,15.4,17.3}// ��ǹ�٧ 102cm.
        ,{9.9, 10.5, 15,16,18}// ��ǹ�٧ 103cm.
        ,{10.1, 10.7, 15.5,16.5,18.5}// ��ǹ�٧ 104cm.
        ,{10.3, 11, 16,17,19}// ��ǹ�٧ 105cm.
        ,{10.6, 11.3, 16.6,17.6,19.7}// ��ǹ�٧ 106cm.
        ,{10.8, 11.7, 17.2,18.2,20.4}// ��ǹ�٧ 107cm.
        ,{11, 12, 17.8,18.8,21}// ��ǹ�٧ 108cm.
        ,{11.3, 12.2, 18.2,19.2,21.7}// ��ǹ�٧ 109cm.
        ,{11.7, 12.6, 18.8,19.9,22.3}// ��ǹ�٧ 110cm.
        ,{11.9, 12.9, 19.5,20.5,23}// ��ǹ�٧ 111cm.
        ,{12.2, 13.2, 20.2,21.2,23.7}// ��ǹ�٧ 112cm.
        ,{12.5, 13.6, 20.9,21.9,24.5}// ��ǹ�٧ 113cm.
        ,{12.8, 13.9, 21.4,22.5,25}// ��ǹ�٧ 114cm.
        ,{13, 14.2, 22,23,25.9}// ��ǹ�٧ 115cm.
        ,{13.3, 14.5, 22.8,23.8,26.6}// ��ǹ�٧ 116cm.
        ,{13.7, 14.8, 23.3,24.5,27.3}// ��ǹ�٧ 117cm.
        ,{14, 15.1, 24,25.2,28}// ��ǹ�٧ 118cm.
        ,{14.2, 15.5, 24.8,26,29}// ��ǹ�٧ 119cm.
        ,{14.5, 15.8, 25.5,26.7,29.8}// ��ǹ�٧ 120cm.
        ,{14.8, 16.1, 26.2,27.4,30.8}// ��ǹ�٧ 121cm.
        ,{15, 16.5, 27,28.2,31.6}// ��ǹ�٧ 122cm.
        ,{15.4, 16.9, 27.7,29,32.3}// ��ǹ�٧ 123cm.
        ,{15.7, 17.2, 28.5,29.9,33.4}// ��ǹ�٧ 124cm.
        ,{15.9, 17.7, 29,30.5,34.2}// ��ǹ�٧ 125cm.
        ,{16.2, 18, 30,31.4,35.2}// ��ǹ�٧ 126cm.
        ,{16.5, 18.3, 30.7,32.3,36.3}// ��ǹ�٧ 127cm.
        ,{16.8, 18.7, 31.7,33.3,37.2}// ��ǹ�٧ 128cm.
        ,{17, 19, 32.3,34.1,38.3}// ��ǹ�٧ 129cm.
        ,{17.5, 19.5, 33.3,35,39.5}// ��ǹ�٧ 130cm.
        ,{17.8, 19.8, 34.1,36,40.3}// ��ǹ�٧ 131cm.
        ,{18, 20.1, 35,37,41.7}// ��ǹ�٧ 132cm.
        ,{18.3, 20.5, 35.9,38,42.9}// ��ǹ�٧ 133cm.
        ,{18.7, 20.9, 36.8,39,43.9}// ��ǹ�٧ 134cm.
        ,{19, 21.2, 37.7,39.9,45}// ��ǹ�٧ 135cm.
        ,{19.3, 21.7, 38.8,41,46}// ��ǹ�٧ 136cm.
        ,{19.7, 22, 39.7,42,47.7}// ��ǹ�٧ 137cm.
        ,{19.9, 22.3, 40.3,43.2,49}// ��ǹ�٧ 138cm.
        ,{20.3, 22.8, 41.6,44.7,50.7}// ��ǹ�٧ 139cm.
        ,{20.6, 23, 42.3,45.5,51.8}// ��ǹ�٧ 140cm.
    };
    static double weight_height_boy[][] = new double[][]{
        {2.8, 3, 3.7,3.7,4}// ��ǹ�٧ 50cm.
        ,{2.6, 2.8, 3.6,3.7,4}// ��ǹ�٧ 51cm.
        ,{2.4, 2.6, 3.5,3.7,4}// ��ǹ�٧ 52cm.
        ,{2.3, 2.55, 3.3,3.7,4}// ��ǹ�٧ 53cm.
        ,{2.2, 2.5, 3.3,3.7,4.1}// ��ǹ�٧ 54cm.
        ,{2.15, 2.45, 3.2,3.8,4.2}// ��ǹ�٧ 55cm.
        ,{2.15, 2.45, 3.15,3.8,4.2}// ��ǹ�٧ 56cm.
        ,{2.15, 2.4, 3.15,3.8,4.3}// ��ǹ�٧ 57cm.
        ,{2.15, 2.3, 3.1,3.9,4.5}// ��ǹ�٧ 58cm.
        ,{2.25, 2.35, 3.1,3.95,4.6}// ��ǹ�٧ 59cm.
        ,{2.3, 2.35, 3.1,4,4.7}// ��ǹ�٧ 60cm.
        ,{2.5, 2.55, 3.2,4.05,4.8}// ��ǹ�٧ 61cm.
        ,{2.7, 2.75, 3.2,4.1,4.9}// ��ǹ�٧ 62cm.
        ,{2.8, 2.85, 3.25,4.2,4.95}// ��ǹ�٧ 63cm.
        ,{2.9, 2.95, 3.3,4.3,5}// ��ǹ�٧ 64cm.
        ,{2.95, 3, 3.35,4.35,5.1}// ��ǹ�٧ 65cm.
        ,{3, 3.05, 3.6,4.6,5.3}// ��ǹ�٧ 66cm.
        ,{3.2, 3.25, 3.7,4.8,5.5}// ��ǹ�٧ 67cm.
        ,{3.5, 3.55, 3.8,4.9,5.7}// ��ǹ�٧ 68cm.
        ,{3.6, 3.65, 3.9,5,5.8}// ��ǹ�٧ 69cm.
        ,{3.7, 3.75, 4,5.1,6}// ��ǹ�٧ 70cm.
        ,{3.9, 3.95, 4.2,5.3,6.2}// ��ǹ�٧ 71cm.
        ,{4.1, 4.15, 4.5,5.5,6.4}// ��ǹ�٧ 72cm.
        ,{4.3, 4.35, 4.7,5.7,6.6}// ��ǹ�٧ 73cm.
        ,{4.6, 4.65, 4.9,5.9,6.9}// ��ǹ�٧ 74cm.
        ,{4.8, 4.85, 5.1,6,7}// ��ǹ�٧ 75cm.
        ,{5.1, 5.15, 5.3,6.3,7.3}// ��ǹ�٧ 76cm.
        ,{5.3, 5.35, 5.5,6.6,7.7}// ��ǹ�٧ 77cm.
        ,{5.5, 5.55, 5.8,6.9,8}// ��ǹ�٧ 78cm.
        ,{5.7, 5.77, 6.1,7.1,8.2}// ��ǹ�٧ 79cm.
        ,{5.9, 5.95, 6.3,7.3,8.5}// ��ǹ�٧ 80cm.
        ,{6.1, 6.15, 6.7,7.7,8.9}// ��ǹ�٧ 81cm.
        ,{6.4, 6.45, 6.9,8,9.2}// ��ǹ�٧ 82cm.
        ,{6.7, 6.75, 7.1,8.3,9.5}// ��ǹ�٧ 83cm.
        ,{7, 7.05, 7.7,8.7,9.8}// ��ǹ�٧ 84cm.
        ,{7.3, 7.35, 8,9,10.2}// ��ǹ�٧ 85cm.
        ,{7.6, 7.65, 8.3,9.3,10.6}// ��ǹ�٧ 86cm.
        ,{7.8, 7.85, 8.7,9.7,11}// ��ǹ�٧ 87cm.
        ,{8, 8.05, 9,10,11.3}// ��ǹ�٧ 88cm.
        ,{8.3, 8.35, 9.5,10.4,11.7}// ��ǹ�٧ 89cm.
        ,{8.7, 8.75, 9.9,10.9,12.2}// ��ǹ�٧ 90cm.
        ,{9, 9.05, 10.2,11.2,12.7}// ��ǹ�٧ 91cm.
        ,{9.3, 9.35, 10.7,11.7,13}// ��ǹ�٧ 92cm.
        ,{9.7, 9.75, 11,12,13.5}// ��ǹ�٧ 93cm.
        ,{9.9, 9.95, 11.5,12.5,14}// ��ǹ�٧ 94cm.
        ,{10.2, 10.25, 12,13,14.5}// ��ǹ�٧ 95cm.
        ,{10.5, 10.55, 12.5,13.5,15}// ��ǹ�٧ 96cm.
        ,{10.8, 10.85, 13,14,15.5}// ��ǹ�٧ 97cm.
        ,{11, 11.05, 13.5,14.5,16}// ��ǹ�٧ 98cm.
        ,{11.3, 11.35, 14.1,15,16.5}// ��ǹ�٧ 99cm.
        ,{11.8, 11.85, 14.7,15.4,17}// ��ǹ�٧ 100cm.
        ,{12, 12.05, 15,16,17.8}// ��ǹ�٧ 101cm.
        ,{12.3, 12.35, 15.7,16.7,18.2}// ��ǹ�٧ 102cm.
        ,{12.8, 12.85, 16.3,17.2,18.8}// ��ǹ�٧ 103cm.
        ,{13, 13.05, 16.9,17.8,19.5}// ��ǹ�٧ 104cm.
        ,{13.5, 13.55, 17.5,18.3,20}// ��ǹ�٧ 105cm.
        ,{13.9, 13.95, 18,18.9,20.8}// ��ǹ�٧ 106cm.
        ,{14, 14.1, 18.7,19.7,21.3}// ��ǹ�٧ 107cm.
        ,{14.5, 14.7, 19,20,22}// ��ǹ�٧ 108cm.
        ,{14.7, 14.9, 19.9,20.8,22.8}// ��ǹ�٧ 109cm.
        ,{15, 15.2, 20.5,21.5,23.3}// ��ǹ�٧ 110cm.
        ,{15.4, 15.7, 21.2,22.1,24}// ��ǹ�٧ 111cm.
        ,{15.7, 16, 22,22.8,25}// ��ǹ�٧ 112cm.
        ,{16, 16.5, 22.6,23.5,25.8}// ��ǹ�٧ 113cm.
        ,{16.5, 16.9, 23.2,24.2,26.5}// ��ǹ�٧ 114cm.
        ,{16.9, 17.3, 24,25,27.1}// ��ǹ�٧ 115cm.
        ,{17.2, 17.8, 24.7,25.8,28}// ��ǹ�٧ 116cm.
        ,{17.5, 18, 25.3,26.3,28.9}// ��ǹ�٧ 117cm.
        ,{18, 18.6, 26.2,27.2,29.8}// ��ǹ�٧ 118cm.
        ,{18.3, 18.8, 26.8,28,30.5}// ��ǹ�٧ 119cm.
        ,{18.7, 19.2, 27.6,28.7,31.3}// ��ǹ�٧ 120cm.
        ,{19, 19.7, 28.4,29.8,32.3}// ��ǹ�٧ 121cm.
        ,{19.3, 20.1, 29.2,30.3,33.3}// ��ǹ�٧ 122cm.
        ,{19.7, 20.6, 30.1,31.2,34.2}// ��ǹ�٧ 123cm.
        ,{20.1, 21, 30.8,32.1,35.2}// ��ǹ�٧ 124cm.
        ,{20.5, 21.4, 31.7,33,36}// ��ǹ�٧ 125cm.
        ,{20.9, 21.9, 32.5,34,37}// ��ǹ�٧ 126cm.
        ,{21.2, 22.2, 33.3,35,38.2}// ��ǹ�٧ 127cm.
        ,{21.8, 22.7, 34.2,35.8,39.5}// ��ǹ�٧ 128cm.
        ,{22, 23, 35,36.8,40.5}// ��ǹ�٧ 129cm.
        ,{22.5, 23.5, 36,37.9,41.5}// ��ǹ�٧ 130cm.
        ,{22.8, 24, 37,38.8,43}// ��ǹ�٧ 131cm.
        ,{23, 24.5, 37.9,39.9,43.8}// ��ǹ�٧ 132cm.
        ,{23.5, 24.9, 38.9,41,45.1}// ��ǹ�٧ 133cm.
        ,{23.9, 25.4, 39.9,42,46.5}// ��ǹ�٧ 134cm.
        ,{24.4, 26, 40.9,43.1,47.8}// ��ǹ�٧ 135cm.
        ,{24.8, 26.3, 41.9,44.2,49}// ��ǹ�٧ 136cm.
        ,{25.2, 26.7, 42.9,45.3,50.6}// ��ǹ�٧ 137cm.
        ,{25.7, 27, 43.8,46.8,51.9}// ��ǹ�٧ 138cm.
        ,{26, 27.7, 45,48,53.5}// ��ǹ�٧ 139cm.
        ,{26.3, 28, 46,49.1,54.8}// ��ǹ�٧ 140cm.
    };
    public GutilPCU() {
    }
    public static void main(String[] args)
    {
//        System.err.println(getNutrition(2,3.2));
//        System.err.println(DateUtil.calculateAgeMonth("2550-07-07", "2553-07-01"));
//        DateUtil.calculateAgeMonth("2550-07-07", "2553-07-01");
//        DateUtil.calculateAgeInt("2550-07-07", "2553-07-01");
        System.out.println(getNutritionWeightGirl("2553-07-01","2552-09-07","6.9"));
    }
    /**
     * return 1 = ���¡���ࡳ��
     * return 2 = ��͹��ҧ����
     * return 3 = ���˹ѡ���ࡳ��
     * return 4 = ���˹ѡ��͹��ҧ�ҡ
     * return 5 = ���˹ѡ�ҡ�Թࡳ��
     */
    public static int getNutritionWeightBoy(String cur_date,String birthdate,String weight){

        int age_month = DateUtil.calculateAgeMonth(birthdate, cur_date);
        System.out.println("[DATA]age = " + age_month);
        System.out.println("[DATA]weight = " + weight);
        double w = Double.parseDouble(weight);
        if(w<=nutrtion_boy[age_month][0])
            return 1;
        else if(w<=nutrtion_boy[age_month][1])
            return 2;
        else if(w<=nutrtion_boy[age_month][2])
            return 3;
        else if(w<=nutrtion_boy[age_month][3])
            return 4;
        else
            return 5;
    }
    /**
     * return 1 = ���
     * return 2 = ��͹��ҧ���
     * return 3 = ����ǹ
     * return 4 = ����
     * return 5 = �������ǹ
     * return 6 = ��ǹ
     */
    public static int getNutritionWeightHeightBoy(String cur_date,String birthdate,String weight){

        int age_month = DateUtil.calculateAgeMonth(birthdate, cur_date);
        System.out.println("[DATA]age = " + age_month);
        System.out.println("[DATA]weight = " + weight);
        double w = Double.parseDouble(weight);
        if(w<=weight_height_boy[age_month][0])
            return 1;
        else if(w<=weight_height_boy[age_month][1])
            return 2;
        else if(w<=weight_height_boy[age_month][2])
            return 3;
        else if(w<=weight_height_boy[age_month][3])
            return 4;
        else if(w<=weight_height_boy[age_month][4])
            return 5;
        else
            return 6;
    }
    /**
     * return 1 = ���
     * return 2 = ��͹��ҧ���
     * return 3 = ����ǹ
     * return 4 = ����
     * return 5 = �������ǹ
     * return 6 = ��ǹ
     */
    public static int getNutritionWeightHeightGirl(String cur_date,String birthdate,String weight){
        
        int age_month = DateUtil.calculateAgeMonth(birthdate, cur_date);
        System.out.println("[DATA]age = " + age_month);
        System.out.println("[DATA]weight = " + weight);
        double w = Double.parseDouble(weight);
        if(w<=weight_height_girl[age_month][0])
            return 1;
        else if(w<=weight_height_girl[age_month][1])
            return 2;
        else if(w<=weight_height_girl[age_month][2])
            return 3;
        else if(w<=weight_height_girl[age_month][3])
            return 4;
        else if(w<=weight_height_girl[age_month][4])
            return 5;
        else
            return 6;
    }
    /**
     * return 1 = ���¡���ࡳ��
     * return 2 = ��͹��ҧ����
     * return 3 = ���˹ѡ���ࡳ��
     * return 4 = ���˹ѡ��͹��ҧ�ҡ
     * return 5 = ���˹ѡ�ҡ�Թࡳ��
     */
    public static int getNutritionWeightGirl(String cur_date,String birthdate,String weight){
        
        int age_month = DateUtil.calculateAgeMonth(birthdate, cur_date);
        System.out.println("[DATA]age = " + age_month);
        System.out.println("[DATA]weight = " + weight);
        double w = Double.parseDouble(weight);
        if(w<=nutrition_girl[age_month][0])
            return 1;
        else if(w<=nutrition_girl[age_month][1])
            return 2;
        else if(w<=nutrition_girl[age_month][2])
            return 3;
        else if(w<=nutrition_girl[age_month][3])
            return 4;
        else
            return 5;
    }
    /**
     * return 1 = ����
     * return 2 = ��͹��ҧ����
     * return 3 = ��ǹ�٧���ࡳ��
     * return 4 = ��͹��ҧ�٧
     * return 5 = �٧����ࡳ��
     */
    public static int getNutritionHeightGirl(String cur_date,String birthdate,String height){
        int age_month = DateUtil.calculateAgeMonth(birthdate, cur_date);
        System.out.println("[DATA]age = " + age_month);
        System.out.println("[DATA]Height = " + height);
        double h = Double.parseDouble(height);
        if(h<=height_girl[age_month][0])
            return 1;
        else if(h<=height_girl[age_month][1])
            return 2;
        else if(h<=height_girl[age_month][2])
            return 3;
        else if(h<=height_girl[age_month][3])
            return 4;
        else
            return 5;
    }
    /**
     * return 1 = ����
     * return 2 = ��͹��ҧ����
     * return 3 = ��ǹ�٧���ࡳ��
     * return 4 = ��͹��ҧ�٧
     * return 5 = �٧����ࡳ��
     */
    public static int getNutritionHeightBoy(String cur_date,String birthdate,String height){
        int age_month = DateUtil.calculateAgeMonth(birthdate, cur_date);
        System.out.println("[DATA]age = " + age_month);
        System.out.println("[DATA]Height = " + height);
        double h = Double.parseDouble(height);
        if(h<=height_boy[age_month][0])
            return 1;
        else if(h<=height_boy[age_month][1])
            return 2;
        else if(h<=height_boy[age_month][2])
            return 3;
        else if(h<=height_boy[age_month][3])
            return 4;
        else
            return 5;
    }
    public static boolean setGuiData(JLabel j,String data){
        j.setText(data);
        return true;
    }
    public static boolean setGuiData(PIDPanel j,String data){
        j.setText(data);
        return true;
    }
    public static boolean setGuiData(JTextField j,String data){
        j.setText(data);
        return true;
    }
    public static boolean setGuiData(DateComboBox j,String data){
        j.setText(DateUtil.convertFieldDate(data));
        return true;
    }
    public static boolean setGuiData(JComboBox j,String data){
        return ComboboxModel.setCodeComboBox(j,data);
    }
    public static boolean setGuiData(JTable jt,Vector v,String code) {
        int i=0;
        for(i=0;i<v.size();i++) {
            String p=((CommonInf)v.get(i)).getCode();
            if(code.equals(p))
                break;
        }
        jt.getSelectionModel().setSelectionInterval(i,i);
        if(i==v.size()) return false;
        return true;
    }
    public static void isSelected(DateComboBox j,String data){
        j.setText(data);
    }
    public static boolean isSelected(String s) {
        try{
            if(s.equals("1"))
                return true;
            if(s.equals("0"))
                return false;
        } catch(Exception ex) {
            return false;
        }
        return false;
    }
//
//    public static String getTextBundle_old (String str) {
//        if(str.trim().equals("")) {
//            return "";
//        }
//        try {
//            return java.util.ResourceBundle.getBundle("com/pcu/property/thai").getString(str);
//        } catch(Exception e) {
//            return str;
//        }
//    }

    public static String getVersion (String str) {
        if(str.trim().equals("")) {
            return "";
        }
        try {
            return java.util.ResourceBundle.getBundle("com/pcu/property/config").getString(str);
        } catch(Exception e) {
            return str;
        }
    }
    public static boolean setGuiLang(JLabel jl){
        jl.setText(getTextBundle(jl.getText()));
        return true;
    }
    public static boolean setGuiLang(JPanel jPanel2){
        TitledBorder tb = (TitledBorder)jPanel2.getBorder();
        if(tb==null)
            return false;
        jPanel2.setBorder(new javax.swing.border.TitledBorder(
                GutilPCU.getTextBundle(tb.getTitle())));
        return true;
    }
    public static boolean setGuiLang(JButton jl){
        jl.setText(getTextBundle(jl.getText()));
        return true;
    }
//
//    /**
//     * @deprecate henbe unused
//     * @param str
//     */
//    public static void printlnFile(String str){
//        FileOutputStream fos = null;
//        try {
//            if(str.equals(old_str))
//                return;
//            str+= "\n";
//            fos = new FileOutputStream("bundle_pcu.txt",true);
//            fos.write(str.getBytes());
//            old_str = str;
//        }
//        catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        finally {
//            try {
//                fos.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
    public static String getTextBundle(String str)
    {
        if(str==null || str.equals(""))
            return null;
        return setLanguage(str);
    }
//        language = null;
//        if(data !=null){
//            try {
//                language =  data.getProperty(str.trim());
//            }
//            catch(Exception e){
//                e.printStackTrace();
//                printlnFile(str);
////                Constant.println("aBundle not found:" + str);
////                language = "NotFnd";
//            }
//        }
//        if(language==null){
//            printlnFile(str);
////            Constant.println("aBundle not found:" + str);
//            language = str;
//        }
//        return language;
//    }
//
//
    public static void JPanelLabler(JPanel pane) {
        try{
            ((TitledBorder) pane.getBorder()).setTitle(getTextBundle(((TitledBorder) pane.getBorder()).getTitle()));
       }
       catch(Exception e) {

        }
    }
    public static void setGuiLang(JTabbedPane  jt)
    {
        if(jt==null)
            return;
        for(int i=0;i<jt.getTabCount();i++)
        {
            jt.setTitleAt(i,getTextBundle(jt.getTitleAt(i)));
//            jt.setFont(defaultFont);
        }
    }
//    public static void getTextBundleTab(int index,JTabbedPane pane) {/*amp*/
//        pane.setTitleAt(index,getTextBundle(pane.getTitleAt(index)));
//    }
//
    public static String changDateToString(String date) {
        return changDateToString(date,false);
    }
    public static String changDateToString(String date,boolean showTime) {

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if(showTime)
            df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Date dated = DateUtil.getDateFromText(date);
        if(dated==null)
            return "";
        System.out.println(df.format(dated));
        return df.format(dated);
    }

    /**
     * ������͵�ͧ��� code �ͧ combobox �����¡�÷��١���͡
     */
    public static String getGuiData(JComboBox j) {
        if(j.getItemCount()==0)    return "";
        return ComboboxModel.getCodeComboBox(j);
    }
    public static String getGuiData(JTextField j) {
        return CheckReservedWords(j.getText());
    }
    public static String getGuiData(PIDPanel j){
        return j.getText();
    }
    public static String getGuiData(String j) {
        return j;
    } 

    public static String CheckReservedWords(String words) {
        String reservedWord = "";
        words = words.trim();
        StringBuffer buf = new StringBuffer();
        char c;
        for(int i =0;i<words.length();i++)
        {
            c = words.charAt(i);
            char before = ' ';
            if(i>0)
            {
                before = words.charAt(i-1);
            }
            if(c == Gutil.SINGLE_SYMBOL && before != Gutil.BACKSLASH_SYMBOL)
            {
                buf.append(Gutil.BACKSLASH_SYMBOL).append(Gutil.SINGLE_SYMBOL);
            }
            else if(c == Gutil.BACKSLASH_SYMBOL && before != Gutil.BACKSLASH_SYMBOL)
            {
                buf.append(Gutil.BACKSLASH_SYMBOL).append(Gutil.BACKSLASH_SYMBOL);
            }
            else
            {
                buf.append(c);
            }
        }
        reservedWord = buf.toString();
        return reservedWord;
    }
//    /**
//     * ������͵�ͧ��� Description �ͧ combobox �����¡�÷��١���͡
//     */
//    public static String getGuiCodeData(JComboBox j) {
//        if(j.getItemCount()==0)    return "";
//        return ComboboxModel.getStringConboBox(j);
//    }
//
//    public static String getTextBundleImage(String str)
//    {
//        if(str.trim().equals(""))return "";
//        try{
//            return java.util.ResourceBundle.getBundle("com/pcu/property/image").getString(str);
//        }catch(Exception e){
//           // Constant.println(str + ":Not Found ");
//            return str;
//        }
//    }
//
    public static boolean setGuiLang(JCheckBox jl) {
        jl.setText(getTextBundle(jl.getText()));
        return true;
    }

    public static boolean setGuiLang(JRadioButton jl) {
         jl.setText(getTextBundle(jl.getText()));
        return true;
    }
//

    public static void addNew(HJTableSort jTable, PanelObj aThis, Object initSurveil) {
        jTable.clearSelection();
        aThis.setObject(initSurveil);
    }

    public static  void saveNotify(PanelObj aThis, JTable jtable, int c1, int c2
            ,String f1,String f2) {
        aThis.refreshList();
        for(int i=0;i<jtable.getRowCount();i++){
            if(f1.equals((String)jtable.getValueAt(i, c1))
            && f2.equals((String)jtable.getValueAt(i, c2))){
                jtable.setRowSelectionInterval(i, i);
                aThis.selectList();
                break;
            }
        }
    }
}
