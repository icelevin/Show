package com.hb.utils.tools;

import java.lang.Thread.UncaughtExceptionHandler;


/**
 * 
*    
* 项目名称：CEB_insurance   
* 类名称：MyExceptionHandler   
* 类描述：   整个app未抓取异常的处理者，保存异常信息，同时做出相应响应，提升用户体验
* 创建人：txl   
* 创建时间：2014-4-16 下午12:54:55   
* 修改人：txl   
* 修改时间：2014-4-16 下午12:54:55   
* 修改备注：   
* @version    
*
 */
public class MyBaseExceptionHandler implements UncaughtExceptionHandler {
	/** 系统默认的UncaughtException处理类 */
    private UncaughtExceptionHandler mDefaultHandler;
    
    public MyBaseExceptionHandler(){
    	mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }
	
	/**
	 * 处理未抓到的异常
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		
		handleException(ex);
	}
	
	private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        
        ex.printStackTrace();
//        
//        //从异常对象中整理出异常报告
//        final String crashReport = getCrashReport(ex);
//        if (LogUtil.isDebug()) {
//            AppException.saveErrorLog(crashReport);
//        }
//        // 上传服务器，退出应用
//
////        StatisticsManager.sendStatistics(new ErrorTask(BaseApplication.getInstance(), crashReport,
////                0));
//       
//        
//        //重启应用并将日志上传至服务器
//      //自杀
		android.os.Process.killProcess(android.os.Process.myPid());
        
        return true;
    }
	
	
	 /**
     * 获取APP崩溃异常报告
     * 
     * @param ex
     * @return
     */
//    private String getCrashReport(Throwable ex) {
//        StringBuffer exceptionStr = new StringBuffer();
//        PackageInfo pinfo = MyApplication.getInstance().getLocalPackageInfo();
//        if (pinfo != null) {
//            exceptionStr.append("Version: " + pinfo.versionName + "(" + pinfo.versionCode + ")\n");
//            exceptionStr.append("Android: " + android.os.Build.VERSION.RELEASE + "("
//                    + android.os.Build.MODEL + ")\n");
//            if (ex != null) {
//                String errorStr = ex.getLocalizedMessage();
//                if (TextUtils.isEmpty(errorStr)) {
//                    errorStr = ex.getMessage();
//                }
//                if (TextUtils.isEmpty(errorStr)) {
//                    errorStr = ex.toString();
//                }
//                exceptionStr.append("Exception: " + errorStr + "\n");
//                StackTraceElement[] elements = ex.getStackTrace();
//                if(elements != null){
//                    for (int i = 0; i < elements.length; i++) {
//                        exceptionStr.append(elements[i].toString() + "\n");
//                    }
//                }
//            } else {
//                exceptionStr.append("no exception. Throwable is null\n");
//            }
//            return exceptionStr.toString();
//        } else {
//            return "";
//        }
//    }
	
}
