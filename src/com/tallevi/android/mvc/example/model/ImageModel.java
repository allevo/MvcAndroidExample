package com.tallevi.android.mvc.example.model;

import java.io.InputStream;

import com.tallevi.android.mvc.model.MVCModel;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class ImageModel implements MVCModel {

	private Bitmap bitmap;

	public static ImageModel createFromInputStream(InputStream is) {
		ImageModel model = new ImageModel();
		model.bitmap = BitmapFactory.decodeStream(is);
		return model;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}

}
