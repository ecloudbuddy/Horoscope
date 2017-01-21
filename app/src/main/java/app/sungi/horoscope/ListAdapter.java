package app.sungi.horoscope;

/**
 * Created by Sungi on 16.01.2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<Zodiac> zodiacList;

    ListAdapter(List<Zodiac> zodiacList) {
        this.zodiacList = zodiacList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,
                viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        Zodiac zodiac = zodiacList.get(position);
        myViewHolder.signName.setText(zodiac.getSignName());
        myViewHolder.signDate.setText(zodiac.getSignDate());
        myViewHolder.signInfo.setText(zodiac.getSignInfo());
        myViewHolder.signImage.setImageResource(Integer.parseInt(String.valueOf(zodiac.getSignIcon())));
    }

    @Override
    public int getItemCount() {
        return zodiacList == null ? 0 : zodiacList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView signName, signDate, signInfo;
        ImageView signImage;

        MyViewHolder(View itemView) {
            super(itemView);

            signName = (TextView) itemView.findViewById(R.id.sign_name);
            signDate = (TextView) itemView.findViewById(R.id.sign_date);
            signInfo = (TextView) itemView.findViewById(R.id.sign_info);
            signImage = (ImageView) itemView.findViewById(R.id.sign_photo);
        }
    }

}
