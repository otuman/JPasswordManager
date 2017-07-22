 package com.jerotoma.jpasswordmanager.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jerotoma.jpasswordmanager.R;
import com.jerotoma.jpasswordmanager.accounts.AccountStore;
import com.jerotoma.jpasswordmanager.views.AccountListView;

import java.util.ArrayList;

 public class AccountAdapter extends BaseAdapter {
	
	private ArrayList<AccountStore> account;
	private Context context;
    
	public AccountAdapter(ArrayList<AccountStore> accounts,Context context){
		super();
		this.account = accounts;
		this.context = context;
	}
	
	@Override
	public int getCount(){
		return account.size();
	}
    @Override
	public AccountStore getItem(int position) {
		return (null == account) ? null : account.get(position);
		
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AccountListView alv;
		if( null== convertView ){
			
			alv = (AccountListView)View.inflate(context, R.layout.account_list_view, null);
			
		}else {
			alv =(AccountListView)convertView;
		}
		alv.setAccountStore(account.get(position)); 
		return alv;
	}

	public void forceReload() {
		 notifyDataSetChanged();
	}

}
