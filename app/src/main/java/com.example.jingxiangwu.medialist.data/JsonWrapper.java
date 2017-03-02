package com.example.jingxiangwu.medialist.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jingxiang wu on 2016/8/16.
 */
public class JsonWrapper {
private static  final String TAG = "JsonWrapper";
   /* [ {"id":3,
            "name":"三星笔记本",
            "description":"韩国三星笔记本",
            "price":4000.0,
            "createDate":1471280201757},
    {"id":4,
            "name":"Oppo手机",
            "description":"Oppo音乐手机",
            "price":1500.0,
            "createDate":1471280201757
    } ]*/
    //单数据{'singer':{'id':1,'name':'tom','gender':'男'}}
    /*多个数据{'singers':[{'id':'2','name':'tom','gender':'男'},
        {'id':'3','name':'jerry','gender':'男'},
        {'id':'4','name':'jim','gender':'男'},
        {'id':'5','name':'lily','gender':'女'}]}*/

    //解析多个数据的Json
    public ArrayList<MediaData> parseJsonMulti(String strResult) {
        Log.d("wjx"," strResult = "+strResult);
        ArrayList<MediaData> dataList = new ArrayList<MediaData>();
        try {
            JSONArray jsonOb = (JSONArray)(new JSONTokener(strResult)).nextValue();
            for(int i = 0; i < jsonOb.length() ; i++){
                MediaData data = new MediaData();
                JSONObject jsonObj = (JSONObject)jsonOb.get(i);
                int id = jsonObj.getInt(MediaData.ID);
                String name = jsonObj.getString(MediaData.NAME);
                String desc = jsonObj.getString(MediaData.DESCRIPTION);
                double price = jsonObj.getDouble(MediaData.PRICE);
                long makeData = jsonObj.getLong(MediaData.DATA);
                String image = jsonObj.getString(MediaData.IMAGE);
                data.setID(id);
                data.setName(name);
                data.setDescriotion(desc);
                data.setprice(price);
                data.setData(makeData);
                data.setImage(image);
                dataList.add(data);
                String s =  "id:"+id + ", mName：" + name + ",desc：" + desc+ ",price:"+price
                        +",Data:"+makeData;
                Log.d(TAG, "Index:"+i+" | "+ s);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Jsons parse error !", e);
        }
        return dataList;
    }

    public void parseJson(String strResult) {
        try {
            JSONObject jsonObj = new JSONObject(strResult).getJSONObject("singer");
            int id = jsonObj.getInt("id");
            String name = jsonObj.getString("name");
            String gender = jsonObj.getString("gender");
        } catch (JSONException e) {
            System.out.println("Json parse error");
            e.printStackTrace();
        }
    }

    public String packageJson(HashMap<String, String> map) {
        String json;
        JSONArray dataArray = new JSONArray();
        JSONObject data = new JSONObject();
        try {
            data.put(MediaData.ID, map.get(MediaData.ID));
            data.put(MediaData.NAME, map.get(MediaData.NAME));
            data.put(MediaData.PRICE, map.get(MediaData.PRICE));
            data.put(MediaData.DESCRIPTION, map.get(MediaData.DESCRIPTION));
            data.put(MediaData.DATA,map.get(MediaData.DATA));
            dataArray.put(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataArray.toString();
    }

}
