package com.example.game_search;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.game_search.response.GameResponse;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> implements Filterable {
    List<GameResponse> gameArrayList;
    List<GameResponse> backupList;


    public GameAdapter( List<GameResponse> gameArrayList) {
        this.gameArrayList = gameArrayList;
        backupList = new ArrayList<>(gameArrayList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {
            holder.textView.setText(gameArrayList.get(position).getTitle());
            holder.textDesc.setText(gameArrayList.get(position).getBody());
            //holder.imageView.setImageResource((game.getGameImage()));
        }
        catch(Exception e){

        }

    }

    @Override
    public int getItemCount() {
        return gameArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return gameFilter;
    }

    Filter gameFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<GameResponse> filteredList = new ArrayList<>();


            Log.d("hello", String.valueOf(charSequence.toString().isEmpty()));
            if(charSequence.toString().isEmpty()==false){
                Log.d("hellooutside", "inside if"+gameArrayList.size());
                for(GameResponse obj: backupList){
                    Log.d("hellooutside", backupList.toString());
                    if(obj.getTitle().toString().toLowerCase().contains(charSequence.toString().toLowerCase()))
                    {
                        filteredList.add(obj);
                    }
                }
            }
            else{
                filteredList.addAll(backupList);
                Log.d("hellooutside", "I am else");
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            gameArrayList.clear();
            gameArrayList.addAll((ArrayList<GameResponse>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textDesc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView  = itemView.findViewById(R.id.name);
            textDesc  = itemView.findViewById(R.id.description);
        }
    }
}
