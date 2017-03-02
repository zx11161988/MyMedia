package com.example.jingxiangwu.medialist;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.jingxiangwu.medialist.data.MediaDataAdapter;
import com.example.jingxiangwu.medialist.ui.MediaDataAddDialog;

import java.util.HashMap;

public class MediaListActivity extends AppCompatActivity {
    public final static int MSG_FAILED_LOAD = 1;
    public final static int MSG_SUCCESS_LOAD = 2;
    private Context mContext ;
    private ListView mListView ;
    private Model mModel;
    private ProgressDialog mProgressDialog;
    public Handler mHandler = new Handler () {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SUCCESS_LOAD:
                    if (mProgressDialog != null) {
                        mProgressDialog.dismiss();
                    }
                break;
                case MSG_FAILED_LOAD:
                    if (mProgressDialog != null) {
                        mProgressDialog.dismiss();
                    }
                    break;
                default:
                    throw new AssertionError(msg.what);
            }
        }
    };

    public static interface Model {
        public void create();
        public void resume();
        public void addMedia(HashMap<String, String> map);
        public void deleteMedia(int id);
        public void pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_media_list);
        mContext = this;
        mListView = (ListView)findViewById(R.id.listView);
        super.onCreate(savedInstanceState);
        MediaDataAdapter adapter = new MediaDataAdapter(this, mHandler);
        mListView.setAdapter(adapter);
        mModel = adapter;
        mModel.create();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_add:
                MediaDataAddDialog dialog = new MediaDataAddDialog();
                dialog.setDataAdapter(mModel);
                dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
                break;
            case R.id.action_delete:
                break;
            default:
        }
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();
        mModel.resume();
        //mProgressDialog = ProgressDialog.show(mContext, "MultiMediaList", "Loading...");

    }

    @Override
    protected  void onPause(){
        super.onPause();
        mModel.pause();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog.hide();

        }
    }
}
