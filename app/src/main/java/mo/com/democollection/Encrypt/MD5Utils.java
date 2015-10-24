package mo.com.democollection.Encrypt;/**
 * Created by  on
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @创建者 MoMxMo
 * @创时间 2015/10/23:22:41
 * @描述 MD5加密工具类
 * @项目名 DemoCollection
 * @版本 $Rev
 * @更新者 $Author
 * @更新时间 $Date
 * @更新描述 TODO
 */
public class MD5Utils {

    public static String encrypt(String md5_text) throws NoSuchAlgorithmException {
        char hexDigits[] = {'0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] bytes = md5_text.getBytes();
            /*获取MD5摘要算法的MessageDigest对象*/
        MessageDigest md5 = MessageDigest.getInstance("MD5");

            /*使用指定的字节更新摘要*/
        md5.update(bytes);

            /*获取密文*/
        byte[] digest = md5.digest();

        int j = digest.length;
        char str[] = new char[j * 2];
        int k = 0;

        for (int i = 0; i < j; i++) {
            byte b = digest[i];
            str[k++] = hexDigits[b >>> 4 & 0xf];
            str[k++] = hexDigits[b & 0xf];
        }
        return new String(str);
    }
}
