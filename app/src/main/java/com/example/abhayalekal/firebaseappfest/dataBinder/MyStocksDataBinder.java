package com.example.abhayalekal.firebaseappfest.dataBinder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhayalekal.firebaseappfest.Objects.StockObject;
import com.example.abhayalekal.firebaseappfest.R;
import com.example.abhayalekal.firebaseappfest.firebase.FirebasePresenter;

import java.util.ArrayList;

/**
 * Created by faheem on 21/06/17.
 */
public class MyStocksDataBinder extends DataBinder<MyStocksDataBinder.ViewHolder> {
    Context context;
    ArrayList<StockObject> stockList;
    Boolean isWatchList;
    FirebasePresenter firebasePresenter;

    public MyStocksDataBinder(DataBindAdapter dataBindAdapter, Context context, ArrayList<StockObject> stockList, FirebasePresenter firebasePresenter) {
        super(dataBindAdapter);
        this.context = context;
        this.stockList = stockList;
        this.firebasePresenter = firebasePresenter;
    }

    @Override
    public MyStocksDataBinder.ViewHolder newViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.my_stock_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(MyStocksDataBinder.ViewHolder holder, final int position) {

        holder.stockName.setText(stockList.get(position).name);
        holder.currentValue.setText(stockList.get(position).currentValue+"");
        holder.openingValue.setText(stockList.get(position).openValue+"");
        float profit = Float.parseFloat(stockList.get(position).currentValue) - Float.parseFloat(stockList.get(position).openValue);
        if(profit<0)
        {
            holder.stockStatusImage.setImageDrawable(context.getResources().getDrawable(R.drawable.expand_plus));
        }
        else
        {
            holder.stockStatusImage.setImageDrawable(context.getResources().getDrawable(R.drawable.expand_plus));

        }
        holder.profitStatus.setText(profit+"");

        holder.investBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebasePresenter.buyStock(stockList.get(position), new FirebasePresenter.StockListener() {
                    @Override
                    public void success(StockObject stockObject) {
                        Toast.makeText(context, "Investment Successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure() {
                        Toast.makeText(context, "Investment Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        holder.watchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebasePresenter.watch(stockList.get(position), new FirebasePresenter.StockListener() {
                    @Override
                    public void success(StockObject stockObject) {
                        Toast.makeText(context, "Added to watch list Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure() {
                        Toast.makeText(context, "Couldn't add to watch list!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stockName;
        TextView currentValue;
        TextView openingValue;
        TextView profitStatus;
        View root;

        View investBtn;
        View watchBtn;
        ImageView stockStatusImage;

        public ViewHolder(View view) {
            super(view);
            root = view.findViewById(R.id.root);

            investBtn = view.findViewById(R.id.invest_btn);
            watchBtn = view.findViewById(R.id.watch_btn);

            stockName = (TextView) view.findViewById(R.id.stockName);
            currentValue = (TextView) view.findViewById(R.id.currentValue);
            openingValue = (TextView) view.findViewById(R.id.openingValue);
            profitStatus = (TextView) view.findViewById(R.id.profitValue);
            stockStatusImage = (ImageView) view.findViewById(R.id.profitIndicator);
        }
    }
}
