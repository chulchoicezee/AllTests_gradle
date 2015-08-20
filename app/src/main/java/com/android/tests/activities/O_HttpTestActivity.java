package com.android.tests.activities;


import android.app.Activity;
import android.os.AsyncTask;
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
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public void onButtonClicked3(View v){


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                String url = "http://www.naver.com";
                String json = "{chulgee:hello, man:hello2}";

                OkHttp http = new OkHttp();
                try {
                    //String response = http.get("http://www.naver.com");
                    String response = http.post(url, json);
                    System.out.println("body = "+response);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute();


    }

    public class OkHttp{
        public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        String get(String url) throws IOException{
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            System.out.println("header = "+response.headers().toString());
            //System.out.println("body = "+response.body().string());

            return response.body().string();
        }

        String post(String url, String json) throws IOException{
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = client.newCall(request).execute();
            System.out.println("header = "+response.headers().toString());
            //System.out.println("body = "+response.body().string());
            return response.body().string();
        }
    }
}
