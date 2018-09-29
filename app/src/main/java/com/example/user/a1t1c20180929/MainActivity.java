package com.example.user.a1t1c20180929;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import net.alhazmy13.imagefilter.ImageFilter;

public class MainActivity extends AppCompatActivity {

    Bitmap mbitmap;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mbitmap = BitmapFactory.decodeResource(getResources(),R.drawable.a2);
        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransStatus transStatus = new TransStatus();
                transStatus.execute();
            }
        });
    }
    class TransStatus extends AsyncTask<String, Void, String> {
        ProgressDialog asyncDialog = new ProgressDialog(context);

        @Override
        protected String doInBackground(String... strings) {
            for (int i = 0; i < 5; i++) {
                asyncDialog.setProgress(i*30);
                try{
                    Thread.sleep(500);
                } catch (Exception e) {
                    Log.e("TAB",e.toString());
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            asyncDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            asyncDialog.setProgress(ProgressDialog.STYLE_HORIZONTAL);
            asyncDialog.setMessage("변환중입니다.");
            Bitmap bm = ImageFilter.applyFilter(mbitmap, ImageFilter.Filter.RELIEF);
            ImageView iv = (ImageView)findViewById(R.id.imageView);
            iv.setImageBitmap(bm);
            asyncDialog.show();
            super.onPreExecute();
        }
    }
}
