package com.akameko.testforlifehackstudio.ui.main;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.akameko.testforlifehackstudio.R;
import com.akameko.testforlifehackstudio.repository.CompanyListItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

private List<CompanyListItem> companyListItems;
private OnItemClickListener itemClickListener;
private ViewGroup parent; //для предоставления локальных ресурсов в onBingViewHolder

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
    public ImageView imageView;



    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        companyName = itemView.findViewById(R.id.text_view_company_name);
        mainCard = itemView.findViewById(R.id.main_card_view);
        imageView = itemView.findViewById(R.id.imageView);
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
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.companyName.setText(companyListItems.get(position).getName());

        AssetManager am = parent.getResources().getAssets();

        try {
            InputStream is = am.open(companyListItems.get(position).getImg());
            holder.imageView.setImageDrawable( Drawable.createFromStream(is, String.valueOf(0)));
        } catch (IOException e) {
            e.printStackTrace();
        }






    }

    @Override
    public int getItemCount() {
        return companyListItems.size();
    }
}
