package com.example.abhayalekal.firebaseappfest.Adapters;

import android.content.Context;

import com.example.abhayalekal.firebaseappfest.Binders.NothingToShowDataBinder;
import com.example.abhayalekal.firebaseappfest.dataBinder.DataBindAdapter;
import com.example.abhayalekal.firebaseappfest.dataBinder.DataBinder;

/**
 * Created by faheem on 21/06/17.
 */
public class HomeAdapter extends DataBindAdapter{
    NothingToShowDataBinder nothingToShowDataBinder;
    public HomeAdapter(Context context)
    {
        nothingToShowDataBinder = new NothingToShowDataBinder(this);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public <T extends DataBinder> T getDataBinder(int viewType) {
        return null;
    }
}
