package com.akameko.testforlifehackstudio.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.akameko.testforlifehackstudio.R;
import com.akameko.testforlifehackstudio.repository.CompanyListItem;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

private List<CompanyListItem> companyListItems;
private OnItemClickListener itemClickListener;
//private ViewGroup parent; //для предоставления локальных ресурсов в onBingViewHolder

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

       // public void onLongItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this .itemClickListener = itemClickListener;
    }

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView companyName;
    public CardView mainCard;



    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        companyName = itemView.findViewById(R.id.text_view_company_name);
        mainCard = itemView.findViewById(R.id.main_card_view);
        mainCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null ) {
                    itemClickListener.onItemClick(v, getAdapterPosition());
                }
            }
        });

    }
}

    public MainAdapter(List<CompanyListItem> companyListItems) {
        this.companyListItems = companyListItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.companyName.setText(companyListItems.get(position).getName());

//        holder.cityName.setText(cityItem.getName());
//        holder.cityTemp.setText(String.format(parent.getContext().getResources().getString(R.string.display),
//                parent.getContext().getResources().getString(R.string.temperature), cityItem.getTemp(),
//                parent.getContext().getResources().getString(R.string.tempShort)));
//        holder.cityMoisture.setText(String.format(parent.getContext().getResources().getString(R.string.display),
//                parent.getContext().getResources().getString(R.string.moisture), cityItem.getMoisture(),
//                parent.getContext().getResources().getString(R.string.moistureShort)));
//        holder.cityPressure.setText(String.format(parent.getContext().getResources().getString(R.string.display),
//                parent.getContext().getResources().getString(R.string.pressure), cityItem.getPressure(),
//                parent.getContext().getResources().getString(R.string.pressureShort)));
//        holder.cityWindSpeed.setText(String.format(parent.getContext().getResources().getString(R.string.display),
//                parent.getContext().getResources().getString(R.string.wind_speed), cityItem.getWindSpeed(),
//                parent.getContext().getResources().getString(R.string.windSpeedShort)));



//        holder.cityMoisture.setText(cityItem.getMoisture());
////        holder.cityPressure.setText(cityItem.getPressure());
////        holder.cityWindSpeed.setText(cityItem.getWindSpeed());


    }

    @Override
    public int getItemCount() {
        return companyListItems.size();
    }
}
