package com.example.virusblokadatask;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class AppsListAdapter extends RecyclerView.Adapter<AppsListAdapter.ItemViewHolder> {

    private final ArrayList<Pair<String,String>> namesHashList;
    private final LayoutInflater inflater;

    AppsListAdapter(ArrayList<Pair<String,String>> namesHashList, Context context){
        this.namesHashList = namesHashList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.app_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(namesHashList.get(position));
    }

    @Override
    public int getItemCount() {
        return namesHashList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        private final TextView nameTextView;
        private final TextView hashTextView;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            hashTextView = itemView.findViewById(R.id.hashTextView);
        }

        public void bind(Pair<String,String> pair){
            nameTextView.setText(pair.first);
            hashTextView.setText(pair.second);
        }
    }


}
