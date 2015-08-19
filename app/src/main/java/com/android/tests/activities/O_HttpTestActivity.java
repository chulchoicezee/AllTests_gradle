package com.android.tests.activities;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.tests.R;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.json.Json;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;

import java.io.IOException;

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
		HttpTransport transport = new MockHttpTransport() {
			@Override
			public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
				return new MockLowLevelHttpRequest() {
					@Override
					public LowLevelHttpResponse execute() throws IOException {
						MockLowLevelHttpResponse response = new MockLowLevelHttpResponse();
						response.addHeader("custom_header", "value");
						response.setStatusCode(200);
						response.setContentType(Json.MEDIA_TYPE);
						response.setContent("{\"error\":\"not found\"}");
						return response;
					}
				};
			}
		};
		HttpRequest request = null;

		try {
			request = transport.createRequestFactory().buildGetRequest(HttpTesting.SIMPLE_GENERIC_URL);
			HttpResponse response = request.execute();
			System.out.println("hello "+response.getContent().toString());

		} catch (IOException e) {
			e.printStackTrace();
		}

		/*new AsyncTask<String, Void, String>() {
			@Override
			protected String doInBackground(String... params) {
                URL url = null;
                try {
                    url = new URL(params[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
			}
		}.execute(strUrl);*/
	}


}
