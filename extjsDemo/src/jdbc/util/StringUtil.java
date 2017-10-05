package jdbc.util;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * @Description 字符串工具基础类
	 * stormhua	
	 * @param args
	 * 2014-7-29
	 */
	public static String trimNull(Object obj){
		return trimNull(obj,"");
	}
	
	public static String trimNull(String obj){
		return trimNull(obj,"");
	}
	
	public static String trimNull(Object obj,String defVal){
		if(isBlank(obj)){
			return defVal;
		}else{
			return obj.toString();
		}
	}
	
	public static boolean isNull(Object obj){
		return obj==null;
	}
	
	public static boolean isBlank(Object obj){
		return obj==null||obj.toString().length()<1;
	}
	
	/**
	 * 非null并且长度大于0 by lihl 2015年2月26日 10:44:59
	 * @param obj
	 * @return
	 */
	public static boolean isNotBlank(Object obj){
		return obj!=null && obj.toString().length()>0;
	}
	
	public static String formatString(String src,Object... args){
		return String.format(src, args);
	}
	
	public static String formatText(String src,Object... args){
		return MessageFormat.format(src, args);
	}
	/**
	 * 将unicode类型的字符串转换成中文 by lihl 2014-08-12 09:35:02
	 * @param in 需要转换的char[]
	 * @param off 开始位置
	 * @param len 需要转换的长度
	 * @return
	 */
	public static String fromEncodedUnicode(char[] in, int off, int len) {  
        char aChar;  
        char[] out = new char[len]; // 只短不长  
        int outLen = 0;  
        int end = off + len;  
  
        while (off < end) {  
            aChar = in[off++];  
            if (aChar == '\\') {  
                aChar = in[off++];  
                if (aChar == 'u') {  
                    // Read the xxxx  
                    int value = 0;  
                    for (int i = 0; i < 4; i++) {  
                        aChar = in[off++];  
                        switch (aChar) {  
                        case '0':  
                        case '1':  
                        case '2':  
                        case '3':  
                        case '4':  
                        case '5':  
                        case '6':  
                        case '7':  
                        case '8':  
                        case '9':  
                            value = (value << 4) + aChar - '0';  
                            break;  
                        case 'a':  
                        case 'b':  
                        case 'c':  
                        case 'd':  
                        case 'e':  
                        case 'f':  
                            value = (value << 4) + 10 + aChar - 'a';  
                            break;  
                        case 'A':  
                        case 'B':  
                        case 'C':  
                        case 'D':  
                        case 'E':  
                        case 'F':  
                            value = (value << 4) + 10 + aChar - 'A';  
                            break;  
                        default:  
                            throw new IllegalArgumentException("Malformed \\uxxxx encoding.");  
                        }  
                    }  
                    out[outLen++] = (char) value;  
                } else {  
                    if (aChar == 't') {  
                        aChar = '\t';  
                    } else if (aChar == 'r') {  
                        aChar = '\r';  
                    } else if (aChar == 'n') {  
                        aChar = '\n';  
                    } else if (aChar == 'f') {  
                        aChar = '\f';  
                    }  
                    out[outLen++] = aChar;  
                }  
            } else {  
                out[outLen++] = (char) aChar;  
            }  
        }  
        return new String(out, 0, outLen);  
    }
	/**
	 * 判断字符串是否为数字(浮点数) "^(-?\\d+)(\\.\\d+)?$"
	 * @param str
	 * @return
	 */
	public static  boolean isNumeric(String str){ 
		Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$"); 
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false; 
		} 
		return true; 
	}
}
