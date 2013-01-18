package com.tallevi.android.mvc.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tallevi.android.mvc.MVCController;
import com.tallevi.android.mvc.callable.FutureCallable;
import com.tallevi.android.mvc.callable.Result;
import com.tallevi.android.mvc.example.OnLogin;
import com.tallevi.android.mvc.example.R;
import com.tallevi.android.mvc.model.MVCModel;

public class GitHubLoginForm extends LinearLayout implements Result {

	private EditText usernameEditText;
	private EditText passwordEditText;
	private TextView errorTextView;
	private MVCController<MVCModel> controller;
	private OnLogin onLogin;

	public GitHubLoginForm(Context context, AttributeSet attrs) {
		super(context, attrs);
		GitHubLoginForm form = (GitHubLoginForm) inflate(getContext(),
				R.layout.github_login_form, this);

		usernameEditText = (EditText) form.findViewById(R.id.username);
		passwordEditText = (EditText) form.findViewById(R.id.password);
		errorTextView = (TextView) form.findViewById(R.id.error_text);

		((Button) form.findViewById(R.id.login_button))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						login();
					}
				});
	}

	public void login() {
		controller = MVCController.createControllerFromModel();
		controller.link(this);
		controller.execute(new ResponseCallable());
	}

	@Override
	public void onSuccess(MVCModel model, View view) {
		onLogin.onLogin();
	}

	@Override
	public void onFail(View view) {
		errorTextView.setVisibility(VISIBLE);
	}

	class ResponseCallable implements FutureCallable<MVCModel> {

		@Override
		public MVCModel call() throws Exception {
			String username = usernameEditText.getText().toString();
			String password = passwordEditText.getText().toString();

			GitHubClient.login(username, password);
			
			return null;
		}

	}

	class RESTResponse implements MVCModel {
		
	}

	public void setOnLogin(OnLogin onLogin) {
		this.onLogin = onLogin;
	}

}
