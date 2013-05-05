package com.flyingzl;


import java.util.Calendar;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class DateUtil extends CordovaPlugin {

	private static final String DATE_METHOD = "date";

	private static final String TIME_METHOD = "time";

	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) {

		if (action.equals(DATE_METHOD)) {
			showDateDialog(callbackContext);
			// this.cordova.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			return true;
		} else if (action.equals(TIME_METHOD)) {
			showTimeDialog(callbackContext);
			return true;
		}

		return false;

	}

	/**
	 * 显示日期选择对话框
	 */
	private synchronized void showDateDialog(
			final CallbackContext callbackContext) {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				final JSONObject object = new JSONObject();
				Calendar c = Calendar.getInstance();
				DatePickerDialog dlg = new DatePickerDialog(
						cordova.getActivity(),
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								try {
									object.put("year", year);
									object.put("month", monthOfYear);
									object.put("day", dayOfMonth);
									callbackContext.success(object);

								} catch (JSONException e) {
									callbackContext.error(e.getMessage());
								}

							}
						}, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
						c.get(Calendar.DAY_OF_MONTH));

				dlg.show();
			}
		};

		this.cordova.getActivity().runOnUiThread(runnable);

	}

	/**
	 * 显示时间选择对话框
	 */
	private synchronized void showTimeDialog(
			final CallbackContext callbackContext) {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				final JSONObject object = new JSONObject();
				Calendar c = Calendar.getInstance();
				TimePickerDialog dlg = new TimePickerDialog(
						cordova.getActivity(),
						new TimePickerDialog.OnTimeSetListener() {

							@Override
							public void onTimeSet(TimePicker paramTimePicker,
									int hourOfDay, int mintue) {
								try {
									object.put("hour", hourOfDay);
									object.put("minute", mintue);
									callbackContext.success(object);

								} catch (JSONException e) {
									callbackContext.error(e.getMessage());

								}

							}
						}, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
						true);

				dlg.show();

			}

		};

		this.cordova.getActivity().runOnUiThread(runnable);

	}

}
