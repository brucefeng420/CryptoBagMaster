package au.edu.unsw.infs3634.cryptobag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.ls.LSOutput;

import java.text.NumberFormat;
import java.util.ArrayList;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.CoinViewHolder> {
    private ArrayList<Coin> mCoins;
    private RecyclerViewClickListener mListener;

    public CoinAdapter(ArrayList<Coin> coins, RecyclerViewClickListener listener) {
        mCoins = coins;
        mListener = listener;
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public static class CoinViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, value, change;
        private RecyclerViewClickListener mListener;

        public CoinViewHolder(View v, RecyclerViewClickListener listener) {
            super(v);
            mListener = listener;
            v.setOnClickListener(this);
            name = v.findViewById(R.id.tvName);
            value = v.findViewById(R.id.tvValue);
            change = v.findViewById(R.id.tvChange);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
//            int pos = getAdapterPosition();
//
//
//            System.out.println("ADAPTER " + pos);
        }
    }

    @Override
    public CoinAdapter.CoinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_list_row, parent, false);
        return new CoinViewHolder(v, mListener);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CoinViewHolder holder, int position) {
        Coin coin = mCoins.get(position);
        holder.name.setText(coin.getName());
        holder.value.setText(NumberFormat.getCurrencyInstance().format(coin.getValue()));
        holder.change.setText(String.valueOf(coin.getChange1h()) + " %");

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCoins.size();
    }

}