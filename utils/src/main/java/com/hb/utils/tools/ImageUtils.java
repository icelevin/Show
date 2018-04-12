package com.hb.utils.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.hb.utils.BaseApplication;

import net.bither.util.NativeUtil;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by txl on 2017/2/18 0018.
 */
public class ImageUtils {
    /**
     * 从相册或相机获取返回的图片url
     *
     * @param data
     * @return
     */
    public String getImageUrlByIntent(Context context, Intent data) {
        if (data == null) {
            return "";
        }

        Uri selectedImage = data.getData();

        if (selectedImage != null) {

            // 获取到了图片
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            return picturePath;

        } else {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return null;
            }
            Bitmap bitmap = (Bitmap) bundle.get("data");

            if (bitmap == null) {
                return null;
            }

            File dirFile = new File(SDCardUtil.getInstance(context).CACHE_FOLDER);

            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            File originalFile = new File(dirFile, "Camera.jpg");
            if (originalFile.exists()) {
                originalFile.delete();
            }

            BufferedOutputStream append = null;
            try {
                append = new BufferedOutputStream(new FileOutputStream(originalFile.getAbsolutePath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, append);
            return originalFile.getAbsolutePath();
        }
    }

    /**
     * 裁剪图片方法实现
     */
    public static String startPhotoZoom(Activity context, String appId, File imageFile, int requestCode) {
        String imagePath = "";

        if (context == null || imageFile == null || !imageFile.exists()) {
            return imagePath;
        }

        //获取裁剪要保存的位置
        imagePath = SDCardUtil.getInstance(context).IMAGE_CACHE_FOLDER + System.currentTimeMillis() + ".jpg";
        File file = new File(imagePath);
        LogUtils.print("文件长度",file.length()+"");
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            Uri zoomUrl = Uri.fromFile(file);

            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            //判断是否是AndroidN以及更高的版本
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                /* 这句要记得写：这是申请权限，之前因为没有添加这个，打开裁剪页面时，一直提示“无法修改低于50*50像素的图片”，
                  开始还以为是图片的问题呢，结果发现是因为没有添加FLAG_GRANT_READ_URI_PERMISSION。
                  如果关联了源码，点开FileProvider的getUriForFile()看看（下面有），注释就写着需要添加权限。
                */
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                //设置裁剪的图片
                Uri contentUri = FileProvider.getUriForFile(context, appId + ".fileProvider", imageFile);
                intent.setDataAndType(contentUri, "image/*");
            } else {
                //设置裁剪的图片
                intent.setDataAndType(Uri.fromFile(imageFile), "image/*");
            }

            //设置裁剪图片要保存的位置
            intent.putExtra(MediaStore.EXTRA_OUTPUT, zoomUrl);

            // 设置裁剪
            intent.putExtra("crop", "true");
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            // outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", false);
            intent.putExtra("noFaceDetection", true);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());


            //将存储图片的uri读写权限授权给剪裁工具应用
            List<ResolveInfo> resInfoList = BaseApplication.getAppContext().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                BaseApplication.getAppContext().grantUriPermission(packageName, zoomUrl, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            context.startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagePath;
    }

    /**
     * 返回裁剪之后的图片数据
     *
     * @param data
     */
    public static Bitmap getZoomImage(Intent data) {
        Bundle extras = data.getExtras();
        if (extras == null) {
            return null;
        }
        return extras.getParcelable("data");
    }


    /**
     * drawable 转 bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        //canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;

    }


    /**
     * 加载一张图片   对其角度 尺寸 大小都进行了优化，然后返回一张小图地址
     *
     * @param context
     * @param path
     * @return
     */
    public static String decodeBitmap(Context context, String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = true;
        // 通过这个bitmap获取图片的宽和高&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);

        float realWidth = options.outWidth;
        float realHeight = options.outHeight;
        LogUtils.print(ImageUtils.class.getName(), "真实图片高度：" + realHeight + "宽度:" + realWidth);
        // 计算缩放比&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        int scale = calculateInSampleSize(options, 300, 300);
        LogUtils.print(ImageUtils.class.getName(), "缩略scale=" + scale);
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;

        //获取图片的角度
        int degree = getRotateImage(path);
        Matrix m = new Matrix();
        // 如果图片角度不对，则需要对图片进行旋转
        if (degree != 0) {
            m.setRotate(degree, (float) options.outWidth / 2, (float) options.outHeight / 2);
        }
        // 注意这次要把options.inJustDecodeBounds 设为 false,这次图片是要读取出来的。&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        bitmap = BitmapFactory.decodeFile(path, options);
        if (bitmap == null) {
            return null;
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        LogUtils.print(ImageUtils.class.getName(), "缩略图高度：" + h + "宽度:" + w);

        //按照角度翻转照片
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);

        //图片质量压缩
        return bitmapCompress(context, bitmap, 50);
    }

    /**
     * 压缩图片,返回byte[],并显示到imageview上
     *
     * @param bitmap
     * @param maxSize 以kb为单位
     * @return String  图片位置
     */
    public static String bitmapCompress(Context context, Bitmap bitmap, int maxSize) {
        if (bitmap == null) {
            return null;
        }

        int tag = 100;
        long size = 0;
        String path = "";
        File file = null;
        do {
            if (tag <= 10) {
                tag = tag - 2;
            } else if (tag <= 20) {
                tag = tag - 5;
            } else {
                tag = tag - 10;
            }

            LogUtils.print(ImageUtils.class.getName(), "图片压缩率当前为=" + tag);
            path = NativeUtil.compressBitmap(context, bitmap, tag);

            file = new File(path);
            size = (int) (file.length() / 1024);
            LogUtils.print(ImageUtils.class.getName(), "图片文件当前大小为=" + size + "kb" + ",length=" + file.length());
        } while (size > maxSize);
        return path;
    }

    /**
     * 获取bitmap的长度
     *
     * @param bitmap
     * @return
     */
    public static long getBitmapsize(Bitmap bitmap) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return bitmap.getByteCount();
        }
        // Pre HC-MR1
        return bitmap.getRowBytes() * bitmap.getHeight();

    }

    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * 从相册获取到的图片有可能角度不正确，所以这里需要计算旋转的角度，如果角度为0则不需要旋转，否则按照角度对图片进行旋转
     *
     * @return
     */
    private static int getRotateImage(String picturePath) {
        ExifInterface exifInterface = null;
        try {
            exifInterface = new ExifInterface(picturePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (exifInterface == null) {
            return 0;
        }
        int tag = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
        switch (tag) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return 90;
            case ExifInterface.ORIENTATION_ROTATE_180:
                return 180;
            case ExifInterface.ORIENTATION_ROTATE_270:
                return -90;
            default:
                return 0;
        }
    }
}
