package com.tallevi.android.mvc.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.tallevi.android.mvc.MVCController;
import com.tallevi.android.mvc.example.callable.ImageCallable;
import com.tallevi.android.mvc.example.model.ImageModel;
import com.tallevi.android.mvc.example.result.ImageResult;
import com.tallevi.android.mvc.view.MVCViewWrapper;

public class MainActivity extends Activity {
	

	private static final String GOOGLE_LOGO_URL = "https://www.google.it/images/srpr/logo3w.png";
	private static final String IAMBOO_LOGO_URL = "http://www.iamboo.it/images/stories/logoiamboo.png";


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
		controller.execute(new ImageCallable(IAMBOO_LOGO_URL));
	}

}
