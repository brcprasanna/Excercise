package com.prasanna.yelpreviewapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prasanna.yelpreviewapp.R;
import com.prasanna.yelpreviewapp.model.Business;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class BusinessListAdapter extends
        RecyclerView.Adapter<BusinessListAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private List<Business> mBussinessList;

    // Pass in the contact array into the constructor
    public BusinessListAdapter(List<Business> businessList) {
        mBussinessList = businessList;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.tv_item);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.list_item_business, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Business contact = mBussinessList.get(position);

        TextView textView = viewHolder.nameTextView;
        textView.setText(contact.getName());

    }

    @Override
    public int getItemCount() {
        return mBussinessList.size();
    }
}

