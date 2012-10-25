package com.flyingzl;

import java.util.Calendar;

import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class DateUtil extends Plugin {

	private static final String DATE_METHOD = "date";

	private static final String TIME_METHOD = "time";

	private String callbackId;

	@Override
	public PluginResult execute(String action, JSONArray args, String callbackId) {

		this.callbackId = callbackId;

		PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);

		result.setKeepCallback(true);

		if (action.equals(DATE_METHOD)) {
			showDateDialog();
			return result;
		} else if (action.equals(TIME_METHOD)) {
			showTimeDialog();
			return result;
		}

		return new PluginResult(PluginResult.Status.INVALID_ACTION);

	}

	// 显示日期都为同步
	public boolean isSynch(String action) {
		return true;
	}

	/**
	 * 显示日期选择对话框
	 */
	private void showDateDialog() {

		final JSONObject object = new JSONObject();

		Calendar c = Calendar.getInstance();

		new DatePickerDialog(cordova.getActivity(),
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {

						try {
							object.put("year", year);
							object.put("month", monthOfYear);
							object.put("day", dayOfMonth);

							showResult(new PluginResult(Status.OK, object));

						} catch (JSONException e) {
							showResult(new PluginResult(
									PluginResult.Status.JSON_EXCEPTION));
						}

					}
				}, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH)).show();

	}

	/**
	 * 显示时间选择对话框
	 */
	private void showTimeDialog() {

		final JSONObject object = new JSONObject();

		Calendar c = Calendar.getInstance();
		
		new TimePickerDialog(cordova.getActivity(),
				new TimePickerDialog.OnTimeSetListener() {

					@Override
					public void onTimeSet(TimePicker paramTimePicker,
							int hourOfDay, int mintue) {
						try {
							object.put("hour", hourOfDay);
							object.put("minute", mintue);

							showResult(new PluginResult(Status.OK, object));

						} catch (JSONException e) {
							showResult(new PluginResult(
									PluginResult.Status.JSON_EXCEPTION));
						}

					}
				}, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true)
				.show();

	}

	// 回调函数处理
	private void showResult(PluginResult pluginResult) {
		if (pluginResult.getStatus() == Status.OK.ordinal()) {
			this.success(pluginResult, callbackId);
		} else {
			this.error(pluginResult, callbackId);
		}
	}

}
