package com.shijo.mvpexample.newsdetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shijo.mvpexample.R;
import com.shijo.mvpexample.root.NewsAppActivity;

public class NewsDetailsActivity extends NewsAppActivity {

    private ImageView imageView;
    private TextView tvAuther;
    private  TextView tvTittle;
    private TextView tvDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        imageView = (ImageView) findViewById(R.id.image);
        tvAuther = (TextView) findViewById(R.id.auther);
        tvTittle = (TextView) findViewById(R.id.title);
        tvDetails = (TextView) findViewById(R.id.news_details);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {

            Glide.with(this).load(bundle.getString("image", ""))
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);

            tvAuther.setText(bundle.getString("author", ""));
            tvTittle.setText(bundle.getString("title", ""));
            tvDetails.setText(bundle.getString("details", ""));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_basic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
