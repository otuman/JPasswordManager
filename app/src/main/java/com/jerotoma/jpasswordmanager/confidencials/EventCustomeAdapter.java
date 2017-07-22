package com.jerotoma.jpasswordmanager.confidencials;

import java.util.ArrayList;

import com.jerotoma.jpasswordmanager.R;
import com.jerotoma.jpasswordmanager.views.EventListViewHolder;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class EventCustomeAdapter extends BaseAdapter {
	private ArrayList<EventInfo> event;
    private Context context;
    
    
    
	public EventCustomeAdapter(Context context, ArrayList<EventInfo> event){
         super();
        this.context= context;
        this.event= event;
    }

	@Override
	public int getCount(){
		return event.size();
	}
    @Override
	public EventInfo getItem(int position) {
		return (null == event) ? null : event.get(position);
		
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

 
    @Override
   public View getView(int position, View convertView, ViewGroup parent) {
    	EventListViewHolder evh;
    	
    	if( null== convertView ){
 			
    		evh = (EventListViewHolder)View.inflate(context, R.layout.event_listview_holder, null);
 			
 		}else {
 			evh =(EventListViewHolder)convertView;
 		}
    	evh.setEventInfo(event.get(position)); 
 		return evh;

       
      }
    
    public void forceReload() {
   		 notifyDataSetChanged();
   	}
        
}

