package com.lamzone.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.lamzone.mareu.di.DI;
import com.lamzone.mareu.model.Collaborateur;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.model.Salle;
import com.lamzone.mareu.service.ReunionApiService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ReunionAdapter reunionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ReunionApiService myService = DI.getReunionApiService();
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        Log.d(TAG, "onCreate() called with: myService = [" + myService + "]");
        Log.d(TAG, "myService.getSalles() = [" + myService.getSalles() + "]");
        Log.d(TAG, "myService.getCollaborateurs() = [" + myService.getCollaborateurs() + "]");
        Log.d(TAG, "myService.getReunions() = [" + myService.getReunions() + "]");
        for (int i = 0; i < myService.getReunions().size() ; i++)
        {
            Reunion reunion = myService.getReunions().get(i);
            Log.d(TAG, "reunions.getId() = [" + reunion.getId() + "]");
            Log.d(TAG, "reunions.getSujet() = [" + reunion.getSujet() + "]");
            Log.d(TAG, "reunions.getDateDebut() = [" + reunion.getDateDebut() + "]");
            Log.d(TAG, "reunions.getDateFin() = [" + reunion.getDateFin() + "]");
            Log.d(TAG, "reunions.getIdSalle() = [" + reunion.getIdSalle() + "]");
            Salle salle = myService.findSalleById(reunion.getIdSalle());
            Log.d(TAG, "Salle.id = [" + salle.getId() + "]");
            Log.d(TAG, "Salle.getNom = [" + salle.getNom() + "]");

            Log.d(TAG, "reunions.getParticipants() = [" + reunion.getParticipants() + "]");

            for (int j = 0; j < reunion.getParticipants().size() ; j++)
            {
                long id = reunion.getParticipants().get(j);
                Collaborateur collaborateur = myService.findCollaborateurById(id);
                Log.d(TAG, "collaborateur.getId() = [" + collaborateur.getId() + "]");
                Log.d(TAG, "collaborateur.getEmail() = [" + collaborateur.getEmail() + "]");
            }
        }

        recyclerView = findViewById(R.id.reunion_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        reunionAdapter = new ReunionAdapter(myService.getReunions());
        recyclerView.setAdapter(reunionAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }
}