package com.lamzone.mareu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.mareu.di.DI;
import com.lamzone.mareu.model.Collaborateur;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.model.Salle;
import com.lamzone.mareu.service.ReunionApiService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReunionAdapter extends RecyclerView.Adapter<ReunionAdapter.ReunionViewHolder> {

    private List<Reunion> listeReunion;
    private ReunionApiService service = DI.getReunionApiService();

    public static class ReunionViewHolder extends RecyclerView.ViewHolder {

        TextView sujet;
        TextView salle;
        TextView id;
        TextView debut;
        TextView fin;
        TextView participants;

        public ReunionViewHolder(@NonNull View itemView) {
            super(itemView);
            sujet = itemView.findViewById(R.id.reunion_sujet);
            salle = itemView.findViewById(R.id.reunion_salle);
            id = itemView.findViewById(R.id.reunion_id);
            debut = itemView.findViewById(R.id.reunion_date_debut);
            fin = itemView.findViewById(R.id.reunion_date_fin);
            participants = itemView.findViewById(R.id.reunion_participant);
        }
    }

    public ReunionAdapter(List<Reunion> listeReunion) {
        this.listeReunion = listeReunion;
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
        Reunion reunion = listeReunion.get(position);
        holder.sujet.setText(reunion.getSujet());

        Salle salle = service.findSalleById(reunion.getIdSalle());
        holder.salle.setText(salle.getNom());

        holder.id.setText(reunion.getId().toString());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        Date dateDebut = reunion.getDateDebut();
        String strDateDebut = dateFormat.format(dateDebut);
        holder.debut.setText(strDateDebut);

        Date dateFin = reunion.getDateFin();
        String strDateFin = dateFormat.format(dateFin);
        holder.fin.setText(strDateFin);

        String strParticipants = listeParticipantToString(reunion.getParticipants());
        holder.participants.setText(strParticipants);
    }

    private String listeParticipantToString(List<Long> participants){
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < participants.size() ; i++)
        {
            long id = participants.get(i);
            Collaborateur collaborateur = service.findCollaborateurById(id);
            str.append(collaborateur.getEmail());
            str.append("; ");
        }
        return str.toString();
    }

    @Override
    public int getItemCount() {
        return listeReunion.size();
    }


}
