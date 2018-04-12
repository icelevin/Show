package com.icelevin.www.show.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ice on 2017/9/4.
 */

public class CityUtils {
    private static CityUtils cityUtils;
    private Context context;
    private List<Province> provinces;

    public CityUtils(Context context) {
        this.context = context;
        String json = readFile();
        provinces= parse(json);
    }

    private List<Province> parse(String json) {
        Type type=new TypeToken<ArrayList<Province>>(){}.getType();
        ArrayList<Province> jsonObjects=new Gson().fromJson(json,type);
        return jsonObjects;
    }

    //读取文件
    private String readFile() {
        InputStream open = null;
        BufferedReader input = null;
        StringBuilder builder = new StringBuilder();
        try {
            open = context.getAssets().open("city.data.js");

            input = new BufferedReader(new InputStreamReader(open));
            builder = new StringBuilder();
            String str = "";
            while ((str = input.readLine()) != null) {
                builder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (open != null) {
                try {
                    open.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return builder.toString();
    }

    public static class Province implements Serializable {
        private String text;
        private String value;
        private List<City> children;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<City> getChildren() {
            return children;
        }

        public void setChildren(List<City> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "Province{" +
                    "text='" + text + '\'' +
                    ", value='" + value + '\'' +
                    ", children=" + children +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Province) {
                Province p = (Province) obj;
                if (text.equals(p.getText()) && value.equals(p.getValue())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        }
    }

    public static class City implements Serializable {
        private String text;
        private String value;

        public City(String text, String value) {
            this.text = text;
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "City{" +
                    "text='" + text + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
