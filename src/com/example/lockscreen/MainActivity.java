package com.example.lockscreen;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity 
{
	private DevicePolicyManager policyManager;
	private ComponentName componentName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		policyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		componentName = new ComponentName(this, LockReceiver.class);
		if(policyManager.isAdminActive(componentName))
		{
			policyManager.lockNow();
			finish();
		}
		else
		{
			activityManager();
			finish();
		}
	}
	private void activityManager()
	{
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Ò»¼üËøÆÁ");
		startActivity(intent);
	}
}
