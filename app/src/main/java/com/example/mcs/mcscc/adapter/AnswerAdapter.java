package com.example.mcs.mcscc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mcs.mcscc.data.model.remote.model1.SOAnswersResponse;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {

    private List<SOAnswersResponse> mItems;
    private Context mContext;
    private PostItemListener mItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTv;

        PostItemListener mItemListener;

        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            titleTv = itemView.findViewById(android.R.id.text1);

            /*this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);*/
        }

      /*  @Override
        public void onClick(View view) {
            SOAnswersResponse item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.getTitle());

            notifyDataSetChanged();
        }*/
    }

    public AnswerAdapter(Context context, List<SOAnswersResponse> posts, PostItemListener itemListener) {
        mItems = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public AnswerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(AnswerAdapter.ViewHolder holder, int position){

        SOAnswersResponse item = mItems.get(position);
        TextView textView = holder.titleTv;
        textView.setText(item.getAuthor());
    }

    @Override
    public int getItemCount(){
        return mItems.size();
    }

    public void updateAnswers(List<SOAnswersResponse> items){
        mItems = items;
        notifyDataSetChanged();
    }

    private SOAnswersResponse getItem(int adapterPosition){
        return mItems.get(adapterPosition);
    }

    public interface PostItemListener{
        void onPostClick(long id);
    }
}