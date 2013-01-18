package com.tallevi.android.mvc.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.tallevi.android.mvc.MVCController;
import com.tallevi.android.mvc.example.callable.ImageCallable;
import com.tallevi.android.mvc.example.model.ImageModel;
import com.tallevi.android.mvc.example.result.ImageResult;
import com.tallevi.android.mvc.example.view.ImageSwitcher;
import com.tallevi.android.mvc.example.view.SmartImageView;
import com.tallevi.android.mvc.view.MVCViewWrapper;

public class MainActivity extends Activity implements OnClickListener{
	

	private static final String GOOGLE_LOGO_URL = "https://www.google.it/images/srpr/logo3w.png";
	private static final String IAMBOO_LOGO_URL = "https://twimg0-a.akamaihd.net/profile_images/1325984524/logoiamboo_normal.png";
	private static final String GITHUB_LOGO_URL = "http://www.lamahe.com/lamahe_images/links/github.jpg";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ImageView view1 = (ImageView) findViewById(R.id.image_view1);
		ImageView view2 = (ImageView) findViewById(R.id.image_view2);
		ImageView view3 = (ImageView) findViewById(R.id.image_view3);

		MVCController<ImageModel> controller = MVCController.createControllerFromModel();
		MVCViewWrapper view = new MVCViewWrapper(view1);
		controller.link(view, new ImageResult());
		view = new MVCViewWrapper(view2);
		controller.link(view, new ImageResult());
		controller.execute(new ImageCallable(GOOGLE_LOGO_URL));

		controller = MVCController.createControllerFromModel();
		controller.link(view3, new ImageResult());
		controller.link((SmartImageView) findViewById(R.id.smartImageView1));
		controller.execute(new ImageCallable(IAMBOO_LOGO_URL));
		
		((Button) findViewById(R.id.github_login_button)).setOnClickListener(this);
		
		ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
		imageSwitcher.addImageUrl(GOOGLE_LOGO_URL);
		imageSwitcher.addImageUrl(IAMBOO_LOGO_URL);
		imageSwitcher.addImageUrl(GITHUB_LOGO_URL);
		imageSwitcher.start();
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.github_login_button:
			startActivity(new Intent(this, GitHubLoginActivity.class));
			break;
		}
	}

}
