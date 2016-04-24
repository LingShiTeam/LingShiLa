package com.bigkoo.pickerview;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by lanmang on 2016/4/21.
 */
public class CityPicker {
    private String province;//省
    private String city;//市
    private String country;//县
    OptionsPickerView pvOptions;
    public ArrayList<ProvinceBean> options1Items;//第一层数据
    public ArrayList<ArrayList<String>> options2Items;//第二层数据
    public ArrayList<ArrayList<ArrayList<String>>> options3Items;//第三层数据
    private boolean isLoadCities = false;

    private static class LazyHolder {

        private static final CityPicker INSTANCE = new CityPicker();
    }

    private CityPicker() {

    }

    private static CityPicker getInstance() {
        return LazyHolder.INSTANCE;
    }

    private void loadCities(Context context) {

        if (!isLoadCities) {
            isLoadCities = true;

            options1Items = new ArrayList<ProvinceBean>();
            options2Items = new ArrayList<ArrayList<String>>();
            options3Items = new ArrayList<ArrayList<ArrayList<String>>>();

            int position = 0;
            ProvinceBean provinceBean;
            ArrayList<String> list1 = null;
            ArrayList<String> list2_2 = null;
            ArrayList<ArrayList<String>> list2 = null;
            InputStream is = null;

            try {
                is = context.getAssets().open("fullcities.xml");
                XmlPullParser parser = Xml.newPullParser();
                parser.setInput(is, "utf-8");
                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            if (parser.getName().equals("province")) {
                                String province = parser.getAttributeValue(1);
                                provinceBean = new ProvinceBean(position++, province, "", "");
                                options1Items.add(provinceBean);
                                list1 = new ArrayList<>();
                                list2 = new ArrayList<ArrayList<String>>();

                            } else if (parser.getName().equals("city")) {
                                String city = parser.getAttributeValue(1);
                                list1.add(city);
                                list2_2 = new ArrayList<>();

                            } else if (parser.getName().equals("county")) {
                                String country = parser.getAttributeValue(1);
                                list2_2.add(country);
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("province")) {
                                options2Items.add(list1);
                                options3Items.add(list2);
                            } else if (parser.getName().equals("city")) {
                                list2.add(list2_2);
                            }
                            break;
                    }
                    eventType = parser.next();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    public static void set(Context context, City city) {
        CityPicker.getInstance().setPicker(context, city);
    }

    private void setPicker(Context context, final City city) {

        loadCities(context);

        //选项选择器
        pvOptions = new OptionsPickerView(context);

        //三级联动效果
        pvOptions.setPicker(options1Items, options2Items, options3Items, true);
        //设置选择的三级单位
//        pwOptions.setLabels("省", "市", "区");
        pvOptions.setTitle("选择城市");
        pvOptions.setCyclic(false, false, false);//设置是否循环滚动
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(0, 0, 0);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                province = options1Items.get(options1).getPickerViewText();
                CityPicker.this.city = options2Items.get(options1).get(option2);
                country = options3Items.get(options1).get(option2).get(options3);

                city.getData(province, CityPicker.this.city, country);
            }
        });

        pvOptions.show();

    }

    public static abstract class City {
        public abstract void getData(String province, String city, String country);
    }

}
