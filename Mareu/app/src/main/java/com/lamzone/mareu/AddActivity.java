package com.lamzone.mareu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.lamzone.mareu.di.DI;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.model.Salle;
import com.lamzone.mareu.service.DummyGenerator;
import com.lamzone.mareu.service.ReunionApiService;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    public static final int RESULTCODE_ANNULER_CRER_ACTIVITY = 0;
    public static final int RESULTCODE_VALIDER_CRER_ACTIVITY = 1;

    // Composants
    private TextInputEditText textInputSujet;
    private Spinner spinnerSalle;
    private TextInputEditText textInputDate;
    TextInputEditText textInputEmail;
    ImageButton btAjouterEmail;
    private TextView textViewListeEmail;
    Button btAnnuler, btValider;

    ReunionApiService service;

    private void initService(){
        service = DI.getReunionApiService();
    }

    // Liaison avec la vue
    private void initComposants(){
        textInputSujet = (TextInputEditText) findViewById(R.id.input_sujet);
        spinnerSalle = (Spinner) findViewById(R.id.spinner_salle);
        textInputDate = (TextInputEditText) findViewById(R.id.input_date);
        textInputEmail = (TextInputEditText) findViewById(R.id.input_email);
        btAjouterEmail = (ImageButton) findViewById(R.id.button_ajouter_email);
        textViewListeEmail = (TextView) findViewById(R.id.input_liste_email);
        btAnnuler = (Button) findViewById(R.id.button_annuler_creer_reunion);
        btValider = (Button) findViewById(R.id.button_valider_creer_reunion);
    }

    // charge la liste des salles dans le spinner
    private void initSalles(){
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                service.arrayNomSalle());
        spinnerSalle.setAdapter(adapter);

    }

    private void initDatePicker(){
        textInputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
                                final int month = monthOfYear + 1;

                                new TimePickerDialog(v.getContext(),
                                        new TimePickerDialog.OnTimeSetListener() {

                                            @Override
                                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                                Calendar cal = Calendar.getInstance();
                                                cal.clear();
                                                cal.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);

                                                Date date = cal.getTime();

                                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
                                                String strDateDebut = dateFormat.format(date);
                                                textInputDate.setText(strDateDebut);
                                            }
                                        }, Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), true)
                                        .show();
                            }
                        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });

    }

    // trf le mail saisie dans la liste des mails
    private void initBtAjouterEmail(){
        btAjouterEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textInputEmail.getText().toString();
                if (email.length() > 0) {
                    String lstEmail = textViewListeEmail.getText().toString();
                    if (lstEmail.length() == 0){
                        textViewListeEmail.setText(email);
                    } else {
                        if (lstEmail.indexOf(email) == -1) {
                            textViewListeEmail.setText(lstEmail + "\n" + email);
                        }
                    }
                    textInputEmail.setText("");
                }
            }
        });
    }

    private void initBtAnnuler(){
        btAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULTCODE_ANNULER_CRER_ACTIVITY);
                finish();
            }
        });
    }

    private void intiBtValider(){
        btValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                idReunion = service.ajouterReunion(new reunion(...));
//                Intent intent = getIntent();
//                intent.putExtra('id_reunion', idReunion);
                String sujet = textInputSujet.getText().toString();
                // A changer en List<salle>
                String nomSalle = spinnerSalle.getSelectedItem().toString();
                Salle salle = service.findSalleByNom(nomSalle);
                String strDate = textInputDate.getText().toString();
//                GregorianCalendar grDate = new GregorianCalendar(())
                Date date = new Date();
                String strEmail = textViewListeEmail.getText().toString();
                String[] arrayEmail = strEmail.split("\n" );
                List<String> lstEmail = Arrays.asList(arrayEmail);
                Reunion reunion = new Reunion(-1L, sujet, date, date, salle.getId(), lstEmail);
                long newId = service.ajouterReunion(reunion);
                setResult(RESULTCODE_VALIDER_CRER_ACTIVITY);
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initService();
        initComposants();
        initSalles();
        initDatePicker();
        initBtAjouterEmail();
        initBtAnnuler();
        intiBtValider();
    }

}