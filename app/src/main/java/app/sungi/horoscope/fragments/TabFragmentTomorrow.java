package app.sungi.horoscope.fragments;

/**
 * Created by Sungi on 16.01.2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.sungi.horoscope.ListAdapter;
import app.sungi.horoscope.R;
import app.sungi.horoscope.RecyclerTouchListener;
import app.sungi.horoscope.Zodiac;
import app.sungi.horoscope.activities.OneSignActivity;


public class TabFragmentTomorrow extends Fragment {

    private static final String LOG_TAG = "myLogsFragment";
    private List<Zodiac> zodiacList = new ArrayList<>();
    ListAdapter zAdapter;

    String[] nameItems = {"Овен", "Телец", "Близнецы",
            "Рак", "Лев", "Дева",
            "Весы", "Скорпион", "Стрелец",
            "Козерог", "Водолей", "Рыбы"};
    String[] dateItems = {"Март 21 - Апрель 20", "Апрель 21 - Май 21", "Май 21 - Июнь 21",
            "Июнь 22 - Июль 22", "Июль 23 - Август 23", "Август 24 - Сентябрь 23",
            "Сентябрь 24 - Октябрь 23", "Октябрь 24 - Ноябрь 22", "Ноябрь 23 - Декабрь 21",
            "Декабрь 22 - Январь 20", "Январь 21 - Февраль 19", "Февраль 20 - Март 20"};
    String aries = "Ваше отношение к одной авторитетной персоне сегодня будет крайне негативным, и это может повлечь за собой серьезные проблемы, особенно, если Ваше начальство вспыльчиво. Постарайтесь избежать каких-либо споров или противоречий, иначе конфликт разгорится нешуточный.";
    String taurus = "В Вашем распоряжении имеются все необходимые данные, так что Вы способны наметить планы на будущее, если уделите этому достаточно времени. Это один из тех дней, когда все детали и нюансы волшебным образом сходятся друг с другом.";
    String gemini = "Пока за Вас никто этого не выяснил, Вам следует самим внимательно наблюдать за теми людьми, с которыми Вам приходится работать. Может быть, Вы назовете этот совет излишней подозрительностью или скептицизмом, однако он спасет Вас от многих неприятностей и проблем.";
    String cancer = "Ваша ситуация идеально подходит для того, чтобы позаботиться о своих взаимоотношениях, даже если Ваш друг или романтический партнер придерживаются иного мнения. Вы найдете нужный способ отрегулировать свое личное восприятие, чтобы все прошло гладко.";
    String leo = "Этот день не подарит Вам слишком бурных событий и эмоциональных переживаний, однако Вы добьетесь большого прогресса в отношении достижения своих личных или профессиональных целей. Только не спешите и решайте задания по очереди.";
    String virgo = "Благодаря Вашему сегодняшнему позитивному настрою этот день пройдет для Вас легко и беззаботно. Постарайтесь не втягивать никого ни в какие дела, какими бы заманчивыми они Вам ни казались, тогда Вы действительно насладитесь сегодняшним днем.";
    String libra = "Ваши семейные взаимоотношения могут сегодня стать более напряженными, однако не возникнет таких проблем, которых Вы бы не смогли решить. Вам нужно надавить на пару человек, заставив их честно признаться, что они думают по тому или иному поводу, иначе целый день Вы будете теряться в догадках.";
    String scorpio = "Ваш дар убеждения сегодня будет особенно сильным, и это означает, что Вы сможете развить свои финансовые проекты или улучшить деловые отношения. В этот день сделать это будет так же легко и просто, как рассказать о своем самочувствии.";
    String sagittarius = "Проверьте свой банковский счет - Вам необходимо иметь полное представление о своем финансовом положении. В этот день Вы обладаете нужным настроем, чтобы ликвидировать все имеющиеся оплошности в данной сфере.";
    String capricorn = "Возможно, Вы этого не ожидали, но благодаря своей последней работе Вас заметили нужные люди. Нельзя сказать, чем все закончится, однако изменения однозначно будут позитивными.";
    String aquarius = "Вы настроены весьма спокойно и миролюбиво, поэтому Вам удастся добиться большего, чем окружающим. Вы заметите новые веяния прежде, чем кто-либо другой. Или же просто успеете выполнить куда больше заданий, чем было запланировано.";
    String pisces = "Вам нравится хранить или открывать секреты больше, чем кому бы то ни было, и этот день подарит Вам еще одну тайну, которая полностью изменит Ваше отношение к сложившемуся положению вещей. Это подходящий момент, чтобы немного отступить от привычной ситуации и оценить ее свежим взглядом.";
    String[] infoItems = {aries, taurus, gemini, cancer, leo, virgo, libra, scorpio, sagittarius, capricorn, aquarius, pisces};
    int[] iconItems = {R.drawable.ic_aries, R.drawable.ic_taurus, R.drawable.ic_gemini, R.drawable.ic_cancer, R.drawable.ic_leo, R.drawable.ic_virgo, R.drawable.ic_libra, R.drawable.ic_scorpio, R.drawable.ic_sagittarius, R.drawable.ic_capricornus, R.drawable.ic_aquarius, R.drawable.ic_pisces};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_list_rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                Zodiac zodiac = zodiacList.get(position);
                Intent intent = new Intent(getActivity(), OneSignActivity.class);
                intent.putExtra("SELECTED_ZODIAC_SIGN", zodiac.getSignName());
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {
               Zodiac zodiac = zodiacList.get(position);
                Toast.makeText(getActivity(), zodiac.getSignName() + " is selected successfully", Toast.LENGTH_SHORT).show();
            }
        }));


        zAdapter = new ListAdapter(zodiacList);
        recyclerView.setAdapter(zAdapter);
        prepareZodiacData();
        return view;
    }

    private void prepareZodiacData() {
        Zodiac zodiac;
        for (int i = 0; i <= 11; i++) {
            zodiac = new Zodiac(nameItems[i], dateItems[i], infoItems[i], iconItems[i]);
            zodiacList.add(zodiac);
        }
        zAdapter.notifyDataSetChanged();

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
}
