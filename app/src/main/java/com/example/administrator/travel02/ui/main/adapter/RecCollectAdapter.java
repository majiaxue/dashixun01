package com.example.administrator.travel02.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.travel02.R;
import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecCollectAdapter extends RecyclerView.Adapter<RecCollectAdapter.ViewHolder> {

    private Context context;
    private List<LikeBean.ResultBean.CollectedRoutesBean> list;

    public RecCollectAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(List<LikeBean.ResultBean.CollectedRoutesBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(View.inflate(context, R.layout.item_follow, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        GlideUtil.loadUrlRoundImg(6, R.drawable.zhanweitu_touxiang, R.drawable.zhanweitu_touxiang,
                list.get(i).getCardURL(), viewHolder.img, context);
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.intro.setText(list.get(i).getIntro());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onClick(v,i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.intro)
        TextView intro;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick(View view, int position);
    }
}
