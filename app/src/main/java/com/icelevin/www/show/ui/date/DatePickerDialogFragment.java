package com.icelevin.www.show.ui.date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by ice on 2017/11/3.
 */

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private String date;
    private Context context;

    public DatePickerDialogFragment(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(context, this, year, month, day);
        DatePicker datePicker = dialog.getDatePicker();
        datePicker.setMaxDate(System.currentTimeMillis());
        return dialog;

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        TimePickerFragment timePicker = new TimePickerFragment();
//        timePicker.show(getFragmentManager(), "time_picker");
//        //将用户选择的日期传到TimePickerFragment
//        date = year + "年" + (month + 1) + "月" + dayOfMonth + "日";
//        timePicker.setTime(date);
        if (context instanceof OnSetDate) {

            OnSetDate date = (OnSetDate) context;
            date.getDate(year + "-" + month + 1 + "-" + dayOfMonth);

        }

    }

    public interface OnSetDate {
        void getDate(String date);
    }
}
