package app.sungi.horoscope.fragment_sign;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.sungi.horoscope.ListAdapter;
import app.sungi.horoscope.R;
import app.sungi.horoscope.Zodiac;

/**
 * Created by Sungi on 22.01.2017.
 */

public class TabFragmentWeek extends Fragment {
    String signName;
    private List<Zodiac> zodiacList = new ArrayList<>();
    ListAdapter zAdapter;
    String infoItems;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        signName = bundle.getString("SIGN");
        Log.d("myLogssssssssssssss", signName);
        infoItems = getSignInformationFromWebSite();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_list_rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        zAdapter = new ListAdapter(zodiacList);
        recyclerView.setAdapter(zAdapter);
        prepareZodiacData();
        return view;
    }

    private void prepareZodiacData() {
        Zodiac zodiac = new Zodiac(signName, "...", infoItems, R.drawable.ic_favorite);
        zodiacList.add(zodiac);
        zAdapter.notifyDataSetChanged();
    }

    String getSignInformationFromWebSite() {
        String webSite = null;
        switch (signName) {
            case "Овен":
                webSite = "http://orakul.com/horoscope/astrologic/general/aries/week.html";
                break;
            case "Телец":
                webSite = "http://orakul.com/horoscope/astrologic/general/taurus/week.html";
                break;
            case "Близнецы":
                webSite = "http://orakul.com/horoscope/astrologic/general/gemini/week.html";
                break;
            case "Рак":
                webSite = "http://orakul.com/horoscope/astrologic/general/cancer/week.html";
                break;
            case "Лев":
                webSite = "http://orakul.com/horoscope/astrologic/general/lion/week.html";
                break;
            case "Дева":
                webSite = "http://orakul.com/horoscope/astrologic/general/virgo/week.html";
                break;
            case "Весы":
                webSite = "http://orakul.com/horoscope/astrologic/general/libra/week.html";
                break;
            case "Скорпион":
                webSite = "http://orakul.com/horoscope/astrologic/general/scorpio/week.html";
                break;
            case "Стрелец":
                webSite = "http://orakul.com/horoscope/astrologic/general/sagittarius/week.html";
                break;
            case "Козерор":
                webSite = "http://orakul.com/horoscope/astrologic/general/capricorn/week.html";
                break;
            case "Водолей":
                webSite = "http://orakul.com/horoscope/astrologic/general/aquarius/week.html";
                break;
            case "Рыбы":
                webSite = "http://orakul.com/horoscope/astrologic/general/pisces/week.html";
                break;

        }
        return ParsingWebPage.getSignInformation(webSite);
    }

    public static class ParsingWebPage {


          static String signInfo;
          static String web;

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
}
