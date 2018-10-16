package com.prasanna.yelpreviewapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.prasanna.yelpreviewapp.R;
import com.prasanna.yelpreviewapp.model.category.Category;

import java.util.List;

public class CategoryListViewAdapter extends ArrayAdapter<Category> {

    private List<Category> categoryList;

    public CategoryListViewAdapter(Context context, int resource, List<Category> categoryList) {
        super(context, resource, categoryList);
        this.categoryList = categoryList;
    }

    public int getCount() {
        if (categoryList != null) {
            return categoryList.size();
        } else {
            return 0;
        }
    }

    public Category getItem(int position) {
        return categoryList.get(position);
    }

    public long getItemId(int position) {
        return categoryList.get(position).hashCode();
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.list_item_main, null);
        }

        final Category category = getItem(position);
        if (category != null) {
            TextView name = view.findViewById(R.id.business);
            if (name != null) {
                name.setText(category.getTitle());
            }
        }

        return view;
    }

    public void setData(List<Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }
}
