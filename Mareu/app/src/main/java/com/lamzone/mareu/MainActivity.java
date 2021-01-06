package com.lamzone.mareu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import android.widget.DatePicker;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamzone.mareu.di.DI;
import com.lamzone.mareu.listener.ListenerNotifyDataChanged;
import com.lamzone.mareu.listener.ListenerSelectionSalle;
import com.lamzone.mareu.service.ReunionApiService;

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
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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
                break;
            case R.id.menu_item_filtrer_salle:
                filtrerSalle();
                break;
            case R.id.menu_item_supprimer_filtre:
                supprimerFiltre();
                break;
        }
        return true;
    }

    private void filtrerDate(){
        Log.d(TAG, "filtrerDate() called");
        // myService.filtrerReunionsParDate(new GregorianCalendar(2020, Calendar.DECEMBER, 10, 10, 30).getTime());
        // reunionAdapter.notifyDataSetChanged();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        DatePicker picker = new DatePicker(this);
        picker.setCalendarViewShown(false);

        builder.setTitle("Create Year");
        builder.setView(picker);
        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("Set", null);

        builder.show();
    }

    private void filtrerSalle(){
        Log.d(TAG, "filtrerSalle() called");
        FiltrerSalleDialog filtrerSalleDialog = new FiltrerSalleDialog(new ListenerSelectionSalle() {
            @Override
            public void onSelectionSalle(long Id) {
                myService.filtrerReunionsParSalle(Id);
                reunionAdapter.notifyDataSetChanged();
            }
        });
        filtrerSalleDialog.show(getSupportFragmentManager(), "MY_TAG");
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