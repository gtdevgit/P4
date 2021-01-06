package com.lamzone.mareu;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.mareu.di.DI;
import com.lamzone.mareu.listener.ListenerDeleteReunion;
import com.lamzone.mareu.listener.ListenerNotifyDataChanged;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.model.Salle;
import com.lamzone.mareu.service.ReunionApiService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReunionAdapter extends RecyclerView.Adapter<ReunionAdapter.ReunionViewHolder> {

    public static class ReunionViewHolder extends RecyclerView.ViewHolder {

        TextView sujet;
        TextView salle;
        TextView date;
        TextView participants;
        ImageView rond;
        ImageButton buttonDelete;

        public ReunionViewHolder(@NonNull View itemView) {
            super(itemView);
            sujet = itemView.findViewById(R.id.reunion_subject);
            salle = itemView.findViewById(R.id.reunion_room);
            date = itemView.findViewById(R.id.reunion_date);
            participants = itemView.findViewById(R.id.reunion_participant);
            rond = itemView.findViewById(R.id.image_rond);
            buttonDelete = itemView.findViewById(R.id.button_delete);
        }
    }

    private ReunionApiService service;
    private ListenerNotifyDataChanged listenerNotifyDataChanged;
    private ListenerDeleteReunion listenerDeleteReunion;

    public ReunionAdapter(ReunionApiService service, ListenerNotifyDataChanged listenerNotifyDataChanged) {
        this.service = service;
        this.listenerNotifyDataChanged = listenerNotifyDataChanged;
    }

    public ListenerNotifyDataChanged getListenerNotifyDataChanged() {
        return listenerNotifyDataChanged;
    }

    public void setListenerNotifyDataChanged(ListenerNotifyDataChanged listenerNotifyDataChanged) {
        this.listenerNotifyDataChanged = listenerNotifyDataChanged;
    }

    @NonNull
    @Override
    public ReunionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_reunion, parent, false);
        ReunionViewHolder reunionViewHolder = new ReunionViewHolder(view);
        return reunionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReunionViewHolder holder, int position) {
        Reunion reunion = service.getReunions().get(position);
        Salle salle = service.findSalleById(reunion.getIdSalle());

        holder.sujet.setText(reunion.getSujet());
        holder.salle.setText(salle.getNom());
        Date dateDebut = reunion.getDateDebut();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
        String strDateDebut = dateFormat.format(dateDebut);
        holder.date.setText(strDateDebut);
        holder.participants.setText(reunion.listeParticipantToString());

        int color = salle.getColor();
        holder.rond.setColorFilter(color);
        holder.buttonDelete.setOnClickListener(new ListenerDeleteReunion(getListenerNotifyDataChanged(), reunion));
    }

    @Override
    public int getItemCount() {
        return service.getReunions().size();
    }
}
