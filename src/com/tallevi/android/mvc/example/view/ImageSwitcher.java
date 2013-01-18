package com.tallevi.android.mvc.example.view;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.tallevi.android.mvc.MVCController;
import com.tallevi.android.mvc.callable.Result;
import com.tallevi.android.mvc.example.callable.ImageCallable;
import com.tallevi.android.mvc.example.model.ImageModel;
import com.tallevi.android.mvc.model.MVCModel;

public class ImageSwitcher extends ImageView implements Result {

	private ArrayList<String> imageUrls = new ArrayList<String>();
	private int imageUrlIndex = 0;

	MVCController<ImageModel> controller;
	private Runnable runnable;
	private Handler handler;
	private long period = 2000;

	public ImageSwitcher(Context context, AttributeSet attrs) {
		super(context, attrs);
		controller = MVCController.createControllerFromModel();
		controller.link(this);

		handler = new Handler();
		runnable = new Runnable() {
			@Override
			public void run() {
				controller.execute(new ImageCallable(imageUrls.get(imageUrlIndex)));
			}
		};
	}

	public void start() {
		handler.postDelayed(runnable, period );
	}

	public void addImageUrl(String url) {
		imageUrls.add(url);
	}
	
	public void setPeriod(int period) {
		this.period = period;
	}

	@Override
	public void onSuccess(MVCModel model, View view) {
		imageUrlIndex = (imageUrlIndex + 1) % imageUrls.size();
		setImageBitmap(((ImageModel) model).getBitmap());

		handler.postDelayed(runnable, period );
	}

	@Override
	public void onFail(View view) {

	}
}
