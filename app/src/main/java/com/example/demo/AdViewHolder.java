package com.example.demo;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.ads.nativetemplates.TemplateView;

public class AdViewHolder extends RecyclerView.ViewHolder {


     TemplateView Adtemplate;

    public AdViewHolder(@NonNull View itemView) {
        super(itemView);
        Adtemplate = itemView.findViewById(R.id.my_template);

    }

}
