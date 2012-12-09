package com.pdg.ticket;

import java.text.ParseException;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import com.pdg.ticket.service.Domain;
import com.pdg.ticket.service.ServiceClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {

	private EditText edUserName;
	private EditText edPassWord;
	private Button btLogin;
	private String userName;
	private String passWord;
	public static String idUser;
	public static String idDevice;
	public static String idCompany;

	private boolean status;
	ServiceClient client;
	private JSONObject jsonObj;
	private AlertDialog dialog;
	private static SharedPreferences prefsRememberCredentials;
	String url = "";
	private String androidId;
	private static final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		androidId = Secure.getString(this.getContentResolver(),
				Secure.ANDROID_ID);
		edUserName = (EditText) findViewById(R.id.editTextUsename);
		edPassWord = (EditText) findViewById(R.id.editTextPassword);
		btLogin = (Button) findViewById(R.id.btLogin);
		btLogin.setOnClickListener(this);
		client = new ServiceClient();
		dialog = new ProgressDialog(this);
		prefsRememberCredentials = getSharedPreferences("remember",
				MODE_PRIVATE);
		try {
			if (prefsRememberCredentials != null) {

				String s = prefsRememberCredentials.getString("lastLogged", "");
				if (!s.equals("")) {
					Calendar ca = Calendar.getInstance();
					ca.setTime(sdf.parse(s));
					if (getDifference(ca, Calendar.getInstance(),
							TimeUnit.SECONDS) < (24 * 60 * 60)) {
						Login.this.finish();
						Intent intent = new Intent(Login.this, HomeTicket.class);
						intent.putExtra("idUser", prefsRememberCredentials
								.getString("idUser", ""));
						startActivity(intent);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private class DownloadWebPageTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				String s = ServiceClient.loadManySerialized(url);
				Log.e("login response", s);
				if (s == null || s.equals("")) {
					System.out.println("Login fail");
				} else {
					System.out.println(s);
					jsonObj = new JSONObject(s);
					status = jsonObj.getBoolean("status");
					if (status) {
						idUser = jsonObj.getString("id");
						userName = jsonObj.getString("username");
						passWord = jsonObj.getString("password");
						idDevice = jsonObj.getString("device_id");
						idCompany = jsonObj.getString("company_id");
						System.out.println(idDevice + "  " + idCompany);
						idUser = jsonObj.getString("id");
						if (idDevice.equals("null") || idDevice.equals("")) {

							Intent intent = new Intent(Login.this,
									WarningLogindevice.class);
							intent.putExtra("androidId", androidId);
							startActivity(intent);
						} else {
							if (idDevice.equals(androidId)) {

								SaveLasLogin(userName, passWord, idUser);
								Intent intent = new Intent(Login.this,
										HomeTicket.class);
								intent.putExtra("idUser", idUser);
								startActivity(intent);
								Login.this.finish();
							} else {
								runOnUiThread(new Runnable() {

									@Override
									public void run() {
										Toast.makeText(
												Login.this,
												"The device you are trying to use is not currently set as your primary device",
												Toast.LENGTH_LONG).show();
										edPassWord.setText("");
									}
								});
							}
						}

					} else {
						final String message = jsonObj.getString("message");
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								Toast.makeText(getBaseContext(), message,
										Toast.LENGTH_LONG).show();
								edPassWord.setText("");
							}
						});

					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			dialog.setMessage("Loading...");
			dialog.show();
		}

		@Override
		protected void onPostExecute(Void result) {
			// checkLogin();
			if (dialog.isShowing())
				dialog.dismiss();
		}
	}

	private void checkLogin() {
		if (status) {
			this.finish();
			Intent intent = new Intent(this, HomeTicket.class);
			intent.putExtra("idUser", idUser);
			startActivity(intent);
		} else {
			Toast.makeText(getApplicationContext(), "Login Fail",
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onClick(View v) {
		if (v == btLogin) {

			if (edUserName.getText().length() > 0
					&& edPassWord.getText().length() > 0) {
				String usernam = edUserName.getText().toString();
				String password = edPassWord.getText().toString();
				url = String.format(Domain.LOGIN_URL, usernam, password);
				Log.e("login url", url);
				new DownloadWebPageTask().execute();

			} else {
				// Toast.makeText(getApplicationContext(),
				// status + "   " + idDevice, Toast.LENGTH_LONG).show();
				Toast.makeText(getBaseContext(), "Enter username and password",
						Toast.LENGTH_LONG).show();
			}

		}
	}

	public static void SaveLasLogin(String user, String pass, String idUser) {
		SharedPreferences.Editor editor = prefsRememberCredentials.edit();
		editor.putString("usernameLastLogged", user);
		editor.putString("passwordLastLogged", pass);
		editor.putString("idDevice", idDevice);
		editor.putString("idCompany", idCompany);
		editor.putString("idUser", idUser);
		editor.putString("lastLogged",
				sdf.format(Calendar.getInstance().getTime()));
		editor.commit();
	}

	public static long getDifference(Calendar a, Calendar b, TimeUnit units) {
		return units.convert(b.getTimeInMillis() - a.getTimeInMillis(),
				TimeUnit.MILLISECONDS);
	}

}
