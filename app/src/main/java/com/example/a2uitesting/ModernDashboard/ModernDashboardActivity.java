package com.example.a2uitesting.ModernDashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.a2uitesting.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ModernDashboardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StaticRVAdapter staticRVAdapter;

    List<DynamicRVModel>  list=new ArrayList<>();
    DynamicRVAdapter dynamicRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_dashboard);

        ArrayList<StaticRVModel> item=new ArrayList<>();
        item.add(new StaticRVModel(R.drawable.pizza,"Pizza"));
        item.add(new StaticRVModel(R.drawable.burger,"Burger"));
        item.add(new StaticRVModel(R.drawable.pizza,"Pizza"));
        item.add(new StaticRVModel(R.drawable.burger,"Burger"));
        item.add(new StaticRVModel(R.drawable.pizza,"Pizza"));

        recyclerView=findViewById(R.id.rv1);
        staticRVAdapter =new StaticRVAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(staticRVAdapter);

        list.add(new DynamicRVModel("Burger"));
        list.add(new DynamicRVModel("Burger"));
        list.add(new DynamicRVModel("Burger"));
        list.add(new DynamicRVModel("Burger"));
        list.add(new DynamicRVModel("Burger"));
        list.add(new DynamicRVModel("Burger"));
        list.add(new DynamicRVModel("Burger"));
        list.add(new DynamicRVModel("Burger"));
        list.add(new DynamicRVModel("Burger"));
        list.add(new DynamicRVModel("Burger"));
        list.add(new DynamicRVModel("Burger"));
        list.add(new DynamicRVModel("Burger"));
        list.add(new DynamicRVModel("Burger"));
        list.add(new DynamicRVModel("Burger"));
        list.add(new DynamicRVModel("Burger"));
        list.add(new DynamicRVModel("Burger"));
        list.add(new DynamicRVModel("Burger"));
        list.add(new DynamicRVModel("Burger"));

        RecyclerView drv=findViewById(R.id.rv2);
        drv.setLayoutManager(new LinearLayoutManager(this));
        dynamicRVAdapter=new DynamicRVAdapter(drv,this,list);
        drv.setAdapter(dynamicRVAdapter);

        dynamicRVAdapter.setLoadMore(new LoadMore() {
            @Override
            public void onLoadMore() {
                if (list.size()<=10){
                    list.add(null);
                    dynamicRVAdapter.notifyItemInserted(list.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            list.remove(list.size()-1);
                            dynamicRVAdapter.notifyItemRemoved(list.size());

                            int index= list.size();
                            int end=index+10;
                            for (int i=index; i< end;i++){
                                String name= UUID.randomUUID().toString();
                                DynamicRVModel item=new DynamicRVModel(name);
                                list.add(item);
                            }
                            dynamicRVAdapter.notifyDataSetChanged();
                            dynamicRVAdapter.setLoaded();
                        }
                    },4000);
                    
                }
                else{
                    Toast.makeText(ModernDashboardActivity.this, "Data Completed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}



























