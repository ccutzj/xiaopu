package com.zzkj.xiaopu.utils;

import org.springframework.util.DigestUtils;

import java.util.Random;
import java.util.UUID;


public class ByteUtil {
	public static int bytesToInt(byte[] bRefArr) {
	    int iOutcome = 0;
	    byte bLoop;
	 
	    for (int i = 0; i < bRefArr.length; i++) {
	        bLoop = bRefArr[i];
	        iOutcome = (bLoop & 0xFF) << (8 * i);
	    }
	    return iOutcome;
	}
	public static final String bytesToHexString(byte[] bArray) {
	      StringBuffer sb = new StringBuffer(bArray.length);
	      String sTemp;
	      for (int i = 0; i < bArray.length; i++ ) {
	       sTemp = Integer.toHexString(0xFF & bArray[i]);
	       if (sTemp.length() < 2)
	        sb.append(0);
	       sb.append(sTemp.toUpperCase());
	      }
	      return sb.toString();
	     }
	 
	// byte数字转字符串
	public static String printHexString(byte[] b) {
	    String hexs = "";
	    for (int i = 0; i < b.length; i++ ) {
	 
	        String hex = Integer.toHexString(b[i] & 0xFF);
	        if (hex.length() == 1) {
	            hex = '0'+hex;
	        }
	        hexs = hex;
	 
	    }
	    return hexs;
	}
	 
	/*
	 * 16进制字符串转整形
	 */
	public static int OxStringtoInt(String ox) throws Exception {
	    ox = ox.toLowerCase();
	    if (ox.startsWith("0x")) {
	        ox = ox.substring(2, ox.length());
	    }
	    Integer ri = 0;
	    Integer oxlen = ox.length();
	    for (int i = 0; i < oxlen; i++) {
	        char c = ox.charAt(i);
	        int h;
	        if (('0' <= c && c <= '9')) {
	            h = c - 48;
	        } else if (('a' <= c && c <= 'f')) {
	            h = c - 87;
	 
	        } else if ('A' <= c && c <= 'F') {
	            h = c - 55;
	        } else {
	            throw (new Exception("not a integer "));
	        }
	        byte left = (byte) ((oxlen - i - 1) * 4);
	        ri |= (h << left);
	    }
	    return ri;
	 
	}
	 
	// 16进制转字符串
	public static String hexString2String(String src) {
	    String temp = "";
	    for (int i = 0; i < src.length() / 2; i++) {
	        temp = temp+ (char) Integer.valueOf(src.substring(i * 2, i * 2+2),
	                        16).byteValue();
	    }
	    return temp;
	}
	 
	// 整数转byte数组
	public static byte[] intTobytes(int var) {
	    byte[] b = new byte[2];
	    // b[0]=(byte)(var>>24);
	    // b[1]=(byte)(var>>16);
	    // b[2]=(byte)(var>>8);
	    // b[3]=(byte)(var);
	    b[0] = (byte) (var >> 8);
	    b[1] = (byte) (var);
	    return b;
	}
	 
	/**
	  * 生成16进制累加和校验码
	  *
	  * @param data 除去校验位的数据
	  * @return
	  */
	 public static String makeChecksum(String data) {
	     if (data.equals("")) {
	         return "";
	     }
	     int total = 0;
	     int len = data.length();
	     int num = 0;
	     while (num < len) {
	         String s = data.substring(num, num+2);
	         total = Integer.parseInt(s, 16);
	         num = num+2;
	     }
	     /**
	      * 用256求余最大是255，即16进制的FF
	      */
	     int mod = total;
	     String hex = Integer.toHexString(mod & 0xFF);
	     len = hex.length();
	     //如果不够校验位的长度，补0,这里用的是两位校验
	     if (len < 2) {
	         hex = "0"+hex;
	     }
	     return hex;
	 }
	 /** 
	   * 16进制字符串转换成byte数组
	   * @param
	   * @return 转换后的byte数组 
	   */  
	    public static byte[] hex2Byte(String hex) {
	    String digital = "0123456789ABCDEF";
	    char[] hex2char = hex.toCharArray();  
	    byte[] bytes = new byte[hex.length() / 2];  
	    int temp;  
	    for (int i = 0; i < bytes.length; i++ ) {  
	    // 其实和上面的函数是一样的 multiple 16 就是右移4位 这样就成了高4位了  
	    // 然后和低四位相加， 相当于 位操作"|"   
	    //相加后的数字 进行 位 "&" 操作 防止负数的自动扩展. {0xff byte最大表示数}  
	        temp = digital.indexOf(hex2char[2 * i]) * 16;  
	        temp += digital.indexOf(hex2char[2 * i]);  
	        bytes[i] = (byte) (temp & 0xff);  
	    }  
	    return bytes;  
	}
	    
	    public static byte[] hexStringToByteArray(String s) {
	        int len = s.length();
	        byte[] b = new byte[len / 2];
	        for (int i = 0; i < len; i += 2) {
	            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
	            b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
	                    .digit(s.charAt(i + 1), 16));
	        }
	        return b;
	    }
	    
	    public static byte[] toByteArray(String hexString) {
	         
	        hexString = hexString.toLowerCase();
	        final byte[] byteArray = new byte[hexString.length() / 2];
	        int k = 0;
	        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
	            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
	            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
	            byteArray[i] = (byte) (high << 4 | low);
	            k += 2;
	        }
	        return byteArray;
	    }
	    
	    /**
	     * 字符串转化成为16进制字符串
	     * @param s
	     * @return
	     */
	    public static String strTo16(String s) {
	        String str = "";
	        for (int i = 0; i < s.length(); i++) {
	            int ch = (int) s.charAt(i);
	            String s4 = Integer.toHexString(ch);
	            str = str + s4;
	        }
	        return str;
	    }
	    public static String getOrderIdByUUId() {
	        int first = new Random(10).nextInt(8) + 1;
	        System.out.println(first);
	        int hashCodeV = UUID.randomUUID().toString().hashCode();
	        if (hashCodeV < 0) {//有可能是负数
	            hashCodeV = -hashCodeV;
	        }
	        // 0 代表前面补充0
	        // 4 代表长度为4
	        // d 代表参数为正数型
	        return first + String.format("%015d", hashCodeV);
	    }
	    private static final String slat = "123456";
	    
	    /**
		 * 生成md5
		 * @param
		 * @return
		 */
		public static String getMD5(String str) {
			String base = str ;
			String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
			return md5;
		}
}
