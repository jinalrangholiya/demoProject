package com.example.demo;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    public   ImageView imageView;
    public  TextView textView, details;
    public RelativeLayout relativeLayout;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        textView = (TextView) itemView.findViewById(R.id.textView);
        details = (TextView) itemView.findViewById(R.id.detail);
        relativeLayout=itemView.findViewById(R.id.relativeLayout);

    }
}
