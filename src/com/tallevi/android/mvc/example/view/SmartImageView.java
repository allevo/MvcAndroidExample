package com.tallevi.android.mvc.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tallevi.android.mvc.callable.Result;
import com.tallevi.android.mvc.example.R;
import com.tallevi.android.mvc.example.model.ImageModel;
import com.tallevi.android.mvc.model.MVCModel;

public class SmartImageView extends LinearLayout implements Result {

	private ImageView imageView;
	private TextView text;

	public SmartImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		View view = inflate(this.getContext(), R.layout.smart_image_view, this);
		imageView = (ImageView) view.findViewById(R.id.image);
		text = (TextView) view.findViewById(R.id.text);
	}

	@Override
	public void onSuccess(MVCModel model, View view) {
		ImageModel imageModel = (ImageModel) model;
		imageView.setImageBitmap(imageModel.getBitmap());
		imageView.setVisibility(View.VISIBLE);
		text.setVisibility(View.VISIBLE);
	}

	@Override
	public void onFail(View view) {
		text.setText("Fail to download image");
	}

}
