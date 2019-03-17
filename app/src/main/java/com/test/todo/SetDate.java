package com.test.todo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SetDate implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private TextView textView;
    private Calendar calendar;
    private Activity myActivity;

    public SetDate(TextView itextView, Activity activity) {
        this.textView = itextView;
        this.myActivity = activity;
        this.textView.setOnClickListener(this);
        calendar = Calendar.getInstance();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String myDateFormat = "dd-M-yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myDateFormat, Locale.getDefault());
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        textView.setText(simpleDateFormat.format(calendar.getTime()));
    }


    @Override
    public void onClick(View v) {
        new DatePickerDialog(myActivity, this, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
