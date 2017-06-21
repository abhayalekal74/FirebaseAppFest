package com.example.abhayalekal.firebaseappfest.Binders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhayalekal.firebaseappfest.R;
import com.example.abhayalekal.firebaseappfest.dataBinder.DataBindAdapter;
import com.example.abhayalekal.firebaseappfest.dataBinder.DataBinder;


/**
 * Created by gunjit on 17/05/17.
 */

public class NothingToShowDataBinder extends DataBinder<NothingToShowDataBinder.ViewHolder> {
    public NothingToShowDataBinder(DataBindAdapter dataBindAdapter) {
        super(dataBindAdapter);
    }


    @Override
    public NothingToShowDataBinder.ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.nothing_to_show, parent, false);
        return new NothingToShowDataBinder.ViewHolder(view);
    }

    @Override
    public void bindViewHolder(NothingToShowDataBinder.ViewHolder holder, int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
