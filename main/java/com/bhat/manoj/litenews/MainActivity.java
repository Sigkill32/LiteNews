package com.bhat.manoj.litenews;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {

    String url,category;
    TextView textView;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView2);
        textView.setTextColor(Color.parseColor("black"));
    }

    public  void national(View view) {
        url="http://indiatoday.intoday.in/section/114/1/india.html";
        category="National News";
        new Shit().execute();
    }

    public void world(View view) {
        url="http://indiatoday.intoday.in/section/113/1/world.html";
        category="International News";
        new Shit().execute();
    }

    public void sports(View view) {
        url="http://indiatoday.intoday.in/section/84/1/sports.html";
        category="Sports";
        new Shit().execute();
    }

    private class Shit extends AsyncTask<Void,Void,Void> {
        String text,head,report,news="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Lite News");
            progressDialog.setMessage("Fetching Data, Please wait....");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect(url).get();
                Elements div = document.select("div.innerbox");
                for(Element p: div) {
                    head = p.getElementsByTag("a").text();
                    report = ">>"+p.getElementsByTag("p").text();
                    news+=head+"\n"+report+"\n\n\n";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            Intent intent = new Intent(MainActivity.this, ShowNews.class);
            intent.putExtra("category", category);
            intent.putExtra("news", news);
            startActivity(intent);
        }
    }
}
