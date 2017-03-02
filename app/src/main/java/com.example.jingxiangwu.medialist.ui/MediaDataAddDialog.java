/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jingxiangwu.medialist.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.jingxiangwu.medialist.MediaListActivity;
import com.example.jingxiangwu.medialist.R;

import java.util.HashMap;

public class MediaDataAddDialog extends DialogFragment implements View.OnClickListener {
    private EditText mEditText_ID;
    private EditText mEditText_Name;
    private EditText mEditText_Price;
    private EditText mEditText_Desc;
    private EditText mEditText_Data;

    private MediaListActivity.Model mModel;
    private HashMap<String, String> mHashMap = new HashMap<String, String>();
    public void setDataAdapter(MediaListActivity.Model model) {
        mModel = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.media_add_dialog, container);

        ///M: some times, getUserPresetsAdapter return null, so move it to onClick
        //mAdapter = activity.getUserPresetsAdapter();
        mEditText_Name = (EditText) view.findViewById(R.id.edit_name);
        mEditText_ID = (EditText)view.findViewById(R.id.edit_id);
        mEditText_Desc = (EditText)view.findViewById(R.id.edit_description);
        mEditText_Data = (EditText)view.findViewById(R.id.edit_data);
        mEditText_Price = (EditText)view.findViewById(R.id.edit_price);
        view.findViewById(R.id.cancel).setOnClickListener(this);
        view.findViewById(R.id.ok).setOnClickListener(this);
        getDialog().setTitle("ttt");
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                dismiss();
                break;
            case R.id.ok:
                mHashMap.put("id", String.valueOf(mEditText_ID.getText()));
                mHashMap.put("name",String.valueOf(mEditText_Name.getText()));
                mHashMap.put("price", String.valueOf(mEditText_Price.getText()));
                mHashMap.put("description",String.valueOf(mEditText_Desc.getText()));
                mHashMap.put("createDate", String.valueOf(mEditText_Data.getText()));
                mModel.addMedia(mHashMap);
                dismiss();
                break;
        }
    }
}
