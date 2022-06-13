package com.example.kitchen_tablet;

import static com.example.kitchen_tablet.FBREF.refActive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class show extends AppCompatActivity {
    Bon b;
    LinkedList<Bon> meal_order_main;
    LinkedList<Meal>mealshow;
    ArrayList<String>mealshows;
    ArrayList<String>[]allMealShows;
    ArrayList<Meal>[]allMealShow;
    LinkedList<String>bonId;
    BroadcastReceiver minuteUpdateRciver;
    ArrayAdapter<String>adp;
    ValueEventListener vel;
    ListView[] all_lists;
    ArrayAdapter<String>[]all_adapters;
    TextView[]allTextViews;
    ListView list1,list2,list3,list4,list5,list6,list7,list8;
    TextView time1,time2,time3,time4,time5,time6,time7,time8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        setViews();
        bonId=new LinkedList<>();
        mealshow=new LinkedList<>();
        mealshows=new ArrayList<>();
        allMealShows=new ArrayList[8];
        allMealShow=new ArrayList[8];
        meal_order_main=new LinkedList<>();
        all_adapters=new ArrayAdapter[8];
        vel =new ValueEventListener() {
            /**
             * when the data changed takes all the bons from the data base or changes bons who was edited.
             * @param snapshot the database snapshot.
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data:snapshot.getChildren()) {
                    Bon tmp=data.getValue(Bon.class);
                    if(bonId.contains(tmp.getID())) {
                        int i = bonId.indexOf(tmp.getID());
                        meal_order_main.add(i, tmp);
                        ArrayList<String>TMPMEALS=new ArrayList<>();
                        b=meal_order_main.get(i);
                        TMPMEALS.add(b.getNote());
                        TMPMEALS.add(b.getTime());
                        int k = 0;
                        while (k < tmp.getShow().size()) {
                            if (b.getShow().get(k) == true) {
                                TMPMEALS.add(b.getB().get(k).toString());
                            }
                            k++;
                        }
                        allMealShows[i]=TMPMEALS;
                        all_adapters[i].notifyDataSetChanged();
                    }
                    else {
                        ArrayList<String>TMP=new ArrayList<>();
                        meal_order_main.add(tmp);
                        bonId.add(tmp.getID());

                        for(int j=0;j<meal_order_main.size();j++){
                            ArrayList<Boolean>b=meal_order_main.get(j).getShow();
                            if(!b.contains(true)){
                                meal_order_main.remove(j);
                            }
                        }
                        int l=0;
                        while (l<8&&l<meal_order_main.size()){
                            b=meal_order_main.get(l);
                            TMP.add(b.getNote());
                            TMP.add(b.getTime());
                            int i=0;
                            while(i<b.getShow().size()){
                                if(b.getShow().get(i)==true) {
                                    TMP.add(b.getB().get(i).toString());
                                }
                                i++;
                            }
                            allMealShows[l]=TMP;
                            TMP.clear();   
                            all_adapters[l]=new ArrayAdapter<String>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,allMealShows[l]);
                            all_lists[l].setAdapter(adp);
                            l++;
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        refActive.addValueEventListener(vel);
    }
    /**
     * sets all the list and text views in their arrays.
     */
    private void setViews() {
        list1=(ListView)findViewById(R.id.list1);
        list2=(ListView)findViewById(R.id.list2);
        list3=(ListView)findViewById(R.id.list3);
        list4=(ListView)findViewById(R.id.list4);
        list5=(ListView)findViewById(R.id.list5);
        list6=(ListView)findViewById(R.id.list6);
        list7=(ListView)findViewById(R.id.list7);
        list8=(ListView)findViewById(R.id.list8);
        time1=(TextView)findViewById(R.id.time1);
        time2=(TextView)findViewById(R.id.time2);
        time3=(TextView)findViewById(R.id.time3);
        time4=(TextView)findViewById(R.id.time4);
        time5=(TextView)findViewById(R.id.time5);
        time6=(TextView)findViewById(R.id.time6);
        time7=(TextView)findViewById(R.id.time7);
        time8=(TextView)findViewById(R.id.time8);
        all_lists= new ListView[]{list1, list2, list3, list4, list5, list6, list7, list8};
        allTextViews= new TextView[]{time1,time2,time3,time4,time5,time6,time7,time8};
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (vel!=null) {
            refActive.removeEventListener(vel);
        }
        unregisterReceiver(minuteUpdateRciver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startMinuteUpdater();
    }
    /**
     * every minute change the time passed from each bon shown and checks if changing bons is needed.
     */
    public void startMinuteUpdater(){
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        minuteUpdateRciver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show();
                String time=new SimpleDateFormat("HHmmss").format(new Date());
                long t=Time.TimetoInt(time)-Time.TimetoInt(meal_order_main.getFirst().getTime().substring(0,5));
                time1.setText("time"+Time.TimeToString(t));
            }
        };
        registerReceiver(minuteUpdateRciver,intentFilter);
    }
}