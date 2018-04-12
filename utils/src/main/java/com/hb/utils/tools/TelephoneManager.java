package com.hb.utils.tools;


import android.content.Context;
import android.telephony.TelephonyManager;

import com.hb.utils.BaseApplication;


/**
 * 手机设备管理器
 * @author txl
 *
 */
public class TelephoneManager {
	private static TelephoneManager telephoneManager;
	
	public static TelephoneManager getInstance(){
		if(telephoneManager == null){
			telephoneManager = new TelephoneManager();
		}
		return telephoneManager;
	}
	
	/**
	 * 获取系统版本
	 * @return
	 */
	public String getSystemVersion(){
		LogUtils.print(this.getClass().getName(),"获取系统版本="+android.os.Build.VERSION.RELEASE);
		return  android.os.Build.VERSION.RELEASE;
	}
	
	/**
	 * 获取手机型号
	 * @return
	 */
	public String getTelephoneModel(){
		LogUtils.print(this.getClass().getName(),"获取手机型号="+android.os.Build.MODEL);
		return android.os.Build.MODEL;
	}
	
	/**
	 * 获取手机号码
	 * @return
	 */
	public String getTelephoneNumber(){
		TelephonyManager phoneMgr = (TelephonyManager) BaseApplication.getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
		LogUtils.print(this.getClass().getName(),"获取手机号码="+phoneMgr.getLine1Number());
		return phoneMgr.getLine1Number();
	}
}
