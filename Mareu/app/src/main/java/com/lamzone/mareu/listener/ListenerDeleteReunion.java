package com.lamzone.mareu.listener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lamzone.mareu.R;
import com.lamzone.mareu.di.DI;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.service.ReunionApiService;

public class ListenerDeleteReunion implements View.OnClickListener{

    private static final String TAG = "ListenerDeleteReunion";

    private ListenerNotifyDataChanged listenerNotifyDataChanged;
    private Reunion reunion;

    public ListenerDeleteReunion(ListenerNotifyDataChanged listenerNotifyDataChanged, Reunion reunion) {
        this.listenerNotifyDataChanged = listenerNotifyDataChanged;
        this.reunion = reunion;
    }

    private void deleteReunion()
    {
        ReunionApiService myService = DI.getReunionApiService();
        myService.deleteReunion(this.reunion);
        listenerNotifyDataChanged.onDataChanged();
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick() called with: reunion.sujet = [" + this.reunion.getSujet() + "]");
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle(v.getContext().getString(R.string.delete_metting, this.reunion.getSujet()));
        builder.setMessage(R.string.are_you_sure);
        builder.setIcon(R.drawable.ic_baseline_contact_support_24);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(v.getContext(), R.string.confirm_deleted_metting, Toast.LENGTH_SHORT).show();
                deleteReunion();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(v.getContext(), R.string.cancel_deleted_metting, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
