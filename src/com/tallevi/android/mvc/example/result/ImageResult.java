package com.tallevi.android.mvc.example.result;

import com.tallevi.android.mvc.callable.Result;
import com.tallevi.android.mvc.example.model.ImageModel;
import com.tallevi.android.mvc.model.MVCModel;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageResult implements Result {

	@Override
	public void onSuccess(MVCModel model, View view) {
		ImageView imageView = (ImageView) view;
		imageView.setImageBitmap(((ImageModel) model).getBitmap());
	}

	@Override
	public void onFail(View view) {
		Toast.makeText(view.getContext(), "onFail", Toast.LENGTH_SHORT).show();
	}

}
