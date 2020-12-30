package com.lamzone.mareu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamzone.mareu.di.DI;
import com.lamzone.mareu.listener.ListenerDeleteReunion;
import com.lamzone.mareu.listener.ListenerNotifyDataChanged;
import com.lamzone.mareu.model.Collaborateur;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.model.Salle;
import com.lamzone.mareu.service.ReunionApiService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int REQUESTCODE_START_ACTIVITY_ADD_ACTIVITY = 1001;

    private ListenerNotifyDataChanged listenerNotifyDataChanged;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ReunionAdapter reunionAdapter;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.reunion_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ReunionApiService myService = DI.getReunionApiService();
        listenerNotifyDataChanged = new ListenerNotifyDataChanged() {
            @Override
            public void onDataChanged() {
                Log.d(TAG, "onDataChanged() called");
                reunionAdapter.notifyDataSetChanged();
            }
        };
        reunionAdapter = new ReunionAdapter(myService, listenerNotifyDataChanged);
        recyclerView.setAdapter(reunionAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        floatingActionButton = findViewById(R.id.floatingActionButtonAdd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, REQUESTCODE_START_ACTIVITY_ADD_ACTIVITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUESTCODE_START_ACTIVITY_ADD_ACTIVITY) {
            if (resultCode == AddActivity.RESULTCODE_ANNULER_CRER_ACTIVITY) {
                Toast.makeText(getApplicationContext(), R.string.cancel_create_metting, Toast.LENGTH_SHORT).show();
            } else {
                if (resultCode == AddActivity.RESULTCODE_VALIDER_CRER_ACTIVITY)
                {
                    Toast.makeText(getApplicationContext(), R.string.confirm_create_metting, Toast.LENGTH_SHORT).show();
                    reunionAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}