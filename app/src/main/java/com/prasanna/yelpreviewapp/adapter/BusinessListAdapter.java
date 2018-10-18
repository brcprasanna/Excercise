package com.prasanna.yelpreviewapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prasanna.yelpreviewapp.R;
import com.prasanna.yelpreviewapp.model.Business;

import java.util.List;

public class BusinessListAdapter extends
        RecyclerView.Adapter<BusinessListAdapter.ViewHolder> {

    private List<Business> mBusinessList;

    public BusinessListAdapter(List<Business> businessList) {
        mBusinessList = businessList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.list_item_business, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Business contact = mBusinessList.get(position);

        TextView textView = viewHolder.nameTextView;
        textView.setText(contact.getName());
    }

    @Override
    public int getItemCount() {
        return mBusinessList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_item);

        }
    }
}

