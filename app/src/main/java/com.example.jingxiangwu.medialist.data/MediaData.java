package com.example.jingxiangwu.medialist.data;

/**
 * Created by jingxiang wu on 2016/8/16.
 */
public class MediaData {
    public final static int INDEX_ID = 0;
    public final static int INDEX_NAME = 1;
    public final static int INDEX_DESCRIPTION = 2;
    public final static int INDEX_PRICE = 3;
    public final static int INDEX_DATA = 4;

    public final static String ID = "id";
    public final static String NAME = "name";
    public final static String DESCRIPTION = "description";
    public final static String PRICE = "price";
    public final static String DATA = "createDate";
    public final static String IMAGE = "imageName";

    private int mId;
    private String mName;
    private String mDesc;
    private double mPrice;
    private long mData;
    private String mImage;

    public void setID(int id) {
        mId = id;
    }
    public void setName(String name) {
        mName = name;
    }
    public void setDescriotion(String descrption) {
        mDesc = descrption;
    }
    public void setprice(double price) {
        mPrice = price;
    }
    public void setData(long data) {
        mData = data;
    }
    public void setImage(String image) {
        mImage = image;
    }
    public int getId() {
        return mId;
    }
    public String getName(){
        return mName;
    }
    public String getDescription (){
        return mDesc;
    }
    public double getPrice() {
        return mPrice;
    }
    public long getData() {
        return mData;
    }
    public String getImage() {
        return mImage;
    }
}
