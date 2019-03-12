package com.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * <p>
 * 小数点处理类
 * </p>
 *
 */
public class DecimalUtils {
	
	private DecimalUtils(){}
	//默认小数点精确到几位,四舍五入
	private final static int DEF_DIV_SCALE = 10;
	
	 /**   
     * 提供精确的加法运算。   
     * @param v1 被加数   
     * @param v2 加数   
     * @return 两个参数的和   
     */    
    public static double add(double v1,double v2){    
        BigDecimal b1 = new BigDecimal(Double.toString(v1));    
        BigDecimal b2 = new BigDecimal(Double.toString(v2));    
        return b1.add(b2).doubleValue();    
    }    
       
    /**   
     * 提供精确的减法运算。   
     * @param v1 被减数   
     * @param v2 减数   
     * @return 两个参数的差   
     */    
    public static double sub(double v1,double v2){    
        BigDecimal b1 = new BigDecimal(Double.toString(v1));    
        BigDecimal b2 = new BigDecimal(Double.toString(v2));    
        return b1.subtract(b2).doubleValue();    
    }    
       
    /**   
     * 提供精确的乘法运算。   
     * @param v1 被乘数   
     * @param v2 乘数   
     * @return 两个参数的积   
     */    
    public static double mul(double v1,double v2){    
        BigDecimal b1 = new BigDecimal(Double.toString(v1));    
        BigDecimal b2 = new BigDecimal(Double.toString(v2));    
        return b1.multiply(b2).doubleValue();    
    }    
  
    /**   
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到   
     * 小数点以后10位，以后的数字四舍五入。   
     * @param v1 被除数   
     * @param v2 除数   
     * @return 两个参数的商   
     */    
    public static double div(double v1,double v2){    
        return div(v1,v2,DEF_DIV_SCALE);    
    }    
  
    /**   
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指   
     * 定精度，以后的数字四舍五入。   
     * @param v1 被除数   
     * @param v2 除数   
     * @param scale 表示表示需要精确到小数点以后几位。   
     * @return 两个参数的商   
     */    
    public static double div(double v1,double v2,int scale){    
        if(scale<0){    
            throw new IllegalArgumentException(    
                "The scale must be a positive integer or zero");    
        }    
        BigDecimal b1 = new BigDecimal(Double.toString(v1));    
        BigDecimal b2 = new BigDecimal(Double.toString(v2));    
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();    
    }    

	/**
	 * 
	 * <p>
	 * 四舍五入截取小数点后两位
	 * </p>
	 * 
	 * @param d
	 * @return
	 * @throws
	 */
	public static double format(double d) {
		DecimalFormat df = new DecimalFormat("#.##");
		return Double.valueOf(df.format(d));
	}

	/**
	 * 
	 * <p>
	 * 截取小数点后两位
	 * </p>
	 * 
	 * @param d
	 * @return
	 * @throws
	 */
	public static double formatFloor(double d) {
		return format(d, 2, RoundingMode.FLOOR);
	}

	/**
	 * 
	 * <p>
	 * 截取小数点后几位
	 * </p>
	 * 
	 * @param d
	 * @return
	 * @throws
	 */
	public static double formatFloor(double d, int w) {
		return format(d, w, RoundingMode.FLOOR);
	}

	/**
	 * 
	 * <p>
	 * 截取小数点
	 * </p>
	 * 
	 * @param d
	 * @return
	 * @throws
	 */
	public static double format(double d, int w, RoundingMode roundingMode) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(w);
		df.setGroupingSize(0);
		df.setRoundingMode(roundingMode);
		return Double.valueOf(df.format(d));
	}

	/**
	 * 
	 * <p>
	 * 格式化小数点
	 * </p>
	 * 
	 * @param d
	 * @param type
	 * @param w
	 * @return
	 * @throws
	 */
	public static double round(double d, int type, int w) {
		BigDecimal bd = new BigDecimal(d);
		bd = bd.setScale(w, type);
		return bd.doubleValue();
	}

	/**
	 * <p>
	 * 直接截取小数点后几位，不进行四舍五入
	 * </p>
	 * @param d
	 * @param w
	 * @return
	 * @throws
	 */
	public static double roundFloor(double d, int w) {
		return format(d,w,RoundingMode.FLOOR);
	}
	/**
	 * <p>
	 * 直接截取小数点后2位，不进行四舍五入
	 * </p>
	 * @param d
	 * @return
	 * @throws
	 */
	public static double roundFloor(double d) {
		return roundFloor(d, 2);
	}
	
	/**
	 * <p>
	 * 直接截取小数点后几位，不进行四舍五入
	 * </p>
	 * @param d
	 * @param w
	 * @return
	 * @throws
	 */
	public static double roundDown(double d, int w) {
		return format(d,w,RoundingMode.DOWN);
	}
	/**
	 * <p>
	 * 直接截取小数点后2位，不进行四舍五入
	 * </p>
	 * @param d
	 * @return
	 * @throws
	 */
	public static double roundDown(double d) {
		return roundDown(d, 2);
	}
	/**
	 * 
	 * <p>
	 * 四舍五入格式化小数点后2位
	 * </p>
	 * 
	 * @param d
	 * @return
	 * @throws
	 */
	public static double roundHalfUp(double d) {
		return round(d, BigDecimal.ROUND_HALF_UP, 2);
	}

	/**
	 * 
	 * <p>
	 * 格式化小数点
	 * </p>
	 * 
	 * @param d
	 * @param type
	 * @param w
	 * @return
	 * @throws
	 */
	public static float round(float d, int type, int w) {
		BigDecimal bd = new BigDecimal(d);
		bd = bd.setScale(w, type);
		return bd.floatValue();
	}

	/**
	 * 
	 * <p>
	 * 直接截取小数点后几位，不进行四舍五入
	 * </p>
	 * 
	 * @param d
	 * @param w
	 * @return
	 * @throws
	 */
	public static float roundFloor(float d, int w) {
		return round(d, BigDecimal.ROUND_FLOOR, w);
	}

	/**
	 * 
	 * <p>
	 * 直接截取小数点后2位，不进行四舍五入
	 * </p>
	 * 
	 * @param d
	 * @return
	 * @throws
	 */
	public static float roundFloor(float d) {
		return round(d, BigDecimal.ROUND_FLOOR, 2);
	}
	

	/**
	 * 
	 * <p>
	 * 四舍五入格式化小数点后2位
	 * </p>
	 * 
	 * @param d
	 * @return
	 * @throws
	 */
	public static float roundHalfUp(float d) {
		return round(d, BigDecimal.ROUND_HALF_UP, 2);
	}

	/**
	 * 
	 * <p>
	 * 四舍五入
	 * </p>
	 * 
	 * @param value
	 * @return
	 * @throws
	 */
	public static double convert(double value) {
		long l1 = Math.round(value * 100); // 四舍五入
		double ret = l1 / 100.0; // 注意:使用 100.0 而不是 100
		return ret;
	}

	/**
	 * 
	 * <p>
	 * 格式化费， 保留两位
	 * </p>
	 * 
	 * @param rateStr
	 * @return
	 * @throws
	 */
	public static String formateRate(String rateStr) {
		if (rateStr.indexOf(".") != -1) {
			// 获取小数点的位置
			int num = 0;
			num = rateStr.indexOf(".");

			// 获取小数点后面的数字 是否有两位 不足两位补足两位
			String dianAfter = rateStr.substring(0, num + 1);
			String afterData = rateStr.replace(dianAfter, "");
			if (afterData.length() < 2) {
				afterData = afterData + "0";
			}
			return rateStr.substring(0, num) + "." + afterData.substring(0, 2);
		} else {
			if (rateStr == "1") {
				return "100";
			} else {
				return rateStr;
			}
		}
	}
}
