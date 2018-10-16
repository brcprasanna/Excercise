package com.prasanna.yelpreviewapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.prasanna.yelpreviewapp.R;
import com.prasanna.yelpreviewapp.model.Business;

import java.util.List;

public class MainListViewAdapter extends ArrayAdapter<Business> {

    private List<Business> businessList;
    private Context context;

    public MainListViewAdapter(Context context, int resource, List<Business> businessList) {
        super(context, resource, businessList);
        this.businessList = businessList;
        this.context = context;
    }

    public int getCount() {
        return businessList.size();
    }

    public Business getItem(int position) {
        return businessList.get(position);
    }

    public long getItemId(int position) {
        return businessList.get(position).hashCode();
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

        final Business business = getItem(position);
        if (business != null) {
            TextView name = view.findViewById(R.id.business);
            if (name != null) {
                name.setText(business.getName());
            }
        }

        return view;
    }

    public void setData(List<Business> businessList) {
        this.businessList = businessList;
        notifyDataSetChanged();
    }

    /*
	 * We create our filter
	 */

}
