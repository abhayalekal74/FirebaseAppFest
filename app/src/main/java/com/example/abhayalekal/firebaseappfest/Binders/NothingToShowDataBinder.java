package com.example.abhayalekal.firebaseappfest.Binders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abhayalekal.firebaseappfest.R;
import com.example.abhayalekal.firebaseappfest.dataBinder.DataBindAdapter;
import com.example.abhayalekal.firebaseappfest.dataBinder.DataBinder;

/**
 * Created by gunjit on 17/05/17.
 */

public class NothingToShowDataBinder extends DataBinder<NothingToShowDataBinder.ViewHolder> {
    String listType;
    public NothingToShowDataBinder(DataBindAdapter dataBindAdapter, String ListType) {
        super(dataBindAdapter);
        this.listType = listType;
    }


    @Override
    public NothingToShowDataBinder.ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.nothing_to_show, parent, false);
        return new NothingToShowDataBinder.ViewHolder(view);
    }

    @Override
    public void bindViewHolder(NothingToShowDataBinder.ViewHolder holder, int position) {
        if(listType.matches("Trending List"))
        {
            holder.pleaseSubscribe.setText("");
            holder.clickToSubscribe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        else if(listType.matches("My Stock"))
        {
            holder.pleaseSubscribe.setText("You have not invested in any stocks yet!");
            holder.clickToSubscribe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        else if(listType.matches("Watch List"))
        {
            holder.pleaseSubscribe.setText("You have not added any stocks to your watch list yet!");
            holder.clickToSubscribe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView pleaseSubscribe;
        TextView clickToSubscribe;
        public ViewHolder(View itemView) {
            super(itemView);
            pleaseSubscribe = (TextView)itemView.findViewById(R.id.clickToSubscribe);

        }
    }
}
