package com.training.proj.seldemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    String url = "https://news.google.com/news/?gl=US&ned=us&hl=en";
    String title;
    Elements newsText;
    Elements e1;
    Elements e2;
    List<String> e3;
    List<NewsData> newsDataList;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          e3=new ArrayList<>();
          newsDataList = new ArrayList<>();
        new AsyncCaller().execute(url);
        mDatabase = FirebaseDatabase.getInstance().getReference();

     //   Log.d("body+++++", body);
    }

    private class AsyncCaller extends AsyncTask<String, Void, List<NewsData>>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }
        @Override
        protected List<NewsData> doInBackground(String... params) {

            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here
            try {
                Document doc = Jsoup.connect(url).get();
                title = doc.title();
                newsText = doc.select("a[class=nuEeue hzdq5d ME7ew][aria-level=2]");
                e1 = doc.select("c-wiz[class=M1Uqc kWyHVd]>div[class=a5SXAc iYiEmb]>span[class=oM4Eqe]>span[class=d5kXP YBZVLb]");
              //  e2 = e1.ne
             //    newsText = doc.select(".M1Uqc kWyHVd + span[class=d5kXP YBZVLb]");

                   for(int j = 0;j<newsText.size();j++)    {

                       NewsData n1 = new NewsData();
                       n1.setNewsHeadline(newsText.get(j).text());
                       n1.setNewsSourceURL(newsText.get(j).attr("href"));


                       //////////TO DO SORT HERE//////////////////////////
                        n1.setNewsTime(e1.get(j).text());



                       newsDataList.add(n1);
                   }

                 /*for(Element ele : e1) {*/
                 /*    NewsData n1 = new NewsData();*/
                 /*    n1.setNewsTime(ele.getElementsByClass("d5kXP YBZVLb").text());*/
                 /*   e3.add(ele.getElementsByClass("d5kXP YBZVLb").text());*/
/*
*/

                 /*    newsDataList.add(n1);*/
                 /*}*/






            } catch (IOException e) {
                e.printStackTrace();
            } ;

            return newsDataList;
        }

        @Override
        protected void onPostExecute(List<NewsData> result) {
            super.onPostExecute(result);

          //  Log.d("news+++",String.valueOf(result.size()) );
           for(int i = 0; i<result.size();i++){

               Log.d("news+++",result.get(i).getNewsHeadline());
               Log.d("news++",result.get(i).getNewsSourceURL());
              Log.d("news+",result.get(i).getNewsTime());

             //  Log.d("news+++", result.get(i));

               mDatabase.child("News").setValue(result); //SAVING DATA TO FIREBASE
           }

            //this method will be running on UI thread

        }

    }
}
