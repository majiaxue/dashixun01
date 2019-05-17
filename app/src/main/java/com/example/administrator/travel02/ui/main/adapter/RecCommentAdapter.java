package com.example.administrator.travel02.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.travel02.R;
import com.example.administrator.travel02.bean.CommentBean;
import com.example.administrator.travel02.bean.MainSpreadBean;
import com.example.administrator.travel02.bean.SubJectBean;
import com.example.administrator.travel02.utils.GlideUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecCommentAdapter extends RecyclerView.Adapter {
    private ArrayList<MainSpreadBean.ResultEntity.ReviewsEntity> list;
    private Context context;

    public RecCommentAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(ArrayList<MainSpreadBean.ResultEntity.ReviewsEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CommentViewHolder(View.inflate(context, R.layout.item_comment, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CommentViewHolder commentViewHolder = (CommentViewHolder) viewHolder;
        if (i == getItemCount() - 1) {
            commentViewHolder.divider.setVisibility(View.GONE);
        } else {
            commentViewHolder.divider.setVisibility(View.VISIBLE);
        }
        commentViewHolder.tvContent.setText(list.get(i).getContent());
        commentViewHolder.tvData.setText(list.get(i).getCreatedAt());
        commentViewHolder.tvName.setText(list.get(i).getUserName());
        GlideUtil.loadUrlCircleImage(R.mipmap.mm, R.mipmap.mm,
                list.get(i).getUserPhoto(), commentViewHolder.imgHeader, context);
        if (list.get(i).getImages() != null && list.get(i).getImages().size() > 0) {
            commentViewHolder.recImgs.setLayoutManager(new GridLayoutManager(context, 3));
            RecImageAdapter adapter = new RecImageAdapter(context, list.get(i).getImages());
            commentViewHolder.recImgs.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_header)
        ImageView imgHeader;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_data)
        TextView tvData;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.rec_imgs)
        RecyclerView recImgs;
        @BindView(R.id.divider)
        View divider;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
