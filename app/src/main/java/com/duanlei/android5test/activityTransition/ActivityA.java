package com.duanlei.android5test.activityTransition;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.duanlei.android5test.R;

/**
 * Author: duanlei
 * Date: 2016-01-19
 */
public class ActivityA extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testa);
    }

    private Intent mIntent;

    //设置不同动画效果
    public void explode(View view) {
        mIntent = new Intent(this, ActivityB.class);
        mIntent.putExtra("flag", 0);
        startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void slide(View view) {
        mIntent = new Intent(this, ActivityB.class);
        mIntent.putExtra("flag", 1);
        startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void fade(View view) {
        mIntent = new Intent(this, ActivityB.class);
        mIntent.putExtra("flag",2);
        startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void share(View view) {
        View fab = findViewById(R.id.fab_button);
        mIntent = new Intent(this, ActivityB.class);
        mIntent.putExtra("flag", 3);


        //创建多个共享元素
        startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(
                this, Pair.create(view, "share"), Pair.create(fab, "fab")
        ).toBundle());
    }




}
