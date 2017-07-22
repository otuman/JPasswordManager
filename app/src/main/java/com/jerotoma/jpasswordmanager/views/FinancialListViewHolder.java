package com.jerotoma.jpasswordmanager.views;

import com.jerotoma.jpasswordmanager.PasswordManagerApplication;
import com.jerotoma.jpasswordmanager.R;
import com.jerotoma.jpasswordmanager.confidencials.FinancialInfo;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class FinancialListViewHolder extends RelativeLayout {
	 private TextView name;
     private TextView date;
     private TextView type;
     FinancialInfo financial;
     PasswordManagerApplication app;
	
  	public FinancialListViewHolder(Context context, AttributeSet attrs) {
  		super(context, attrs);
  		}
  	@Override
  	protected void onFinishInflate(){
          super.onFinishInflate();
          name    =(TextView)findViewById(R.id.name);
          date     =(TextView)findViewById(R.id.date);
          type     =(TextView)findViewById(R.id.type);
               
          }

  	public void setFinancialInfo(FinancialInfo financial) {
  		this.financial = financial;
  		if(financial.getFinancial_name().equals("N/A")){
  			name.setText(financial.getFinancial_number());
  		}
  		else{
  			name.setText(financial.getFinancial_name());
  		}
  		
  		date.setText(financial.getDate());
  		type.setText(financial.getFinancial_type());
      }
  	
  	public FinancialInfo getFinancial() {
		return financial;
	}
		
    }

