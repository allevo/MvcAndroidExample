package com.tallevi.android.mvc.example;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tallevi.android.mvc.MVCController;
import com.tallevi.android.mvc.callable.FutureCallable;
import com.tallevi.android.mvc.callable.Result;
import com.tallevi.android.mvc.example.view.GitHubClient;
import com.tallevi.android.mvc.model.MVCModel;

public class ListRepositoriesActivity extends Activity implements Result {

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_git_hub_list_repos);
		listView = ((ListView) findViewById(R.id.listView1));

		MVCController<RespositoryList> controller = MVCController
				.createControllerFromModel();
		controller.link(listView, this);
		controller.execute(new ListRepoCallback());
	}

	@Override
	public void onSuccess(MVCModel model, View view) {
		RespositoryList list = (RespositoryList) model;
		MyAdapter adapter = new MyAdapter(this, list.toArray(new Repository[list.size()]));
		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onFail(View view) {
		Toast.makeText(this, "Fail on retrieve your repos", Toast.LENGTH_LONG)
				.show();
		finish();
	}

	class MyAdapter extends ArrayAdapter<Repository> {

		private Repository[] list;
		private Context context;

		public MyAdapter(Context context, Repository[] list) {
			super(context, R.layout.rowlayout, list);
			this.context = context;
			this.list = list;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
			((TextView) rowView.findViewById(R.id.name))
					.setText(list[position].name);
			((TextView) rowView.findViewById(R.id.description))
					.setText(list[position].description);
			return rowView;
		}

	}

	class ListRepoCallback implements FutureCallable<RespositoryList> {

		@Override
		public RespositoryList call() throws Exception {
			String content = GitHubClient.getRepositories();
			RespositoryList repos = Repository
					.createFromJSONArray(new JSONArray(content));
			return repos;
		}

	}

	static class RespositoryList extends ArrayList<Repository> implements
			MVCModel {
		private static final long serialVersionUID = -8996058946347199105L;

	}

	static class Repository {

		String name;
		String description;

		public Repository(String name, String desc) {
			this.name = name;
			this.description = desc;
		}

		public static RespositoryList createFromJSONArray(JSONArray array)
				throws JSONException {
			RespositoryList repos = new RespositoryList();
			int len = array.length();
			for (int i = 0; i < len; i++) {
				repos.add(new Repository(array.getJSONObject(i).getString(
						"full_name"), array.getJSONObject(i).getString(
						"description")));
			}
			return repos;
		}

	}
}
