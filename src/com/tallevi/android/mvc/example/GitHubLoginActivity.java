package com.tallevi.android.mvc.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tallevi.android.mvc.example.view.GitHubLoginForm;

public class GitHubLoginActivity extends Activity implements OnLogin {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_git_hub_login);
		
		((GitHubLoginForm) findViewById(R.id.gitHubLoginForm1)).setOnLogin(this);
	}

	@Override
	public void onLogin() {
		startActivity(new Intent(this, ListRepositoriesActivity.class));
	}

	
}
