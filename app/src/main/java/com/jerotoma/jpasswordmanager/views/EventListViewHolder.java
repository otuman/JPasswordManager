
package com.jerotoma.jpasswordmanager.views;

import com.jerotoma.jpasswordmanager.PasswordManagerApplication;
import com.jerotoma.jpasswordmanager.R;
import com.jerotoma.jpasswordmanager.confidencials.EventInfo;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class EventListViewHolder extends RelativeLayout {
	 private TextView name;
     private TextView date;
     private TextView type;
     EventInfo event;
     PasswordManagerApplication app;
  	public EventListViewHolder(Context context, AttributeSet attrs) {
  		super(context, attrs);
  		}
  	@Override
  	protected void onFinishInflate(){
          super.onFinishInflate();
          name    =(TextView)findViewById(R.id.name);
          date     =(TextView)findViewById(R.id.date);
          type     =(TextView)findViewById(R.id.type);
               
          }
  	public void setEventInfo(EventInfo event) {
  		this.event = event;
  		name.setText(event.getEventName());
  		date.setText(event.getDateCreated());
  		type.setText(event.getEventType());
      }
  	
  	public EventInfo getFinancial() {
		return event;
	}
		
    }

