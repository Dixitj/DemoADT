package com.example.demoapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.R;
import com.example.demoapp.model.Result;
import com.example.demoapp.viewmodel.IDisplayInfo;
import com.squareup.picasso.Picasso;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoViewHolder> {

    private Context context;
    private Result result;
    private IDisplayInfo displayInfo;

    public DemoAdapter(Context context, Result result, IDisplayInfo displayInfo){

        this.context = context;
        this.result = result;
        this.displayInfo = displayInfo;

    }

    @NonNull
    @Override
    public DemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.card_view,parent,false);
        return new DemoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DemoViewHolder holder, int position) {

        holder.txtName.setText(result.getResults().get(position).getName());
        holder.txtStatus.setText(result.getResults().get(position).getStatus());
        holder.txtSpecies.setText(result.getResults().get(position).getSpecies());
        Picasso.with(context)
                .load(result.getResults().get(position).getImage())
                .fit()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return result.getResults().size();
    }

    public  void removeItem(int position){

        result.getResults().remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public class DemoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv;
        TextView  txtName,txtStatus,txtSpecies;
        ImageView imageView;

        public DemoViewHolder(@NonNull View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.cardview);
            cv.setOnClickListener(this);
            txtName = itemView.findViewById(R.id.txtName);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtSpecies = itemView.findViewById(R.id.txtSpecies);
            imageView = itemView.findViewById(R.id.imageView2);
        }

        @Override
        public void onClick(View view) {
            if (view == cv )

                displayInfo.displayInfo(result.getResults()
                        .get(getAdapterPosition()).getName());
        }
    }
}
