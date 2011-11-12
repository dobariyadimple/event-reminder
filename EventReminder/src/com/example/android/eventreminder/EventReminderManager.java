package com.example.android.eventreminder;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class EventReminderManager {

	private Context mContext;
	private AlarmManager mAlarmManager;
	
	public EventReminderManager(Context context) { 
		mContext = context;
		/* Get alarm manager */
		mAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE); 
	}
	
	public void setReminder(Long eventId, Calendar when) { 
		/* when alarm goes off, AlarmReceiver will be called */
		Intent intent = new Intent(mContext, AlarmReceiver.class); 
		intent.putExtra(eventDB.KEY_ROWID, (long)eventId); 
		/* using PendingIntent, inform application
		 * which action to perform  
		 * FLAG_ONE_SHOT means this PendingIntent can be used only once*/
		PendingIntent pi = PendingIntent.getBroadcast
			(mContext, 0, intent, PendingIntent.FLAG_ONE_SHOT); 
		/* set an alarm */
		mAlarmManager.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis(), pi); 
		/*type: AlarmManager.RTC_WAKEUP: wakes up when the specified triggerAtTime
				argument time elapses.
		triggerAtTime: when.getTimeInMillis(): time alarm should go off
						represents time in units of milliseconds. */
	}
}