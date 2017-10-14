/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hospital_os.utility;

import java.text.DecimalFormat;

/**
 *
 * @author henbe
 */
public class CurrencyUtil {
  private static final String[] tensNames = {
    "",
    " ten",
    " twenty",
    " thirty",
    " forty",
    " fifty",
    " sixty",
    " seventy",
    " eighty",
    " ninety"
  };

  private static final String[] numNames = {
    "",
    " one",
    " two",
    " three",
    " four",
    " five",
    " six",
    " seven",
    " eight",
    " nine",
    " ten",
    " eleven",
    " twelve",
    " thirteen",
    " fourteen",
    " fifteen",
    " sixteen",
    " seventeen",
    " eighteen",
    " nineteen"
  };

  private static String convert2EngLessThanOneThousand(int number) {
    String soFar;

    if (number % 100 < 20){
      soFar = numNames[number % 100];
      number /= 100;
    }
    else {
      soFar = numNames[number % 10];
      number /= 10;

      soFar = tensNames[number % 10] + soFar;
      number /= 10;
    }
    if (number == 0) return soFar;
    return numNames[number] + " hundred" + soFar;
  }


  public static String convert2Eng(double number) {
      try{
          String num = String.valueOf(number);
          int dd1 = Integer.parseInt(num.substring(0,num.indexOf(".")));
          int dd2 = Integer.parseInt(num.substring(num.indexOf(".")+1));
          String d1 = convert(dd1);
          String d2 = "";
          if(dd2!=0)
            d2 += "and " + convert(dd2) + " Satang ";
          return (d1+" Baht "+d2).toUpperCase();
      }
      catch(Exception e){
          return "";
      }
  }
  public static String convert(int number) {
    // 0 to 999 999 999 999
    if (number == 0) { return "zero"; }

    String snumber = Long.toString(number);

    // pad with "0"
    String mask = "000000000000";
    DecimalFormat df = new DecimalFormat(mask);
    snumber = df.format(number);

    // XXXnnnnnnnnn
    int billions = Integer.parseInt(snumber.substring(0,3));
    // nnnXXXnnnnnn
    int millions  = Integer.parseInt(snumber.substring(3,6));
    // nnnnnnXXXnnn
    int hundredThousands = Integer.parseInt(snumber.substring(6,9));
    // nnnnnnnnnXXX
    int thousands = Integer.parseInt(snumber.substring(9,12));

    String tradBillions;
    switch (billions) {
    case 0:
      tradBillions = "";
      break;
    case 1 :
      tradBillions = convert2EngLessThanOneThousand(billions)
      + " billion ";
      break;
    default :
      tradBillions = convert2EngLessThanOneThousand(billions)
      + " billion ";
    }
    String result =  tradBillions;

    String tradMillions;
    switch (millions) {
    case 0:
      tradMillions = "";
      break;
    case 1 :
      tradMillions = convert2EngLessThanOneThousand(millions)
      + " million ";
      break;
    default :
      tradMillions = convert2EngLessThanOneThousand(millions)
      + " million ";
    }
    result =  result + tradMillions;

    String tradHundredThousands;
    switch (hundredThousands) {
    case 0:
      tradHundredThousands = "";
      break;
    case 1 :
      tradHundredThousands = "one thousand ";
      break;
    default :
      tradHundredThousands = convert2EngLessThanOneThousand(hundredThousands)
      + " thousand ";
    }
    result =  result + tradHundredThousands;

    String tradThousand;
    tradThousand = convert2EngLessThanOneThousand(thousands);
    result =  result + tradThousand;

    // remove extra spaces!
    return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ") ;
  }

  /**
   * testing
   * @param args
   */
  public static void main(String[] args) {
    Constant.println("*** " + CurrencyUtil.convert2Eng(1.1));
    Constant.println("*** " + CurrencyUtil.convert2Eng(1.23));
    Constant.println("*** " + CurrencyUtil.convert2Eng(16));
    Constant.println("*** " + CurrencyUtil.convert2Eng(100));
    Constant.println("*** " + CurrencyUtil.convert2Eng(118));
    Constant.println("*** " + CurrencyUtil.convert2Eng(200));
    Constant.println("*** " + CurrencyUtil.convert2Eng(219));
    Constant.println("*** " + CurrencyUtil.convert2Eng(800));
    Constant.println("*** " + CurrencyUtil.convert2Eng(801));
    Constant.println("*** " + CurrencyUtil.convert2Eng(1316));
    Constant.println("*** " + CurrencyUtil.convert2Eng(1000000));
    Constant.println("*** " + CurrencyUtil.convert2Eng(2000000));
    Constant.println("*** " + CurrencyUtil.convert2Eng(3000200));
    Constant.println("*** " + CurrencyUtil.convert2Eng(700000));
    Constant.println("*** " + CurrencyUtil.convert2Eng(9000000));
    Constant.println("*** " + CurrencyUtil.convert2Eng(9001000));
    Constant.println("*** " + CurrencyUtil.convert2Eng(123456789));
    Constant.println("*** " + CurrencyUtil.convert2Eng(2147483647));

    /*
     *** zero
     *** one
     *** sixteen
     *** one hundred
     *** one hundred eighteen
     *** two hundred
     *** two hundred nineteen
     *** eight hundred
     *** eight hundred one
     *** one thousand three hundred sixteen
     *** one million
     *** two millions
     *** three millions two hundred
     *** seven hundred thousand
     *** nine millions
     *** nine millions one thousand
     *** one hundred twenty three millions four hundred
     **      fifty six thousand seven hundred eighty nine
     *** two billion one hundred forty seven millions
     **      four hundred eighty three thousand six hundred forty seven
     *** three billion ten
     **/
  }

}
