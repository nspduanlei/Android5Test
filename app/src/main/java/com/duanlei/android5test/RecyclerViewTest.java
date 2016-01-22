package com.duanlei.android5test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: duanlei
 * Date: 2016-01-19
 */
public class RecyclerViewTest extends Activity {
    
    private RecyclerView mRcList;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Spinner mSpinner;
    private List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);

        mRcList = (RecyclerView) findViewById(R.id.rc_list);
        mLayoutManager = new LinearLayoutManager(this);
        mRcList.setLayoutManager(mLayoutManager);
        mRcList.setHasFixedSize(true);

        //设置显示的动画
        mRcList.setItemAnimator(new DefaultItemAnimator());

        mSpinner = (Spinner) findViewById(R.id.spinner);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    //设置为线性布局
                    mRcList.setLayoutManager(new LinearLayoutManager(RecyclerViewTest.this));
                } else if (position == 1){
                    //设置为表格布局
                    mRcList.setLayoutManager(new GridLayoutManager(RecyclerViewTest.this, 3));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //添加测试数据
        for (int i = 0; i < 10; i++) {
            mData.add("Recycler");
        }

        mAdapter = new RecyclerAdapter(mData);
        mRcList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final View view, int position) {
                //设置点击动画

                view.animate().translationZ(15F).setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);

                                view.animate().translationZ(1f).setDuration(500).start();
                            }
                        }).start();
            }
        });
    }

    public void addRecycler(View view) {
        mData.add("Recycler");
        mAdapter.notifyDataSetChanged();
    }

    public void delRecycler(View view) {
        int position = mData.size();
        if (position > 0) {
            mData.remove(position - 1);
            mAdapter.notifyDataSetChanged();
        }
    }



}
