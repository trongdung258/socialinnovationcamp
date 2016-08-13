package com.example.yuu.socialinnovationcamp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuu.socialinnovationcamp.Model.Diary;
import com.example.yuu.socialinnovationcamp.R;

import java.util.Vector;

/**
 * Created by Yuu on 8/13/2016.
 */
public class ListDiaryAdapter extends RecyclerView.Adapter<ListDiaryAdapter.DiaryHolder> {

    private Vector<Diary> lisDiary;
    private Context mContext;
    OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
    public ListDiaryAdapter(Vector<Diary> diaries,Context mContext)
    {
        this.mContext  = mContext;
        this.lisDiary = diaries;
    }
    @Override
    public DiaryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_in_list_diary,parent,false);
        DiaryHolder holder = new DiaryHolder(view);
        holder.onItemClickListener = onItemClickListener;
        return holder;
    }

    @Override
    public void onBindViewHolder(DiaryHolder holder, int position) {
        holder.txtTime.setText(lisDiary.get(position).getDateTime());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return lisDiary.size();
    }

     static class DiaryHolder extends RecyclerView.ViewHolder {
         private TextView txtTime;
         private OnItemClickListener onItemClickListener;
         public int position;
        public DiaryHolder(final View itemView) {
            super(itemView);
            txtTime = (TextView)itemView.findViewById(R.id.txt_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null)
                    {
                        onItemClickListener.onItemClickListener(itemView,position);
                    }
                }
            });
        }
    }
    public interface OnItemClickListener
    {
        void onItemClickListener(View view ,int position);
    }
}
