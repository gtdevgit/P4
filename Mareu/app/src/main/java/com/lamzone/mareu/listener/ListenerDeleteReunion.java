package com.lamzone.mareu.listener;

import android.util.Log;
import android.view.View;

import com.lamzone.mareu.ReunionAdapter;
import com.lamzone.mareu.di.DI;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.service.ReunionApiService;

public class ListenerDeleteReunion implements View.OnClickListener{

    private static final String TAG = "ListenerDeleteReunion";

    private ReunionAdapter reunionAdapter;
    private Reunion reunion;

    public ListenerDeleteReunion(ReunionAdapter reunionAdapter, Reunion reunion) {
        this.reunion = reunion;
        this.reunionAdapter = reunionAdapter;
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick() called with: sujet = [" + this.reunion.getSujet() + "]");
        ReunionApiService myService = DI.getReunionApiService();
        myService.deleteReunion(this.reunion);
        this.reunionAdapter.notifyDataSetChanged();
    }
}
