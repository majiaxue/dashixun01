package com.example.administrator.travel02.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.travel02.R;
import com.example.administrator.travel02.bean.BanmiInfo;
import com.example.administrator.travel02.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecDyncAdapter extends RecyclerView.Adapter {
    private List<BanmiInfo.ResultEntity.ActivitiesEntity> list;
    private Context context;
    private final int MODE_IMG = 0;
    private final int BIG_IMG = 1;

    public RecDyncAdapter(List<BanmiInfo.ResultEntity.ActivitiesEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == MODE_IMG) {
            return new MoreViewHolder(View.inflate(context, R.layout.item_banmi_more, null));
        } else {
            return new BigImgViewHolder(View.inflate(context, R.layout.item_banmi_more_02, null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final BanmiInfo.ResultEntity.ActivitiesEntity entity=list.get(i);
        if (getItemViewType(i)==BIG_IMG) {
            BigImgViewHolder bigImgViewHolder = (BigImgViewHolder) viewHolder;
            bigImgViewHolder.tvDate.setText(entity.getDate());
            bigImgViewHolder.tvTitle.setText(entity.getContent());
            bigImgViewHolder.tvPraiseCount.setText(entity.getLikeCount() + "");
            bigImgViewHolder.tvReplyCount.setText(entity.getReplyCount() + "");
            if (entity.isIsLiked()) {
                GlideUtil.loadResImage(R.drawable.praise_unselected, R.drawable.praise_unselected, R.drawable.praise, bigImgViewHolder.ivPraise, context);
            }
            if (entity.getImages().size() == 1) {
                GlideUtil.loadUrlImage(R.drawable.zhanweitu_touxiang, R.drawable.zhanweitu_touxiang, entity.getImages().get(0), bigImgViewHolder.ivBig, context);
                bigImgViewHolder.ivBig.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onImageClickListener != null) {
                            onImageClickListener.onClick(entity.getImages().get(0));
                        }
                    }
                });
            }
        }else {
                MoreViewHolder moreViewHolder= (MoreViewHolder) viewHolder;
                moreViewHolder.tvDate.setText(entity.getDate());
                moreViewHolder.tvPraiseCount.setText(entity.getLikeCount()+"");
                moreViewHolder.tvReplyCount.setText(entity.getReplyCount()+"");
                moreViewHolder.tvTitle.setText(entity.getContent());
                if (entity.isIsLiked()){
                    GlideUtil.loadResImage(R.drawable.praise_unselected,R.drawable.praise_unselected,R.drawable.praise,moreViewHolder.ivPraise,context);
                }
                moreViewHolder.recView.setLayoutManager(new LinearLayoutManager(context, LinearLayout.HORIZONTAL,false));
                RecImageAdapter adapter = new RecImageAdapter(context, entity.getImages());
                moreViewHolder.recView.setAdapter(adapter);
                adapter.setOnImageClickListener(new RecImageAdapter.OnImageClickListener() {
                    @Override
                    public void onClick(String imgUrl) {
                        if (onImageClickListener!=null){
                            onImageClickListener.onClick(imgUrl);
                        }
                    }
                });
            }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getImages().size()>1){
            return MODE_IMG;
        }else {
            return BIG_IMG;
        }
    }

    class MoreViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_circle)
        ImageView ivCircle;
        @BindView(R.id.iv_data)
        TextView tvDate;
        @BindView(R.id.tv_dynamic)
        TextView tvDynamic;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_intro)
        TextView tvIntro;
        @BindView(R.id.recView)
        RecyclerView recView;
        @BindView(R.id.iv_praise)
        ImageView ivPraise;
        @BindView(R.id.tv_praise_count)
        TextView tvPraiseCount;
        @BindView(R.id.iv_comment)
        ImageView ivComment;
        @BindView(R.id.tv_reply_count)
        TextView tvReplyCount;

        public MoreViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class BigImgViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_circle)
        ImageView ivCircle;
        @BindView(R.id.tv_data)
        TextView tvDate;
        @BindView(R.id.tv_dynamic)
        TextView tvDynamic;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_intro)
        TextView tvIntro;
        @BindView(R.id.iv_big)
        ImageView ivBig;
        @BindView(R.id.iv_praise)
        ImageView ivPraise;
        @BindView(R.id.tv_praise_count)
        TextView tvPraiseCount;
        @BindView(R.id.iv_comment)
        ImageView ivComment;
        @BindView(R.id.tv_reply_count)
        TextView tvReplyCount;
        public BigImgViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    private OnImageClickListener onImageClickListener;

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    public interface OnImageClickListener{
        void onClick(String imgUrl);
    }
}
