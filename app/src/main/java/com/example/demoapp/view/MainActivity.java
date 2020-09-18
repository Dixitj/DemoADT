package com.example.demoapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
import com.example.demoapp.R;
import com.example.demoapp.model.Result;
import com.example.demoapp.viewmodel.DemoViewModel;
import com.example.demoapp.viewmodel.IDisplayInfo;

public class MainActivity extends AppCompatActivity implements IDisplayInfo {

    RecyclerView recyclerView ;
    DemoViewModel demoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demoViewModel = ViewModelProviders.of(this).get(DemoViewModel.class);
        initview();
    }

    @Override
    protected void onResume() {
        super.onResume();
        demoViewModel.dataList().observe(this, new Observer<Result>() {
            @Override
            public void onChanged(Result result) {

                if(result.getInfo() != null || result.getResults().size() == 0) {

                    final DemoAdapter demoAdapter = new DemoAdapter(getApplicationContext(),result,MainActivity.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(demoAdapter);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
                        @Override
                        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                            return false;
                        }

                        @Override
                        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                            demoAdapter.removeItem(viewHolder.getAdapterPosition());

                        }
                    }).attachToRecyclerView(recyclerView);

                }else
                    Toast.makeText(getApplicationContext(),"No data",Toast.LENGTH_LONG).show();

            }
        });
    }

    private void initview() {
        recyclerView = findViewById(R.id.recyclerview);
    }

    @Override
    public void displayInfo(String name) {

        Toast.makeText(this,name,Toast.LENGTH_LONG).show();
    }
}
