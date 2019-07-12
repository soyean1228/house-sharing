package com.example.user.hanzip.mypage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.hanzip.R;
import com.example.user.hanzip.vo.MainListVO;

import java.util.ArrayList;

public class MypageListAdapter extends RecyclerView.Adapter<MypageListAdapter.MypageListHolder> {

    private Context context;
    private ArrayList<MainListVO> mypageList = new ArrayList<MainListVO>();

    public MypageListAdapter(Context context, View.OnClickListener itemActivityListener) {
        this.context = context;

    }
    public void setImgData(ArrayList<MainListVO> mypageList){
        this.mypageList = mypageList;
    }

    @Override
    public MypageListAdapter.MypageListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_mainlist, parent, false);
        return new MypageListAdapter.MypageListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MypageListAdapter.MypageListHolder holder, int position) {

        if(mypageList.size() == 0){
            return;
        }
        if(position>mypageList.size()){}
        MainListVO item = mypageList.get(position);
        ((MypageListAdapter.MypageListHolder)holder).address.setText(item.getAddress().toString());
        ((MypageListAdapter.MypageListHolder)holder).price.setText(String.valueOf(item.getPrice())+"만원/월");
        Glide.with(context).load(item.getImagePath()).into(holder.home_img);
    }

    @Override
    public int getItemCount() {
        return mypageList.size();
    }

    public class MypageListHolder extends RecyclerView.ViewHolder {
        ImageView home_img;
        TextView address,price;

        public MypageListHolder(View itemView) {
            super(itemView);
            home_img = (ImageView)itemView.findViewById(R.id.home_img);
            address = (TextView)itemView.findViewById(R.id.address);
            price = (TextView)itemView.findViewById(R.id.price);

        }
    }


}

