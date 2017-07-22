package com.jerotoma.jpasswordmanager.confidencials;

import java.util.ArrayList;

import com.jerotoma.jpasswordmanager.R;
import com.jerotoma.jpasswordmanager.views.FinancialListViewHolder;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class FinancialCustomeAdapter extends BaseAdapter {
        private ArrayList<FinancialInfo> financial;
        private Context context;
        
        
        
		public FinancialCustomeAdapter(ArrayList<FinancialInfo> financial, Context context){
             super();
            this.context= context;
            this.financial= financial;
        }

		@Override
		public int getCount(){
			return financial.size();
		}
	    @Override
		public FinancialInfo getItem(int position) {
			return (null == financial) ? null : financial.get(position);
			
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

     
        @Override
       public View getView(int position, View convertView, ViewGroup parent) {
        	FinancialListViewHolder fvh;
        	
        	if( null== convertView ){
     			
        		fvh = (FinancialListViewHolder)View.inflate(context, R.layout.financial_listview_holder, null);
     			
     		}else {
     			fvh =(FinancialListViewHolder)convertView;
     		}
        	fvh.setFinancialInfo(financial.get(position)); 
     		return fvh;
     	}

    public void forceReload() {
   		 notifyDataSetChanged();
   	}
        
}

