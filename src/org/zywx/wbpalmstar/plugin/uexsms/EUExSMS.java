package org.zywx.wbpalmstar.plugin.uexsms;

import java.util.ArrayList;
import org.zywx.wbpalmstar.base.ResoureFinder;
import org.zywx.wbpalmstar.engine.EBrowserView;
import org.zywx.wbpalmstar.engine.universalex.EUExBase;
import org.zywx.wbpalmstar.engine.universalex.EUExCallback;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.telephony.SmsManager;
import android.widget.Toast;

public class EUExSMS extends EUExBase {

	public static final String TAG = "uexSMS";
	public static final String ACTION_SEND_SMS = "org.zywx.bbcs.palmstar.Send.SMS";
	public static final String F_CALLBACK_NAME_SMS_SEND = "uexSMS.cbSend";

	private ResoureFinder finder;

	public EUExSMS(Context context, EBrowserView inParent) {
		super(context, inParent);
		finder = ResoureFinder.getInstance(context);
	}

	/**
	 * 打开短信发送界面
	 */
	public void open(String[] params) {
		if (params.length < 2) {
			return;
		}
		final String inPhoneNum = params[0].replace(",", ";");
		final String inContent = params[1];
		((Activity) mContext).runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Uri uri = Uri.parse("smsto:" + inPhoneNum);
				Intent intent = new Intent(android.content.Intent.ACTION_SENDTO, uri);
				intent.putExtra("sms_body", inContent);
				try {
					mContext.startActivity(intent);
				} catch (ActivityNotFoundException e) {
					Toast.makeText(mContext, finder.getString("can_not_find_suitable_app_perform_this_operation"),
							Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	/**
	 * 直接发送短信
	 * 
	 * @param inPhoneNum
	 *            接收人电话号码
	 * @param inContent
	 *            短信内容
	 */
//	public void send(String[] params) {
//		if (params.length < 2) {
//			return;
//		}
//		final String inPhoneNum = params[0];
//		final String inContent = params[1];
//		((Activity) mContext).runOnUiThread(new Runnable() {
//
//			@Override
//			public void run() {
//
//				new AlertDialog.Builder(mContext).setIcon(android.R.drawable.ic_dialog_alert)
//						.setTitle(finder.getString("prompt"))
//						.setMessage(finder.getString("plugin_sms_are_you_sure_to_send_sms"))
//						.setPositiveButton(finder.getString("confirm"), new OnClickListener() {
//
//							@Override
//							public void onClick(DialogInterface dialog, int which) {
//								registerSmsReceiver();
//								String[] phoneNumbers = inPhoneNum.split(",");
//								if (phoneNumbers == null) {
//									phoneNumbers = new String[] { inPhoneNum };
//								}
//								for (String phoneNumber : phoneNumbers) {
//									performSendSmsAction(phoneNumber, inContent);
//								}
//							}
//						}).setNegativeButton(finder.getString("cancel"), null).setCancelable(false).show();
//			}
//		});
//	}

//	private void registerSmsReceiver() {
//		if (receiver == null) {
//			receiver = new SmsStateReceiver();
//			IntentFilter filter = new IntentFilter(ACTION_SEND_SMS);
//			mContext.registerReceiver(receiver, filter);
//		}
//	}

	/**
	 * 执行发送短信的操作
	 * 
	 */
//	private void performSendSmsAction(String phoneNum, String content) {
//		try {
//			SmsManager smsMa = SmsManager.getDefault();
//			Intent intent = new Intent(ACTION_SEND_SMS);
//			PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//			ArrayList<String> messages = smsMa.divideMessage(content);
//			for (String msg : messages) {
//				smsMa.sendTextMessage(phoneNum, null, msg, pi, null);
//			}
//		} catch (IllegalArgumentException e) {
//			errorCallback(0, EUExCallback.F_ERROR_CODE_SMS_SEND_ARGUMENTS_ERROR, finder.getString("error_parameter"));
//		} catch (SecurityException e) {
//			Toast.makeText(mContext, finder.getString("no_permisson_declare"), Toast.LENGTH_SHORT).show();
//		}
//	}

//	public class SmsStateReceiver extends BroadcastReceiver {
//
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			String action = intent.getAction();
//			if (action.equals(ACTION_SEND_SMS)) {
//				if (getResultCode() == Activity.RESULT_OK) {
//					jsCallback(F_CALLBACK_NAME_SMS_SEND, 0, EUExCallback.F_C_INT, EUExCallback.F_C_SUCCESS);
//				} else {
//					jsCallback(F_CALLBACK_NAME_SMS_SEND, 0, EUExCallback.F_C_INT, EUExCallback.F_C_FAILED);
//				}
//			}
//		}
//	}

	@Override
	protected boolean clean() {
//		if (receiver != null) {
//			mContext.unregisterReceiver(receiver);
//			receiver = null;
//		}
		return true;
	}
}
