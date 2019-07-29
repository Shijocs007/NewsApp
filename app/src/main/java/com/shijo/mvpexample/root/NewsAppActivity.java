package com.shijo.mvpexample.root;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.shijo.mvpexample.R;

public class NewsAppActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    protected void showProgress(String msg) {
        if (mProgressDialog != null) {
            if (mProgressDialog.isShowing())
                dismissProgress();
            mProgressDialog.setMessage(msg);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }
    }



    protected void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.setCancelable(true);
            mProgressDialog.dismiss();
        }
    }
}
