package com.example.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.nativead.NativeAd;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    private mylistAdapter a_mylistAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<NativeAd> nativeAds;
    ArrayList<Object> myLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);

        myLists = new ArrayList<>();
        myLists = getData();

        addNativeAds();

        Log.e("TAG", "myLists.s: "+myLists.size() );



        layoutManager = new LinearLayoutManager(this);
        a_mylistAdapter = new mylistAdapter(MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(a_mylistAdapter);
        a_mylistAdapter.SetList(myLists);

        nativeAds = new ArrayList<>();


    }

    private ArrayList<Object> getData() {
        ArrayList<Object> myLists = new ArrayList<>();
        myLists.add(new mylist("Android", getString(R.string.Adesc1), R.drawable.android));
        myLists.add(new mylist("cpp", getString(R.string.cdesc), R.drawable.cpp));
        myLists.add(new mylist("java", getString(R.string.jdesc1), R.drawable.java));
        myLists.add(new mylist("php", getString(R.string.pdesc1), R.drawable.php));
        myLists.add(new mylist("python", getString(R.string.pydesc1), R.drawable.python));
        myLists.add(new mylist("Wordpress", getString(R.string.wdesc1), R.drawable.wp));
        return myLists;
    }

    public void addNativeAds(){
        for (int i = 0; i < myLists.size(); i++) {

            if(i%3 == 1){
                Log.e( "addNativeAds: ", "***"+i+"*******");
                myLists.add(i,"native");
            }
        }

        for (int i = 0; i < myLists.size(); i++) {
            Log.e( "list data: i::", i + " .."+ myLists.get(i)+"");
        }
    }


}
