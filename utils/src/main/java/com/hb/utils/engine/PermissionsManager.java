package com.hb.utils.engine;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ice on 2017/12/11.
 */

public class PermissionsManager {
    //外部存储
    public final static int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 0x11;
    public final static int WRITE_EXTERNAL_READ_PHONE_STATE_CODE = 0x12;
    public final static int WRITE_EXTERNAL_ACCESS_FINE_LOCATION_CODE = 0x13;
    public final static int WRITE_EXTERNAL_CAMERA = 0x14;

    private Activity context;

    public PermissionsManager(Activity context) {
        this.context = context;
    }

    /**
     * 检查必须权限
     *
     * @return
     */
    public boolean checkPermissions() {
        List<String> permissions = new ArrayList<>();
        // 申请外部储存读写权限
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        // 申请手机状态权限,用来读取手机号码
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add( Manifest.permission.READ_PHONE_STATE);
        }

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissions.add( Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (permissions.size() == 0) {
            return true;
        } else {
            final String[] permissionsArray = new String[permissions.size()];
            for(int i = 0; i < permissions.size(); i++){
                permissionsArray[i] = permissions.get(i);
            }

            showDialog(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(context, permissionsArray, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                }
            });
            return false;
        }
    }

    private void showDialog(final DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("检测应用未获取到正常运行所需的权限，点击确认开启！");
        builder.setNegativeButton("确认", clickListener);
        builder.setCancelable(false);
        builder.show();
    }

    /**
     * 检查位置权限
     */
    public boolean checkLocationPermissions() {
        // 申请高精度定位权限
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, WRITE_EXTERNAL_ACCESS_FINE_LOCATION_CODE);
            return false;
        }
        return true;
    }

    /**
     * 检查相机权限
     */
    public boolean checkCameraPermissions() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA}, WRITE_EXTERNAL_CAMERA);
            return false;
        }
        return true;
    }

    /**
     * 处理权限获取结果
     *
     * @param requestCode
     * @param grantResults
     * @return
     */
    public boolean doNext(int requestCode, int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            //外部存储
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                onBackPressed();
                return false;
            } else {
                return checkPermissions();
            }
        } else if (requestCode == WRITE_EXTERNAL_READ_PHONE_STATE_CODE) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                onBackPressed();
                return false;
            } else {
                return checkPermissions();
            }
        } else if (requestCode == WRITE_EXTERNAL_ACCESS_FINE_LOCATION_CODE) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                return false;
            } else {
                return checkLocationPermissions();
            }
        } else if (requestCode == WRITE_EXTERNAL_CAMERA) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                return false;
            } else {
                return checkCameraPermissions();
            }
        }
        return false;
    }

    public void onBackPressed() {
        if (context != null) {
            Toast.makeText(context, "必须权限被拒绝,程序退出", Toast.LENGTH_LONG).show();
            context.finish();
        }
    }
}
