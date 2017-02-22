package com.avidos.avidospay;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Alan on 21/02/2017.
 */

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class CardsAdapter extends
        RecyclerView.Adapter<CardsAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private List<Card> mCards;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public CardsAdapter(Context context, List<Card> contacts) {
        mCards = contacts;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public SwitchCompat paymentSwitch;
        public TextView paymentName;
        public ImageView paymentImage;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            paymentName = (TextView) itemView.findViewById(R.id.text_payment_name);
            paymentImage = (ImageView) itemView.findViewById(R.id.image_payment_type);
            paymentSwitch = (SwitchCompat) itemView.findViewById(R.id.switch_payment);
        }
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public CardsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(CardsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Card card = mCards.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.paymentName;
        textView.setText(card.getPaymentType());
        SwitchCompat switchCompat = viewHolder.paymentSwitch;
        switchCompat.setChecked(card.isActivated());
        ImageView imageView = viewHolder.paymentImage;
        if(!card.getPaymentType().contains("Efectivo")) imageView.setImageResource(R.drawable.ic_credit_card_black_24dp);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mCards.size();
    }
}
