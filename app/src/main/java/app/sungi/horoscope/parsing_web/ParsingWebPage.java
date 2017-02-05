package app.sungi.horoscope.parsing_web;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Sungi on 29.01.2017.
 */

public class ParsingWebPage {


    private static String signInfo;
    private static String web;

    public static String getSignInformation(String SignURL) {
        web = SignURL;
        ParsingTask parsingTask = new ParsingTask();
        parsingTask.execute();
        return signInfo;
    }

    public static class ParsingTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... urls) {
            Document doc = null;

            try {
                doc = Jsoup.connect(web).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Elements newsHeadlines = null;
            if (doc != null) {
                newsHeadlines = doc.select(".horoBlock");
            }
            if (newsHeadlines != null) {
                signInfo = newsHeadlines.text();
            }
            Log.d("myLogsssssss", signInfo);
            return null;
        }


    }
}
