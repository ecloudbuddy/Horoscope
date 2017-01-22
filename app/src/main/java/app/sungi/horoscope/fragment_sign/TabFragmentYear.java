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
 * Created by Julia on 22.01.2017.
 */

public class TabFragmentYear extends Fragment {

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
                String infoItems ="Начало 2017 года получится для Вас не самым простым, и в середине января Вам будет трудно обрести твердую почву под ногами. Однако не переживайте, ведь несмотря на то, что у Вас будут какие-то обязанности (вероятно, семейного характера), которые будут самым непосредственным образом конфликтовать с теми событиями, которых Вы так давно ждали, Вы сможете найти компромиссное решение этой дилемме. Нужно лишь немного постараться. Дайте другим людям возможность поделиться с Вами своими идеями, и они могут предложить Вам нечто очень конструктивное.\n" +
                        "\n" +
                        "Начало апреля станет для Вас очень удачной порой перемен. Вы сможете продуктивно поработать над собой и своим образом и заменить негативные мысли на позитивные. Если, конечно, Вы будете готовы приложить к этому должные усилия со своей стороны. К счастью, Вы никогда не боялись сложных задач и с готовностью за нее возьметесь.\n" +
                        "\n" +
                        "Конец декабря станет для Вас полной противоположностью изматывающего начала года. Благодаря влиянию благоприятных Марса и Нептуна Вы сможете без труда добиться осуществления любых Ваших желаний. Все будет само идти к Вам в руки, но, несмотря на столь явную благосклонность судьбы, Вам не стоит принимать свою удачу как должное.";
                Zodiac zodiac = new Zodiac("Овен", "Март 21 - Апрель 20", infoItems, R.drawable.ic_aries);
        zodiacList.add(zodiac);
        zAdapter.notifyDataSetChanged();
    }
}

