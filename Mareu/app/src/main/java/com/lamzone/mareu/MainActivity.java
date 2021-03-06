package com.lamzone.mareu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamzone.mareu.di.DI;
import com.lamzone.mareu.listener.ListenerNotifyDataChanged;
import com.lamzone.mareu.listener.ListenerSelectDate;
import com.lamzone.mareu.listener.ListenerSelectRoom;
import com.lamzone.mareu.service.ReunionApiService;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int REQUESTCODE_START_ACTIVITY_ADD_ACTIVITY = 1001;

    private ListenerNotifyDataChanged listenerNotifyDataChanged;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ReunionAdapter reunionAdapter;
    private FloatingActionButton floatingActionButton;

    ReunionApiService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.reunion_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        myService = DI.getReunionApiService();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Affichage du menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Actions du menu
        switch (item.getItemId()) {
            case R.id.menu_item_filtrer_date :
                filtrerDate();
                return true;
            case R.id.menu_item_filtrer_salle:
                filtrerSalle();
                return true;
            case R.id.menu_item_supprimer_filtre:
                supprimerFiltre();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void filtrerDate(){
        Log.d(TAG, "filtrerDate() called");
        DialogSelectDate dialogSelectDate = new DialogSelectDate(new ListenerSelectDate() {
            @Override
            public void onSelectDate(Date date) {
                Log.d(TAG, "onSelectDate() called with: date = [" + date + "]");
                myService.filtrerReunionsParDate(date);
                reunionAdapter.notifyDataSetChanged();
            }
        });
        dialogSelectDate.show(getSupportFragmentManager(), "MY_TAG_DATE");
    }

    private void filtrerSalle(){
        Log.d(TAG, "filtrerSalle() called");
        DialogSelectRoom dialogSelectRoom = new DialogSelectRoom(new ListenerSelectRoom() {
            @Override
            public void onSelectRoom(long Id) {
                myService.filtrerReunionsParSalle(Id);
                reunionAdapter.notifyDataSetChanged();
            }
        });
        dialogSelectRoom.show(getSupportFragmentManager(), "MY_TAG_SALLE");
    }

    private void supprimerFiltre(){
        Log.d(TAG, "supprimerFiltre() called");
        myService.supprimerFiltreReunion();
        reunionAdapter.notifyDataSetChanged();
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