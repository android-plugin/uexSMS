package org.zywx.wbpalmstar.plugin.uexsms;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import org.zywx.wbpalmstar.base.ResoureFinder;
import org.zywx.wbpalmstar.engine.EBrowserView;
import org.zywx.wbpalmstar.engine.universalex.EUExBase;

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

    public void send(String[] params) {
        open(params);
    }

//    private void registerSmsReceiver() {
//        if (receiver == null) {
//            receiver = new SmsStateReceiver();
//            IntentFilter filter = new IntentFilter(ACTION_SEND_SMS);
//            mContext.registerReceiver(receiver, filter);
//        }
//    }

    /**
     * 执行发送短信的操作
     * 
     */
//    private void performSendSmsAction(String phoneNum, String content) {
//        try {
//            SmsManager smsMa = SmsManager.getDefault();
//            Intent intent = new Intent(ACTION_SEND_SMS);
//            PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            ArrayList<String> messages = smsMa.divideMessage(content);
//            for (String msg : messages) {
//                smsMa.sendTextMessage(phoneNum, null, msg, pi, null);
//            }
//        } catch (IllegalArgumentException e) {
//            errorCallback(0, EUExCallback.F_ERROR_CODE_SMS_SEND_ARGUMENTS_ERROR, finder.getString("error_parameter"));
//        } catch (SecurityException e) {
//            Toast.makeText(mContext, finder.getString("no_permisson_declare"), Toast.LENGTH_SHORT).show();
//        }
//    }

//    public class SmsStateReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            if (action.equals(ACTION_SEND_SMS)) {
//                if (getResultCode() == Activity.RESULT_OK) {
//                    jsCallback(F_CALLBACK_NAME_SMS_SEND, 0, EUExCallback.F_C_INT, EUExCallback.F_C_SUCCESS);
//                } else {
//                    jsCallback(F_CALLBACK_NAME_SMS_SEND, 0, EUExCallback.F_C_INT, EUExCallback.F_C_FAILED);
//                }
//            }
//        }
//    }

    @Override
    protected boolean clean() {
//        if (receiver != null) {
//            mContext.unregisterReceiver(receiver);
//            receiver = null;
//        }
        return true;
    }
}
