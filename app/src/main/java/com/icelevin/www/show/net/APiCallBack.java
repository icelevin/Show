package com.icelevin.www.show.net;

import android.provider.ContactsContract;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hb.utils.net.listener.OnResultListener;
import com.hb.utils.net.okhttp.HttpCodeMessage;
import com.hb.utils.tools.LogUtils;
import com.icelevin.www.show.model.BaseModel;
import com.icelevin.www.show.model.CacheModle;
import com.icelevin.www.show.model.Root;
import com.icelevin.www.show.utils.DBUtils;


import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.FormBody;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

/**
 * Created by ice on 2017/9/25.
 */

public class APiCallBack<T extends BaseModel> implements Callback<String> {
    private OnResultListener<T> listener;
    private Class<T> clz;

    public APiCallBack(Call<String> call, Class<T> clz, OnResultListener<T> listener) {
        this.listener = listener;
        this.clz = clz;
        try {
            Request request = call.request();
            String urlByRequest = APiCallBack.getUrlByRequest(request);
            if (TextUtils.isEmpty(urlByRequest)) {
                return;
            }
            T cache = DBUtils.getCache(urlByRequest, clz);
            if (cache != null && listener != null) {
                listener.onResult(cache);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        T obj = null;
        if (response == null) {
            obj = createObj(response);
            listener.onResult(obj);
            LogUtils.print("--->", "response为空");
            return;
        }

        String str = response.body();
        if (!TextUtils.isEmpty(str))
            str = decode1(str);

        LogUtils.print(APiCallBack.class.getName(), "请求:  " + call.request().url() + "\n响应" + str);

        if (TextUtils.isEmpty(str)) {
            obj = createObj(response);
            listener.onResult(obj);
        } else if (response.isSuccessful()) {
            try {
                obj = new Gson().fromJson(str, clz);
                cache(call.request(), str, obj);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                obj = createObj(response);
            }
            if (listener != null) {
                listener.onResult(obj);
            }
        }
    }

    private void cache(Request request, String str, T obj) {
        String isCache = request.header("isCache");
        if (TextUtils.isEmpty(isCache)) {
            return;
        }

        if (!"000000".equals(obj.getRetcode())) {
            return;
        }
        try {
            BaseModel o = new Gson().fromJson(str, BaseModel.class);
            if (o == null) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        CacheModle cacheModle = new CacheModle();
        String url = getUrlByRequest(request);
        if (TextUtils.isEmpty(url)) {
            return;
        }
        cacheModle.setUrl(url);
        cacheModle.setResponse(str);

        DBUtils.savaCache(cacheModle);
    }

    private T createObj(Response<String> response) {
        T obj = null;
        try {
            obj = clz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (response != null) {
            int code = response.code();
            String message = new HttpCodeMessage().codeHandle(code);
            obj.setRetcode(code + "");
            obj.setMessage(message);
        }
        return obj;
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        T body = createObj(null);
        listener.onResult(body);
        LogUtils.print(APiCallBack.class.getName(), t.getLocalizedMessage());
    }

    public static String getUrlByRequest(Request request) {
        String url = "";
        if (!request.method().equals("GET")) {
            url = request.url().toString();
            return url;
        }
        if (!(request.body() instanceof FormBody)) {
            url = request.url().toString();
            return url;
        }
        FormBody formBody = (FormBody) request.body();
        StringBuilder builder = new StringBuilder();
        builder.append(request.url());
        builder.append("?");
        for (int i = 0; i < formBody.size(); i++) {
            String name = formBody.name(i);
            String value = formBody.value(i);
            //检查是否是分页,如果是分页检查是否是第一页,只缓存第一页
            if ("pageToken".equalsIgnoreCase(name) && !"1".equals(value)) {
                return "";
            }
//        剔除掉时间与签名参数
            if ("timestamp.".equals(name)) {
                continue;
            } else if ("sign".equals(name)) {
                continue;
            }
            builder.append(name + "=" + value);
            builder.append("&");
        }
        url = builder.toString();
        return url;
    }


    static final Pattern reUnicode = Pattern.compile("\\\\u([0-9a-zA-Z]{4})");

    public static String decode1(String s) {
        Matcher m = reUnicode.matcher(s);
        StringBuffer sb = new StringBuffer(s.length());
        while (m.find()) {
            m.appendReplacement(sb,
                    Character.toString((char) Integer.parseInt(m.group(1), 16)));
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
