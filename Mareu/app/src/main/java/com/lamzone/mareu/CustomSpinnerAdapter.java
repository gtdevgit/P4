package com.lamzone.mareu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lamzone.mareu.model.Salle;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<Salle> {

    private final List<Salle> salles;

    public CustomSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Salle> objects) {
        super(context, resource, objects);
        this.salles = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getDropDownView(position, convertView, parent);
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(final int position, View convertView, ViewGroup parent){
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_spinner, parent, false);
        final ImageView imageViewSalle = (ImageView) row.findViewById(R.id.custom_spinner_item_image);
        final TextView textViewSalle = (TextView) row.findViewById(R.id.custom_spinner_item_text);
        Salle salle = salles.get(position);

        imageViewSalle.setColorFilter(salle.getColor());
        textViewSalle.setText(salle.getNom());
        return row;
    }
}
