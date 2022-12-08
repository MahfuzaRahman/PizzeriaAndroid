package com.example.pizzeria;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * This is an Adapter class to be used to instantiate an adapter for the RecyclerView.
 * Must extend RecyclerView.Adapter, which will enforce you to implement 3 methods:
 *      1. onCreateViewHolder, 2. onBindViewHolder, and 3. getItemCount
 *
 * You must use the data type <thisClassName.yourHolderName>, in this example
 * <ItemAdapter.ItemHolder>. This will enforce you to define a constructor for the
 * ItemAdapter and an inner class ItemsHolder (a static class)
 * The ItemsHolder class must extend RecyclerView.ViewHolder. In the constructor of this class,
 * you do something similar to the onCreate() method in an Activity.
 * @author Lily Chang
 */
class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsHolder>{
    private Context context; //need the context to inflate the layout
    private ArrayList<Item> items; //need the data binding to each row of RecyclerView
    private SelectListener listener;

    public ItemsAdapter(Context context, ArrayList<Item> items, SelectListener l) {
        this.context = context;
        this.items = items;
        this.listener = l;
    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);
        return new ItemsHolder(view);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        //assign values for each row
        holder.tv_name.setText(items.get(position).getItemName());
        holder.im_item.setImageResource(items.get(position).getImage());

        holder.cardView.setOnClickListener(new View.OnClickListener(
        ) {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(items.get(position));


            }
        });

    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return items.size(); //number of MenuItem in the array list.
    }

    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public static class ItemsHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price;
        private ImageView im_item;
        //private ConstraintLayout parentLayout; //this is the row layout
        private CardView cardView;

        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_flavor);
            im_item = itemView.findViewById(R.id.im_item);
            //parentLayout = itemView.findViewById(R.id.rowLayout);
            cardView = itemView.findViewById(R.id.mainContainer);
            //setAddButtonOnClick(itemView); //register the onClicklistener for the button on each row.

            /* set onClickListener for the row layout,
             * clicking on a row will navigate to another Activity
             */
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//
//                    Intent intent = new Intent(itemView.getContext(), ItemSelectedActivity.class);
//                    intent.putExtra("ITEM", tv_name.getText());
//                    itemView.getContext().startActivity(intent);
//                }
//            });
        }

        /**
         * Set the onClickListener for the button on each row.
         * Clicking on the button will create an AlertDialog with the options of YES/NO.
         * @param itemView
         */
//        private void setAddButtonOnClick(@NonNull View itemView) {
//            btn_add.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
//                    alert.setTitle("Add to order");
//                    alert.setMessage(tv_name.getText().toString());
//                    //handle the "YES" click
//                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(itemView.getContext(),
//                                    tv_name.getText().toString() + " added.", Toast.LENGTH_LONG).show();
//                        }
//                        //handle the "NO" click
//                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(itemView.getContext(),
//                                    tv_name.getText().toString() + " not added.", Toast.LENGTH_LONG).show();
//                        }
//                    });
//                    AlertDialog dialog = alert.create();
//                    dialog.show();
//                }
//            });
//        }
    }
}
