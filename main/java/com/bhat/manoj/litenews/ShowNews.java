package com.bhat.manoj.litenews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowNews extends AppCompatActivity {

    static TextView category,news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);
        category = (TextView) findViewById(R.id.ncategory);
        news = (TextView) findViewById(R.id.theNews
        );
        Bundle bundle = getIntent().getExtras();
        String n = bundle.getString("news");
        String c = bundle.getString("category");
        category.setText(c);
        news.setText(n);
    }
}
