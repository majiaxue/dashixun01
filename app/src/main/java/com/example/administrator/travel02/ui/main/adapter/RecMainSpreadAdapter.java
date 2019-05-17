package com.example.administrator.travel02.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.MainSpreadBean;
import com.example.administrator.travel02.ui.main.activity.BanMiDetailsActivity;
import com.example.administrator.travel02.ui.main.activity.CommentActivity;
import com.example.administrator.travel02.utils.GlideUtil;
import com.example.administrator.travel02.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecMainSpreadAdapter extends RecyclerView.Adapter {
    private final int IMAGE_TYPE = 0;
    private final int AUTHOR_TYPE = 1;
    private final int REVIEW_TYPE = 2;
    private final int LIKE_TYPE = 3;

    private Context context;
    private MainSpreadBean.ResultEntity resultEntity;

    public RecMainSpreadAdapter(Context context, MainSpreadBean.ResultEntity resultEntity) {
        this.context = context;
        this.resultEntity = resultEntity;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == IMAGE_TYPE) {
            return new ImgViewHolder(View.inflate(context, R.layout.item_spread_img, null));
        } else if (viewType == AUTHOR_TYPE) {
            return new AuthorViewHolder(View.inflate(context, R.layout.item_author, null));
        } else if (viewType == REVIEW_TYPE) {
            return new ReviewViewHolder(View.inflate(context, R.layout.item_review, null));
        } else {
            return new LikeViewHolder(View.inflate(context, R.layout.item_like, null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        int index = position;
        if (viewType == IMAGE_TYPE) {
            ImgViewHolder imageHolder = (ImgViewHolder) holder;
            GlideUtil.loadUrlImage(R.mipmap.mm, R.mipmap.mm,
                    resultEntity.getCarousel().get(0), imageHolder.img, context);
        } else if (viewType == AUTHOR_TYPE) {
            final MainSpreadBean.ResultEntity.BanmiEntity banmi = resultEntity.getBanmi();
            AuthorViewHolder authorHolder = (AuthorViewHolder) holder;
            GlideUtil.loadUrlCircleImage(R.mipmap.mm, R.mipmap.mm,
                    banmi.getPhoto(), authorHolder.ivHeader, context);
            authorHolder.tvName.setText(banmi.getName());
            authorHolder.tvOccup.setText(banmi.getOccupation());
            authorHolder.tvCity.setText(banmi.getLocation());
            authorHolder.tvContent.setText(banmi.getIntroduction());
            authorHolder.ivHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent intent=new Intent(context,BanMiDetailsActivity.class);
                   intent.putExtra(Constants.DATA,banmi.getId());
                   context.startActivity(intent);
                }
            });
        } else if (viewType == REVIEW_TYPE) {
            List<MainSpreadBean.ResultEntity.ReviewsEntity> list = resultEntity.getReviews();
            ReviewViewHolder reviewsHolder = (ReviewViewHolder) holder;
            reviewsHolder.tvName.setText(list.get(position - 2).getUserName());
            reviewsHolder.tvTime.setText(list.get(position - 2).getCreatedAt());
            reviewsHolder.tvContent.setText(list.get(position - 2).getContent());
            GlideUtil.loadUrlCircleImage(R.mipmap.mm, R.mipmap.mm,
                    list.get(position - 2).getUserPhoto(), reviewsHolder.ivHeader, context);
            if (list.get(position - 2).getImages() != null && list.get(position - 2).getImages().size() != 0) {
                reviewsHolder.recImgs.setLayoutManager(new GridLayoutManager(context, list.get(position - 2).getImages().size(), LinearLayout.HORIZONTAL, true));
                RecImageAdapter adapter = new RecImageAdapter(context, list.get(position - 2).getImages());
                reviewsHolder.recImgs.setAdapter(adapter);
                adapter.setOnImageClickListener(new RecImageAdapter.OnImageClickListener() {
                    @Override
                    public void onClick(String imgUrl) {
                        if (onImageClickListener != null){
                            onImageClickListener.onClick(imgUrl);
                        }
                    }
                });
            }
        } else {
            final LikeViewHolder likeHolder = (LikeViewHolder) holder;
            if (resultEntity.getRoute().isIsCollected()) {
                GlideUtil.loadResImage(R.drawable.collect_default, R.drawable.collect_default,
                        R.drawable.collect_highlight, likeHolder.ivLike, context);
                likeHolder.tvLike.setText("已收藏");
            } else {
                GlideUtil.loadResImage(R.drawable.collect_default, R.drawable.collect_default,
                        R.drawable.collect_default, likeHolder.ivLike, context);
                likeHolder.tvLike.setText("收藏");
            }
            likeHolder.showAllContent.setText("查看全部" + resultEntity.getReviewsCount() + "条评价");
            likeHolder.showAllContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if (onCommentListener!=null){
                       onCommentListener.onCommentClick(resultEntity.getRoute().getId());
                   }

                }
            });
            likeHolder.rlLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (resultEntity.getRoute().isIsCollected()) {
                        if (onItemLikeListener != null) {
                            onItemLikeListener.disLike(resultEntity.getRoute().getId());
                            GlideUtil.loadResImage(R.drawable.back_white, R.drawable.back_black,
                                    R.drawable.collect_default, likeHolder.ivLike, context);
                            ToastUtil.showShort("已取消收藏");
                            likeHolder.tvLike.setText("收藏");
                            resultEntity.getRoute().setIsCollected(false);
                        }
                    } else {
                        if (onItemLikeListener != null) {
                            onItemLikeListener.addLike(resultEntity.getRoute().getId());
                            GlideUtil.loadResImage(R.drawable.back_white, R.drawable.back_black,
                                    R.drawable.collect_highlight, likeHolder.ivLike, context);
                            ToastUtil.showShort("已收藏");
                            likeHolder.tvLike.setText("已收藏");
                            resultEntity.getRoute().setIsCollected(true);
                        }
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 3 + resultEntity.getReviews().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return IMAGE_TYPE;
        } else if (position == 1) {
            return AUTHOR_TYPE;
        } else if (position == getItemCount() - 1) {
            return LIKE_TYPE;
        } else {
            return REVIEW_TYPE;
        }
    }
    class ImgViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;

        public ImgViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class AuthorViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_header)
        ImageView ivHeader;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_occup)
        TextView tvOccup;
        @BindView(R.id.tv_city)
        TextView tvCity;
        @BindView(R.id.tv_content)
        TextView tvContent;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_header)
        ImageView ivHeader;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.rec_imgs)
        RecyclerView recImgs;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class LikeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.show_all_content)
        TextView showAllContent;
        @BindView(R.id.iv_like)
        ImageView ivLike;
        @BindView(R.id.tv_like)
        TextView tvLike;
        @BindView(R.id.rl_like)
        RelativeLayout rlLike;
        public LikeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    private OnItemLikeListener onItemLikeListener;

    public void setOnItemLikeListener(OnItemLikeListener onItemLikeListener) {
        this.onItemLikeListener = onItemLikeListener;
    }

    public interface OnItemLikeListener {
        void addLike(int id);

        void disLike(int id);
    }
    private OnImageClickListener onImageClickListener;

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    public interface OnImageClickListener{
        void onClick(String imgUrl);
    }

    private onCommentListener onCommentListener;

    public void setOnCommentListener(RecMainSpreadAdapter.onCommentListener onCommentListener) {
        this.onCommentListener = onCommentListener;
    }

    public interface onCommentListener{
        void onCommentClick(int id);
    }


}
