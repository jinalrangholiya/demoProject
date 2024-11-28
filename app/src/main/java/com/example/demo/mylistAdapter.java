package com.example.demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Ads.MyAds;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.ArrayList;
import java.util.List;

public class mylistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int IS_AD = 0;
    private static final int NOT_AD =1 ;
    private List<NativeAd> nativeAds;
    private final ArrayList<Object> object=new ArrayList<>();
    Context context;
    AdLoader adLoader;
   // private TemplateView templateView;

    public void SetList(List<Object> list)
    {
        this.object.addAll(list);
    }


    public mylistAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.e( "onCreateViewHolder: ","viewType::"+viewType );
           if(viewType == IS_AD)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.native_ads,parent,false);
                return new AdViewHolder(view);
            }
            else {
               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
               return new ItemViewHolder(view);
           }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

            if(getItemViewType(position)==0) {
                  Adclass adclass = new Adclass();

                  MyAds myAds=new MyAds(context);
                  myAds.NativeAds(context,((AdViewHolder) holder).Adtemplate);
                  adclass.setAdLoader(adLoader);
                }
            else
                 {
                     mylist ma=(mylist) object.get(position);
                      ItemViewHolder ivh=(ItemViewHolder) holder;
                      ivh.imageView.setImageResource(ma.getImage());
                      ivh.textView.setText(ma.getText());
                      ivh.details.setText(ma.getDetail());
                      ivh.itemView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                             Log.e("a", "onClick: ***************");

                             Intent i = new Intent(context, Android.class);
                             i.putExtra("Image",((mylist) object.get(position)).getImage());
                             i.putExtra("name",((mylist) object.get(position)).getText());
                             i.putExtra("details",((mylist) object.get(position)).getDetail());
                             context.startActivity(i);
                     }

                 });
            }
    }
    @Override
    public int getItemCount() {

        return object.size();
    }


    @Override
    public int getItemViewType(int position) {
            if(position%3 == 1){
                return IS_AD;
            }
            else
            {
                return NOT_AD;
            }
    }

}
  /**  @NonNull
    @Override
    public mylistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View listitem = inflater.inflate(R.layout.list_item,parent,false);
        return new mylistAdapter.MyViewHolder(listitem);
    }


    @Override
    public void onBindViewHolder(@NonNull RecycleView.ViewHolder holder, int position) {

    }

    @Override
    public  void onBindViewHolder(@NonNull mylistAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

              final mylist mydata = listdata[position];
            holder.textView.setText(listdata[position].getText());
            holder.details.setText(listdata[position].getDetail());
            holder.imageView.setImageResource(listdata[position].getImage());
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {


                    if (position == 0) {

                        Intent i = new Intent(context, Android.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    } else if (position == 1) {
                        Intent i = new Intent(context, Cpp.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    } else if (position == 2) {
                        Intent i = new Intent(context, java.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    } else if (position == 3) {
                        Intent i = new Intent(context, php.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    } else if (position == 4) {
                        Intent i = new Intent(context, python.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    } else {
                        Intent i = new Intent(context, Wordpress.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    }


                }
            });
        }


    @Override
    public int getItemCount() {

        return listdata.length;
    }
    @Override
    public int getItemViewType(int position) {
        if (AD_LOGIC_CONDITION)
        {
            return AD_TYPE;
        }
        else
        {
            return CONTENT_TYPE; ///do not forget to initialize any of AD_TYPE and CONTENT_TYPE
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView, details;
        RelativeLayout relativeLayout;
        mylist Mylist;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            this.details = (TextView) itemView.findViewById(R.id.detail);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }*/





