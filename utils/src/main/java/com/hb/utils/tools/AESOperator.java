package com.hb.utils.tools;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.hb.utils.BaseApplication;

import android.text.TextUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * aes加密要求key为8的倍数
 * @author xianlai
 *
 */
public class AESOperator {
	private static String deviceId = DeviceUtils.getDeviceId(BaseApplication.getAppContext());
	private static String model = TelephoneManager.getInstance().getSystemVersion();
	private static String str = model+deviceId;
	
	static{
		if(str.length() < 9){
			str = str+"abcdef0987654321fedcba";
		}
	}
	
	private static final String sKey = str.substring(0, 16);
	private static final String ivParameter = str.substring(str.length() - 16, str.length());
	private static AESOperator instance = null;

	private AESOperator() {

	}

	public static AESOperator getInstance() {
		if (instance == null)
			instance = new AESOperator();
		return instance;
	}

	// 加密
	public String encrypt(String sSrc) {
		if(TextUtils.isEmpty(sSrc)){
			return "";
		}
		String result = sSrc;
		try {
			Cipher cipher;
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] raw = sKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
			result = new BASE64Encoder().encode(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 此处使用BASE64做转码。
		return result;

	}

	// 解密
	public String decrypt(String sSrc) {
		if(TextUtils.isEmpty(sSrc)){
			return "";
		}
		String originalString = "";
		try {
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			originalString = new String(original, "utf-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			return sSrc;
		}
		return originalString;
	}
}
