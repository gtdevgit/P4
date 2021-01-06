package com.lamzone.mareu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.lamzone.mareu.di.DI;
import com.lamzone.mareu.listener.ListenerSelectRoom;
import com.lamzone.mareu.model.Salle;
import com.lamzone.mareu.service.ReunionApiService;

import java.util.ArrayList;
import java.util.List;

public class DialogSelectRoom extends DialogFragment {

    private static final String TAG="ChercherParSalle";
    private ListenerSelectRoom listenerSelectRoom;

    public DialogSelectRoom() {
    }

    public DialogSelectRoom(ListenerSelectRoom listenerSelectRoom) {
        this.listenerSelectRoom = listenerSelectRoom;
    }

    public ListenerSelectRoom getListenerSelectRoom() {
        return listenerSelectRoom;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // cré une liste de nom de salles
        ReunionApiService service = DI.getReunionApiService();
        List<String> lstNomSalle = new ArrayList<String>();
        for (Salle salle : service.getSalles()){
            lstNomSalle.add(salle.getNom());
        }
        String[] arrayNonSalle = lstNomSalle.toArray(new String[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Selectionner une salle")
               .setItems(arrayNonSalle, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        Log.d(TAG, "Item. onClick() called with: dialog = [" + dialog + "], which = [" + which + "]");
                        // le liste des noms est parallele à la liste des salles, aussi l'on peut utiliser l' index "which" pour recherche l'objet salle et obtenir son id.
                        long id = service.getSalles().get(which).getId();
                        // notification de selection d'une salel
                        getListenerSelectRoom().onSelectRoom(id);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "Cancel. onClick() called with: dialog = [" + dialog + "], which = [" + which + "]");
                    }
                })
        ;
        return builder.create();
    }
}
