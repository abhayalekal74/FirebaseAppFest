package com.example.abhayalekal.firebaseappfest.dataBinder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abhayalekal.firebaseappfest.Objects.StockObject;
import com.example.abhayalekal.firebaseappfest.R;

import java.util.ArrayList;

/**
 * Created by faheem on 21/06/17.
 */
public class MyStockDataBinder2 extends DataBinder<MyStockDataBinder2.ViewHolder> {
    Context context;
    ArrayList<StockObject> stockList;
    Boolean isWatchList;

    public MyStockDataBinder2(DataBindAdapter dataBindAdapter, Context context, ArrayList<StockObject> stockList, String listType) {
        super(dataBindAdapter);
        this.context = context;
        this.stockList = stockList;
        this.isWatchList = isWatchList;
    }

    @Override
    public MyStockDataBinder2.ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.my_stocks_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(MyStockDataBinder2.ViewHolder holder, int position) {
        View view = View.inflate(context, R.layout.my_stock_item, null);
        TextView stockName = (TextView) view.findViewById(R.id.stockName);
        TextView currentValue = (TextView) view.findViewById(R.id.currentValue);
        TextView openingValue = (TextView) view.findViewById(R.id.openingValue);
        TextView profitStatus = (TextView) view.findViewById(R.id.profitValue);

        if(position==0)
        {
            stockName.setText("Stock Name");
            currentValue.setText("Current Value");
            openingValue.setText("Opening Value");
            profitStatus.setText("Status");

        }
        else
        {
            stockName.setText(stockList.get(position).name);
            currentValue.setText(stockList.get(position).currentValue+"");
            openingValue.setText(stockList.get(position).openValue+"");
            float profit = stockList.get(position).currentValue - stockList.get(position).openValue;
            profitStatus.setText(profit+"");
        }
        holder.myStocksLayout.addView(view);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout myStocksLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            myStocksLayout = (LinearLayout) itemView.findViewById(R.id.myStocksLinearLayout);
        }
    }
}
