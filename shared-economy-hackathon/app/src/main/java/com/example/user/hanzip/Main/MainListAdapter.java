package com.example.user.hanzip.Main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.hanzip.R;
import com.example.user.hanzip.vo.MainListVO;

import java.util.ArrayList;
import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainListHolder> {

    private Context context;
    private List<MainListVO> mainList = new ArrayList<MainListVO>();
    private View.OnClickListener onClickListener;
    private View.OnClickListener onHeartClickListener;


    public MainListAdapter(Context context, View.OnClickListener listener, View.OnClickListener heartListener) {
        this.context = context;
        this.onClickListener = listener;
        onHeartClickListener = heartListener;
    }
    public void setImgData(ArrayList<MainListVO> mainList){
        this.mainList = mainList;
    }

    @Override
    public MainListAdapter.MainListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_mainlist, parent, false);
        return new MainListAdapter.MainListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainListAdapter.MainListHolder holder, int position) {

        if(mainList.size() == 0){
            return;
        }
        if(position>mainList.size()){}
        MainListVO item = mainList.get(position);
        ((MainListHolder)holder).address.setText(item.getAddress().toString());
        ((MainListHolder)holder).name.setText(item.getName().toString());
        ((MainListHolder)holder).price.setText(String.valueOf(item.getPrice())+"만원/월");
        Glide.with(context).load(item.getImagePath()).into(holder.home_img);

        ((MainListHolder)holder).parent.setTag(item);
        ((MainListHolder)holder).parent.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }
    public void setOnItemClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    public class MainListHolder extends RecyclerView.ViewHolder {
        ImageView home_img;
        TextView name,address,price;
        private LinearLayout parent;

        public MainListHolder(View itemView) {
            super(itemView);
            parent = (LinearLayout) itemView.findViewById(R.id.view_parent);
            home_img = (ImageView)itemView.findViewById(R.id.home_img);
            address = (TextView)itemView.findViewById(R.id.address);
            price = (TextView)itemView.findViewById(R.id.price);
            name = (TextView)itemView.findViewById(R.id.house_name);

        }
        public void setOnItemClick(View.OnClickListener listener){
            parent.setOnClickListener(listener);
        }
    }


}

