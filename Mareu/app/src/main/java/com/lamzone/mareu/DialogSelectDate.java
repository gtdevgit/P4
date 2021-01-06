package com.lamzone.mareu;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.text.format.DateFormat;

import androidx.fragment.app.DialogFragment;

import com.lamzone.mareu.listener.ListenerSelectDate;

import java.util.Calendar;
import java.util.Date;

public class DialogSelectDate extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    private static final String TAG = "DialogSelectDate";
    private ListenerSelectDate listenerSelectDate;

    public DialogSelectDate() {
    }

    public DialogSelectDate(ListenerSelectDate listenerSelectDate) {
        this.listenerSelectDate = listenerSelectDate;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        boolean is24Hour = DateFormat.is24HourFormat(getActivity());

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar cal = Calendar.getInstance();
                        cal.clear();
                        cal.set(year, month, dayOfMonth, hourOfDay, minute);
                        Date date = cal.getTime();
                        listenerSelectDate.onSelectDate(date);
                    }
                }
                , hour, minute, is24Hour);
        timePickerDialog.show();
    }
}
