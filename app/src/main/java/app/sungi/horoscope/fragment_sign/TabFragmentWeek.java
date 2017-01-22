package app.sungi.horoscope.fragment_sign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.sungi.horoscope.ListAdapter;
import app.sungi.horoscope.R;
import app.sungi.horoscope.Zodiac;

/**
 * Created by Sungi on 22.01.2017.
 */

public class TabFragmentWeek extends Fragment {

    private List<Zodiac> zodiacList = new ArrayList<>();
    ListAdapter zAdapter;

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
        String infoItems = "Сделайте глубокий вдох, расслабьтесь и поделитесь своими чувствами с каким-нибудь близким Вам человеком. Этого будет вполне достаточно для того, чтобы снять то напряжение, которое Вы будете испытывать в понедельник.\n" +
                "\n" +
                "Во вторник Вы можете получить известие от какого-то близкого Вам человека (возможно, от одного из Ваших родных), которое вновь вызовет у Вас стресс. Просто воспользуйтесь той методикой релаксации, которую Вы применяли накануне. Уже в среду от всех этих переживаний не останется и следа, а четверг получится для Вас просто-таки фантастическим!\n" +
                "\n" +
                "В пятницу Вы будете по уши погружены в работу, но зато выходные обещают получиться для Вас исключительно безмятежными и приятными. В воскресенье посвятите все свои силы и внимание какому-нибудь ";
        Zodiac zodiac = new Zodiac("Овен", "Март 21 - Апрель 20", infoItems, R.drawable.ic_aries);
            zodiacList.add(zodiac);

        zAdapter.notifyDataSetChanged();

    }
}
