package com.example.administrator.travel02.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.SubJectBean;
import com.example.administrator.travel02.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecSubjectAdapter extends RecyclerView.Adapter<RecSubjectAdapter.ViewHolder> {

    private List<SubJectBean.ResultBean.BundlesBean> list;
    private Context context;

    public RecSubjectAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<SubJectBean.ResultBean.BundlesBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       return new ViewHolder(View.inflate(context,R.layout.item_sub,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final SubJectBean.ResultBean.BundlesBean entity = list.get(i);
        GlideUtil.loadUrlImage(R.drawable.zhanweitu_touxiang,R.drawable.zhanweitu_touxiang,
                entity.getCardURL(),viewHolder.img,context);
        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick!=null){
                    onItemClick.onClick(list.get(i).getContentURL(), Constants.TITLE);
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private onItemClick onItemClick;

    public void setOnItemClick(RecSubjectAdapter.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface onItemClick{
        void onClick(String url,String title);
    }
}
