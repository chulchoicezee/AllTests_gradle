package com.android.tests.activities;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.tests.R;

import java.net.HttpURLConnection;
import java.net.URL;

public class O_HttpTestActivity extends Activity{

	private static final String TAG = "O_HttpTestActivity";
	private String strUrl = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_button);

		strUrl = "http://www.naver.com";
	}

	public void onButtonClicked1(View view) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "clicked1", Toast.LENGTH_SHORT).show();
		AsyncTask<String, Void, String> execute = new AsyncTask<String, Void, String>() {
			@Override
			protected String doInBackground(String... params) {
				URL url = new URL(params[0]);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();

				return null;
			}
		}.execute(strUrl);
	}


}
