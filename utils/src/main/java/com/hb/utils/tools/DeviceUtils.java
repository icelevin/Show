package com.hb.utils.tools;

import java.util.UUID;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class DeviceUtils {
	/**
	 * </span>
	 * </p>
	 * * deviceID的组成为：渠道标志+识别符来源标志+hash后的终端识别符
	 * 
	 * 渠道标志为： 1，andriod（a）
	 * 
	 * 识别符来源标志： 1， wifi mac地址（wifi）； 2， IMEI（imei）； 3， 序列号（sn）； 4，
	 * id：随机码。若前面的都取不到时，则随机生成一个随机码，需要缓存。
	 * 
	 * @param context
	 * @return
	 */
	public static String getDeviceId(Context context) {
		StringBuilder deviceId = new StringBuilder();
		// 渠道标志
		try {

			// IMEI（imei）
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			String imei = tm.getDeviceId();
			if (!TextUtils.isEmpty(imei)) {
				deviceId.append("IMEI");
				deviceId.append(imei);
				return deviceId.toString();
			}
			// 序列号（sn）
			String sn = tm.getSimSerialNumber();
			if (!TextUtils.isEmpty(sn)) {
				deviceId.append("SN");
				deviceId.append(sn);
				return deviceId.toString();
			}

			// wifi mac地址
			WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = wifi.getConnectionInfo();
			String wifiMac = info.getMacAddress();
			if (!TextUtils.isEmpty(wifiMac)) {
				deviceId.append("WIFI");
				deviceId.append(wifiMac);
				return deviceId.toString();
			}

			// 如果上面都没有， 则生成一个id：随机码
			String uuid = getUUID(context);
			if (!TextUtils.isEmpty(uuid)) {
				deviceId.append("ID");
				deviceId.append(uuid);
				return deviceId.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			deviceId.append("id").append(getUUID(context));
		}
		return deviceId.toString();
	}

	/**
	 * 得到全局唯一UUID
	 */
	public static String getUUID(Context context) {
		//极光推送不允许别名出现-这个字符
		return UUID.randomUUID().toString().replace("-", "");
	}
}
