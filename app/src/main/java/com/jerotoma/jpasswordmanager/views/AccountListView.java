package com.jerotoma.jpasswordmanager.views;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jerotoma.jpasswordmanager.PasswordManagerApplication;
import com.jerotoma.jpasswordmanager.R;
import com.jerotoma.jpasswordmanager.accounts.AccountStore;

public class AccountListView extends RelativeLayout{
	private AccountStore account;
	private TextView acc_info;
	private TextView date;
	private TextView email;
	public PasswordManagerApplication app;
	
	public AccountListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		}
	@Override
	protected void onFinishInflate(){
        super.onFinishInflate();
        acc_info =(TextView)findViewById(R.id.acc_name);
        date     =(TextView)findViewById(R.id.date);
        email     =(TextView)findViewById(R.id.email);
             
        }

	public void setAccountStore(AccountStore account) {
		this.account = account;
			acc_info.setText(account.getAccount_name());
			email.setText(account.getEmail_address());
			date.setText(account.getDates());
    }
	
	public AccountStore getAccount() {
		return account;
	}
}
