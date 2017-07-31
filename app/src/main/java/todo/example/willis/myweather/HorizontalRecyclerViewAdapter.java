package todo.example.willis.myweather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by willis on 7/14/17.
 * the horizontal list to show forecast weather for the following 24hr in main page
 */

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.MyHolder> {


    ArrayList<WeatherListModel> mData = new ArrayList<>();



    public void setmData(ArrayList<WeatherListModel> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_list_item, parent, false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.getmTime().setText(mData.get(position).getmTime());
        holder.getmTemp().setText(mData.get(position).getmTemp());
        holder.getmImage().setImageResource(mData.get(position).getmIcon());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.list_text_time)
        TextView mTime;
        @BindView(R.id.list_text_temp)
        TextView mTemp;

        @BindView(R.id.list_img_weather)
        ImageView mImage;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public TextView getmTime() {
            return mTime;
        }

        public TextView getmTemp() {
            return mTemp;
        }

        public ImageView getmImage() {
            return mImage;
        }
    }
}
