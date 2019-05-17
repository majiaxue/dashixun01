package com.example.administrator.travel02.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.travel02.R;
import com.example.administrator.travel02.bean.TravelBean;
import com.example.administrator.travel02.utils.GlideUtil;
import com.example.administrator.travel02.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecBanMiAdapter extends RecyclerView.Adapter<RecBanMiAdapter.ViewHolder> {
    private ArrayList<TravelBean.ResultBean.BanmiBean> banmiBeans;
    private Context context;

    public RecBanMiAdapter(ArrayList<TravelBean.ResultBean.BanmiBean> banmiBeans, Context context) {
        this.banmiBeans = banmiBeans;
        this.context = context;
    }

    public void setBanmiBeans(ArrayList<TravelBean.ResultBean.BanmiBean> banmiBeans) {
        this.banmiBeans = banmiBeans;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_stay, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final TravelBean.ResultBean.BanmiBean bean = banmiBeans.get(i);
        if (bean.isIsFollowed()) {
            GlideUtil.loadResImage(R.mipmap.mm, R.mipmap.mm,R.drawable.follow, viewHolder.mImg, context);
        } else {
            GlideUtil.loadResImage(R.mipmap.mm, R.mipmap.mm, R.drawable.follow_unselected, viewHolder.mImg, context);
        }
        viewHolder.mTv_name_stay.setText(bean.getName());
        viewHolder.tv_person.setText(bean.getFollowing() + "人关注");
        viewHolder.mTv_region.setText(bean.getLocation());
        viewHolder.mTv_status.setText(bean.getOccupation());
        RequestOptions options = RequestOptions.placeholderOf(R.mipmap.mm);
        Glide.with(context).load(bean.getPhoto()).apply(options).into(viewHolder.img_stay);
        viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.isIsFollowed()) {
                    if (fellowListener != null) {
                        ToastUtil.showShort("已取消关注");
                        fellowListener.cancelFellow(bean.getId(),i);
                        Glide.with(context).load(R.drawable.follow_unselected).into(viewHolder.mImg);
                        banmiBeans.get(i).setIsFollowed(false);
                    }
                } else {
                    if (fellowListener != null) {
                        ToastUtil.showShort("已关注");
                        fellowListener.onFellow(bean.getId(),i);
                        Glide.with(context).load(R.drawable.follow).into(viewHolder.mImg);
                        bean.setIsFollowed(true);
                    }
                }
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onClick(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return banmiBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_stay;
        TextView mTv_name_stay;
        TextView tv_person;
        TextView mTv_region;
        TextView mTv_status;
        ImageView mImg;
        CardView card;
        RelativeLayout rl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_stay = itemView.findViewById(R.id.img_stay);
            mTv_name_stay = itemView.findViewById(R.id.mTv_name_stay);
            tv_person = itemView.findViewById(R.id.tv_person);
            mTv_region = itemView.findViewById(R.id.mTv_region);
            mTv_status = itemView.findViewById(R.id.mTv_status);
            mImg = itemView.findViewById(R.id.mImg);
            card = itemView.findViewById(R.id.card);
            rl = itemView.findViewById(R.id.rl);
        }
    }

    private FellowListener fellowListener;

    public void setFellowListener(FellowListener fellowListener) {
        this.fellowListener = fellowListener;
    }

    public interface FellowListener {
        void onFellow(int id,int position);
        void cancelFellow(int id,int position);
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick(TravelBean.ResultBean.BanmiBean bean);
    }

}
