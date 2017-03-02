package com.example.jingxiangwu.medialist.data;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.jingxiangwu.medialist.MediaListActivity;
import com.example.jingxiangwu.medialist.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jingxiang wu on 2016/8/14.
 */
public class MediaDataAdapter extends BaseAdapter implements MediaListActivity.Model, OkHttp.RequestListener {
    private final static String TAG = "MediaDataAdapter";
    private Activity mContext;
    private LayoutInflater mInflater;
    private JsonWrapper mJsonWrapper;
    private OkHttp mOkHttp;
    private ArrayList<MediaData> mMediaDatas;
    private Handler mHandler;
    public final class ViewHolder{
        public TextView id;
        public TextView type;
        public TextView   title;
        public TextView image;
        public CheckBox  checkbox;
    }

    public MediaDataAdapter(Activity context, Handler handler) {
        mContext = context;
        mHandler = handler;
        mInflater = LayoutInflater.from(mContext);
        mJsonWrapper = new JsonWrapper();
        mOkHttp = new OkHttp();
        mOkHttp.setListener(this);
    }


    @Override
    public int getCount() {
        if (mMediaDatas != null) {
            return mMediaDatas.size();
        } else {
            return 0;
        }

    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        Log.d("wjx", "I = "+i+"  View = "+view);
        if(view == null) {
            view = mInflater.inflate(R.layout.media, null);
            Log.d("wjx", "View = "+view);
            holder = new ViewHolder();
            holder.id = (TextView)view.findViewById(R.id.media_id);
            holder.type = (TextView)view.findViewById(R.id.media_type);
            holder.title = (TextView)view.findViewById(R.id.media_title);
            holder.checkbox = (CheckBox)view.findViewById(R.id.media_checkBox);
            holder.image = (TextView)view.findViewById(R.id.media_image);
            view.setTag(holder);
        }

        if (mMediaDatas != null) {
            holder = (ViewHolder)view.getTag();
            //String[] data = getData(i);
            MediaData data = mMediaDatas.get(i);
            holder.id.setText(""+data.getId());
            holder.type.setText(data.getDescription());
            holder.title.setText(data.getName());
            holder.image.setText(data.getImage());
        }
        return view;
    }

    @Override
    public void create() {
        mOkHttp.createRequest();
    }

    @Override
    public void resume() {
    }
    @Override
    public void addMedia(HashMap<String, String> map) {
        //mOkHttp.
        //String data = mJsonWrapper.packageJson(map);
        mOkHttp.addData(map);
    }
    @Override
    public void deleteMedia(int id) {

    }
    @Override
    public void pause() {
        mOkHttp.pauseRequest();
    }

    @Override
    public void finish(boolean successful, String data) {
        if(successful) {
            Log.d(TAG," finish : "+data);
            mMediaDatas = mJsonWrapper.parseJsonMulti(data);
            this.notifyDataSetChanged();
            mHandler.sendEmptyMessage(MediaListActivity.MSG_SUCCESS_LOAD);
        } else {
            mHandler.sendEmptyMessage(MediaListActivity.MSG_FAILED_LOAD);
        }
    }
}
