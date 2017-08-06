package com.me.finance;

import java.text.DecimalFormat;
import java.util.LongSummaryStatistics;

/**
 * Created by zjm on 26/04/2017.
 */
public class MoneyFormatUtil {

    /**
	 * 精确到分的金额格式
	 */
	private final static String MONEY_FORMAT = "#,##0.00";

	/**
	 * 精确到厘的金额格式
	 */
	private final static String LI_MONEY_FORMAT = "#,##0.000";

	/**
	 * 中文数字常量
	 */
	private final static String[] CHINESE_NUMBER = { "零", "壹", "贰", "叁", "肆",
			"伍", "陆", "柒", "捌", "玖" };

	/**
	 * 中文数字单位常量
	 */
	private final static String[] CHINESE_NUMBER_UNIT = { "分", "角", "元", "拾",
			"佰", "仟", "万", "拾", "佰", "仟", "亿" };

    /**
     * 分转换成元
     * @param moneyFen 分
     * @return String 元
     */
    public static String fen2Yuan(long moneyFen){
        DecimalFormat df = new DecimalFormat(MONEY_FORMAT);
        return df.format(moneyFen / 100.00);
    }

    public static String fen2IntYuan(long moneyFen) {
		DecimalFormat df = new DecimalFormat("0.##");
		return df.format(moneyFen / 100.00);
	}

    /**
     * 厘转元
     * @param moneyLi
     * @return
     */
	public static String li2Yuan(long moneyLi){
        DecimalFormat df = new DecimalFormat(LI_MONEY_FORMAT);
        return df.format(moneyLi / 1000.000);
    }


    public static long yuan2Fen(String moneyYuan){
	    moneyYuan = moneyYuan.replaceAll(",", "");
	    String fStr = moneyYuan.replace('.', ' ');
	    fStr = fStr.replaceAll(" ", "");
	    long money = Long.parseLong(fStr);
	    int pointPosition = 0;
	    if(moneyYuan.indexOf(".") != -1){
	        pointPosition = moneyYuan.length() - moneyYuan.indexOf(".") - 1;
        }
        switch (pointPosition){
            case 0 :
                return money * 100;
            case 1 :
                return money * 10;
            case 2 :
                return money;
            default :
                return Long.parseLong(fStr.substring(0, moneyYuan.indexOf(".") + 2));
        }
    }

    /**
     * 元变成中文
     * @param moneyYuan 元
     * @return
     */
    public static String yuan2Chinese(String moneyYuan){
		String yuan, fen;
		String yuanStr = "", fenStr = "";
        String fuStr=""; //负号，在结果字符串上加一个“负”
		int len = 0;

		/**
		 * 数据准备
		 */
		// 去除千分符","
		moneyYuan = moneyYuan.replaceAll(",", "");

		// 判断输入参数是否为数字
		try {
			Float.parseFloat(moneyYuan);
		} catch (Exception e) {
			return "";
		}

		// 拆分小数位和整数位
		int dot = moneyYuan.indexOf('.');
		if (dot < 0) {
			yuan = moneyYuan;
			fen = "00";
		} else {
			yuan = moneyYuan.substring(0, dot);
			fen = moneyYuan.substring(dot + 1);
		}

		/**
		 * 小数位
		 */
		// 限制小数为两位小数
		if (fen.length() == 1) {
			fen += "0";
		} else {
			fen = fen.substring(0, 2);
		}

		// 如果小数位为零，则显示"整"
		if (Integer.parseInt(fen) != 0) {
			len = fen.length();
			for (int i = 0; i < len; i++) {
				int pos = Integer.parseInt(String.valueOf(fen.charAt(len - 1
						- i)));
				fenStr = CHINESE_NUMBER[pos] + CHINESE_NUMBER_UNIT[i] + fenStr;
			}
		} else {
			fenStr = "整";
		}

		/**
		 * 整数位
		 */
		// 去除负号
		if (yuan.charAt(0) == '-') {
			yuan = yuan.substring(1);
            fuStr="负";
		}

		// 限制最大位数为9
		if (yuan.length() >= 9) {
			yuan = yuan.substring(yuan.length() - 9);
		}

		// 去除前导0字符
		yuan = String.valueOf(Integer.parseInt(yuan));

		len = yuan.length();

		// 如果为0，则显示"零元"
		if (yuan.equals("0")) {
			yuanStr = "零元";
			len = 0;
		}

		// 其他情况
		boolean zeroFlag1 = false; // 个位至千位零标志
		boolean zeroFlag2 = false; // 万位至千万位零标志

		boolean prevIsZero = false;

		for (int i = 0; i < len; i++) {
			int pos = Integer
					.parseInt(String.valueOf(yuan.charAt(len - 1 - i)));
			if (pos != 0) {
				if (i > 0 && i < 5) {
					if (zeroFlag1) {
						yuanStr = CHINESE_NUMBER[pos]
								+ CHINESE_NUMBER_UNIT[i + 2]
								+ CHINESE_NUMBER_UNIT[2] + yuanStr;
					} else {
						yuanStr = CHINESE_NUMBER[pos]
								+ CHINESE_NUMBER_UNIT[i + 2] + yuanStr;
					}
					zeroFlag1 = false;
				} else if (i >= 5 && i < 9) {
					if (zeroFlag2) {
						yuanStr = CHINESE_NUMBER[pos]
								+ CHINESE_NUMBER_UNIT[i + 2]
								+ CHINESE_NUMBER_UNIT[6] + yuanStr;
					} else {
						yuanStr = CHINESE_NUMBER[pos]
								+ CHINESE_NUMBER_UNIT[i + 2] + yuanStr;
					}
					zeroFlag2 = false;
				} else {
					yuanStr = CHINESE_NUMBER[pos] + CHINESE_NUMBER_UNIT[i + 2]
							+ yuanStr;
				}
				prevIsZero = false;
			} else {
				if (i == 0) {
					zeroFlag1 = true;
				}

				if (i == 4) {
					zeroFlag2 = true;
				}

				if (!zeroFlag1 && !zeroFlag2 && !prevIsZero) {
					yuanStr = CHINESE_NUMBER[pos] + yuanStr;
				}
				prevIsZero = true;
			}
		}

		/**
		 * 整合
		 */
		return fuStr + yuanStr + fenStr;
	}


    public static void main(String[] args) {
        System.out.println(fen2Yuan(12000000));
        System.out.println(fen2IntYuan(12000000));
        System.out.println(yuan2Chinese("4328732.321"));
    }
}
