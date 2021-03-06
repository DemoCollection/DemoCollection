package mo.com.democollection.temp;

import android.content.Context;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;



/**
 * 签名工具类
 * 
 * @author jack
 *
 */
public class SignUtil_lj {
	
	/**
	 * 签名算法
	 */
	//public static final String SIGN_ALGORITHMS = "SHA1withRSA";
	private static String keys = "damailicai_sit.p12";//私钥文件
	private static String pass = "damailicai_sit@2016"; //私钥密码
//	private static String  crt = "fdep.crt"; //服务端证书文件
	private static String  crt = "damailicai_sit.crt"; //服务端证书文件

	public SignUtil_lj() {

	}

	/**
	 * 获取签名
	 * 
	 * @param signStr  待签名字符串
	 * @return
	 * @throws Exception
	 */
	public static String sign(Context context,String signStr){
		
		System.out.println((new StringBuilder()).append("(P2P-->即信端)待签名字符串：").append(signStr)
				.toString());
		String sign = null;
		RSAHelper signer = null;
		try {
			//Signature sig = Signature.getInstance(SIGN_ALGORITHMS);
			
			System.out.println("(P2P-->即信端)获取签名私钥:"+keys);

			InputStream open = context.getAssets().open(keys);
			RSAKeyUtil rsaKey = new RSAKeyUtil(open, pass);
			signer = new RSAHelper(rsaKey.getPrivateKey());
			
		    sign = signer.sign(signStr);
		} catch (Exception e) {
			System.out.println("签名校验异常"+e.getMessage());
		}
		System.out.println((new StringBuilder()).append("(P2P-->即信端)签名:").append(sign)
				.toString());
		return sign;
	}
	
	/**
	 *  签名校验
	 * @param signText  待验的签名
	 * @param dataText  待签名字符串
	 * @return
	 */
	public static boolean verify(Context context,String signText,String dataText){
		signText = signText.replaceAll("[\\t\\n\\r]", "");
		System.out.println((new StringBuilder()).append("(即信端-->P2P)待签名字符串：").append(dataText)
				.toString());
		System.out.println((new StringBuilder()).append("(即信端-->P2P)签名：").append(signText)
				.toString());

		boolean b = false;
		try {
			InputStream open = context.getAssets().open(crt);
			RSAKeyUtil ru = new RSAKeyUtil(open);
			RSAHelper signHelper = new RSAHelper(ru.getPublicKey()); 
			b = signHelper.verify(dataText , signText);
		} catch (Exception e) {
			System.out.println("签名校验异常"+e.getMessage());
		}
		
		return b;
	}

	/**
	 * 拼接字符串
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String mergeMap(Map map) {
//		map = new TreeMap(map);
		String requestMerged = "";
		StringBuffer buff = new StringBuffer();
		Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
		Map.Entry<String, String> entry;
		while (iter.hasNext()) {
			entry = iter.next();
			if (!"SIGN".equalsIgnoreCase(entry.getKey())) {
				if(entry.getValue()==null){
					entry.setValue("");
					buff.append("");
				}else{
						buff.append(String.valueOf(entry.getValue()));
				}
			}
		}
		requestMerged = buff.toString();
		return requestMerged;
	}
}
