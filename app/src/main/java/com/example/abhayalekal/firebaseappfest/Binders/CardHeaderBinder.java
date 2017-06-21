package com.example.abhayalekal.firebaseappfest.Binders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhayalekal.firebaseappfest.Activities.StockListActivity;
import com.example.abhayalekal.firebaseappfest.R;
import com.example.abhayalekal.firebaseappfest.dataBinder.DataBindAdapter;
import com.example.abhayalekal.firebaseappfest.dataBinder.DataBinder;

/**
 * Created by faheem on 21/06/17.
 */
public class CardHeaderBinder extends DataBinder<CardHeaderBinder.ViewHolder> {
    String listType = "";
    Context context;

    public CardHeaderBinder(DataBindAdapter dataBindAdapter, String listType, Context context) {
        super(dataBindAdapter);
        this.listType = listType;
        this.context = context;
    }

    @Override
    public CardHeaderBinder.ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_header, parent, false);
        return new CardHeaderBinder.ViewHolder(view);
    }

    @Override
    public void bindViewHolder(CardHeaderBinder.ViewHolder holder, int position) {
        holder.headerTitle.setText(listType);
        if (listType.matches("Trending")) {
            holder.plusIcon.setVisibility(View.GONE);
        }
        holder.plusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, StockListActivity.class);
                intent.putExtra("listType", listType);
                context.startActivity(intent);
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView headerTitle;
        ImageView plusIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            headerTitle = (TextView) itemView.findViewById(R.id.headerTitle);
            plusIcon = (ImageView) itemView.findViewById(R.id.plusIcon);

        }
    }
}
