package com.tallevi.android.mvc.example.callable;

import java.io.InputStream;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.tallevi.android.mvc.callable.FutureCallable;
import com.tallevi.android.mvc.example.model.ImageModel;

public class ImageCallable implements FutureCallable<ImageModel> {

	private String url;

	public ImageCallable(String url) {
		this.url = url;
	}

	@Override
	public ImageModel call() throws Exception {
		Log.e("ImageCallable", "call");
		HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        request.setURI(new URI(url));
        HttpResponse response = client.execute(request);
        InputStream is = response.getEntity().getContent();
		return ImageModel.createFromInputStream(is);
	}


}
