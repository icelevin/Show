/*
 * Copyright 2014 http://Bither.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.bither.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;


import com.hb.utils.tools.SDCardUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * 使用libjpeg来进行压缩图片
 * 
 * 为什么Android的图片质量会比iPhone的差？
 * 就是因为google在调用jpeg的compressBitmap方法时，默认设置了optimize_coding为false
 * ，而现设备硬件的不断更新，压缩所需要的时间与空间足够，完全可以设为true
 * 详情见：http://www.cnblogs.com/MaxIE/p/3951294.html
 * 
 * @author 69095
 * 
 */

public class NativeUtil {
	private static int DEFAULT_QUALITY = 95;

	public static void compressBitmap(Bitmap bit, String fileName, boolean optimize) {
		compressBitmap(bit, DEFAULT_QUALITY, fileName, optimize);

	}

	/**
	 * 真正压缩图片的方法，如果图片不是argb8888的则重新进行创建
	 * @param bit
	 * @param quality
	 * @param fileName
	 * @param optimize
	 */
	public static void compressBitmap(Bitmap bit, int quality, String fileName, boolean optimize) {
		Log.d("native", "compress of native");

		if (bit.getConfig() != Config.ARGB_8888) {
			Bitmap result = null;
			result = Bitmap.createBitmap(bit.getWidth(), bit.getHeight(), Config.ARGB_8888);
			Canvas canvas = new Canvas(result);
			Rect rect = new Rect(0, 0, bit.getWidth(), bit.getHeight());// original
			canvas.drawBitmap(bit, null, rect, null);
			saveBitmap(result, quality, fileName, optimize);
			result.recycle();
		}else{
			saveBitmap(bit, quality, fileName, optimize);
		}
	}

	private static void saveBitmap(Bitmap bit, int quality, String fileName, boolean optimize) {
		compressBitmap(bit, bit.getWidth(), bit.getHeight(), quality, fileName.getBytes(), optimize);
	}

	/**
	 * 压缩图片，返回压缩后图片的位置
	 * 
	 * @param bit
	 * @param quality
	 * @return
	 */
	public static String compressBitmap(Context context, Bitmap bit, int quality) {

		File dirFile = new File(SDCardUtil.getInstance(context).IMAGE_CACHE_FOLDER);

		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		File originalFile = new File(dirFile, System.currentTimeMillis()+".jpg");
		if(originalFile.exists()){
			originalFile.delete();
		}

		compressBitmap(bit, quality, originalFile.getAbsolutePath(), true);

		return originalFile.getAbsolutePath();
	}

	private static byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		return baos.toByteArray();
	}

	/**
	 * 
	 * @param bit
	 * @param w
	 * @param h
	 * @param quality
	 * @param fileNameBytes
	 * @param optimize
	 *            如果设置optimize_coding为TRUE，将会使得压缩图像过程中基于图像数据计算哈弗曼表（关于图片压缩中的哈弗曼表，
	 *            请自行查阅相关资料），由于这个计算会显著消耗空间和时间，默认值被设置为FALSE。
	 * @return
	 */
	private static native String compressBitmap(Bitmap bit, int w, int h, int quality, byte[] fileNameBytes, boolean optimize);

	static {
		System.loadLibrary("jpegbither");
		System.loadLibrary("bitherjni");
	}
}
