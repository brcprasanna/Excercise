package com.prasanna.yelpreviewapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prasanna.yelpreviewapp.R;

import java.util.List;

public class BusinessListAdapter extends RecyclerView.Adapter<BusinessListAdapter.ViewHolder> {

    private final List<String> items;

    public BusinessListAdapter(final List<String> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final Context context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(R.layout.list_item_business, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final String itemText = items.get(position);
        holder.tvItem.setText(itemText);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<String> getItems() {
        return items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvItem;

        public ViewHolder(final View itemView) {
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}