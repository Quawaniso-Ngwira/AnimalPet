package com.example.animalpet;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myviewholder> {

    private Context context;
    private static ArrayList<model> dataholder;


    public myAdapter(ArrayList<model> dataholder) {
        this.dataholder = dataholder;

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        holder.pname.setText(dataholder.get(position).getPetname());
        holder.pcolor.setText(dataholder.get(position).getPetcolor());
        holder.porigin.setText(dataholder.get(position).getPetorigin());
        holder.ptype.setText(dataholder.get(position).getPettype());
       /* holder.vstatus.setText(dataholder.get(position).getVaccinationstatus());
        holder.rdue.setText(dataholder.get(position).getRabiesdue());
        holder.pscheme.setText(dataholder.get(position).getHealthscheme());
        holder.vtname.setText(dataholder.get(position).getVetname());
        holder.vtaddress.setText(dataholder.get(position).getAddress());
        holder.vttreatments.setText(dataholder.get(position).getTreatments());
        holder.vtremarks.setText(dataholder.get(position).getVetrecomendation()); */
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ViewDetailsActivity.class);
                intent.putExtra("pname",dataholder.get(position).getPetname());
                intent.putExtra("pcolor",dataholder.get(position).getPetcolor());
                intent.putExtra("porigin",dataholder.get(position).getPetorigin());
                intent.putExtra("ptype",dataholder.get(position).getPettype());
                context.startActivity(intent);
            }
        });


        boolean isExpandable = dataholder.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }


    public void filterList(ArrayList<model> filteredList) {
        dataholder = filteredList;
       // myAdapter adapter1 = new myAdapter(filteredList);
        notifyDataSetChanged();

    }

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView pname, pcolor, ptype, porigin, vstatus, rdue, pscheme, vtname, vtaddress, vttreatments, vtremarks;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;
        ImageView deletePetEntry;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            pname = (TextView) itemView.findViewById(R.id.ptname);
            pcolor = (TextView) itemView.findViewById(R.id.ptcolor);
            ptype = (TextView) itemView.findViewById(R.id.ptType);
            porigin = (TextView) itemView.findViewById(R.id.ptOrigin);
            vtname = (TextView) itemView.findViewById(R.id.vtname);
            vtaddress = (TextView) itemView.findViewById(R.id.vtaddress);
            vttreatments = (TextView) itemView.findViewById(R.id.vttreatments);
            vtremarks = (TextView) itemView.findViewById(R.id.vtremarks);
            deletePetEntry = (ImageView) itemView.findViewById(R.id.deletePetEntry);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            context = itemView.getContext();


/*
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model myadapter = dataholder.get(getAdapterPosition());
                    myadapter.setExpandable(!myadapter.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            }); */
        }
    }
}