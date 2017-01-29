package app.sungi.horoscope.fragment_sign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class TabFragmentMonth extends Fragment {

    private List<Zodiac> zodiacList = new ArrayList<>();
    ListAdapter zAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        String signName = bundle.getString("SIGN");
        Log.d("myLogssssssssssssss", signName);
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
        String infoItems = "Какова самая недостижимая цель, какую Вы можете себе представить? Дело в том, что 1-го и 2-го числа для Вас не будет ничего невозможного. Если Вы поставите перед собой по-настоящему высокую планку и приложите усилия, чтобы ее достичь, то у Вас будут все шансы это сделать.\n" +
                "\n" +
                "7-го и 8-го числа все Ваши мысли будут посвящены финансовым вопросам. Вам удастся заметно повысить свой доход и сделать ряд довольно грамотных вложений. А если в Вашем распоряжении не будет достаточного количества свободных средств, то постарайтесь взять их в долг. В любом случае разработайте подробный бизнес-план, чтобы в нужный момент Вы могли бы сразу же приступить к его реализации.\n" +
                "\n" +
                "11-го, 12-го и 13-го числа Вам определенно стоит уделить какое-то время общению с близкими Вам представительницами прекрасного пола. Пообщайтесь со своей мамой, тетей, племянницей или подругой - они смогут оказать Вам очень серьезную поддержку и дать ряд весьма полезных советов. Их точка зрения прольет свет на массу важных вопросов.\n" +
                "\n" +
                "18-го и 19-го числа Вы будете самым решительным и упорным образом добиваться осуществления своих желаний, и тем острее будет горечь Вашего разочарования, если Вам все-таки не удастся их достичь. К счастью, Вы обладаете очень многими талантами, которые помогут Вам справиться с этой задачей.\n" +
                "\n" +
                "28-го и 29-го числа присущая Вам изобретательность поможет Вам добиться большого признания со стороны окружающих Вас людей. Похоже, история Вашего успеха будет иметь очень счастливый конец.";
        Zodiac zodiac = new Zodiac("Овен", "Март 21 - Апрель 20", infoItems, R.drawable.ic_aries);
        zodiacList.add(zodiac);
        zAdapter.notifyDataSetChanged();

    }
}
