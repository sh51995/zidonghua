package util.jiami;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	public static String toMd5(String s) {
		System.out.println(s);
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
	
	public static void main(String[] args) {
		String sign="amount=3.2&amountSettle=&body=扫描订单&channel=wx_pub&currency=CNY&description=扫描订单&details=[{amount=1.6&innerOrderNo="
				+ "2017090001&virAccNo=0}&{amount=1.6&innerOrderNo=2017090002&virAccNo=0}]&mchNo=100060000000001&"
				+ "notifyUrl=http://test.com&openId=123456&mchOrderNo=2017090001&subject=&timeExpire=&timePaid=&timeSettle=&"
				+ "tradeType=cs.pay.submit&version=2.0";
		sign=sign+"&key=91be991a7491481ab43a89657a780b69";
		System.out.println(sign);
		String s=toMd5("checkDate=2017-09-13&mchNo=100120000000011&tradeType=cs.trade.check&version=2.0&key=91be991a7491481ab43a89657a780b69");
		System.out.println(s);
	}
}
