package com.tallevi.android.mvc.example.view;

import java.io.IOException;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Base64;

public class GitHubClient {

	private static String realm;
	
	final private static String LOGIN_URL = "https://api.github.com/";
	final private static String REPOS_URL = "https://api.github.com/user/repos";

	public static String login(String username, String password) throws IllegalStateException, IOException {
		GitHubClient.realm = createRealm(username, password);
		HttpResponse response = executeGet(LOGIN_URL);
		return getContent(response);
	}
	
	public static String getRepositories() throws IllegalStateException, IOException {
		HttpResponse response = executeGet(REPOS_URL);
		return getContent(response);
	}
	
	private static HttpResponse executeGet(String url) throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(url);
		get.addHeader("Authorization", "Basic " + realm);
		DefaultHttpClient client = new DefaultHttpClient();
		return client.execute(get);
	}
	
	private static String getContent(HttpResponse response) throws IllegalStateException, IOException {
		Scanner scanner = new Scanner(response.getEntity().getContent());
		scanner.useDelimiter("\\A");
		
		return scanner.hasNext() ? scanner.next() : "";
	}
	
	private static String createRealm(String username, String password) {
		return Base64.encodeToString((username + ":" + password).getBytes(), Base64.NO_WRAP);
	}

}
