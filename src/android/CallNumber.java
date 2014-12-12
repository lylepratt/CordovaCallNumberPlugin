package com.monmouth.callnumber;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class CallNumber extends CordovaPlugin
{
	@Override
    	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        String number = args.getString(0);
        number = "tel:"+number;
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.KITKAT) {
                // only for gingerbread and newer versions
            	Log.d("CallNumber", "New Android Version");
            	intent.setPackage("com.android.server.telecom");
            }
            else {
            	intent.setPackage("com.android.phone");
            }
            
            intent.setData(Uri.parse(number));
            cordova.getActivity().startActivity(intent);
            callbackContext.success();
        }
        catch (Exception e) {
            callbackContext.error(e.getMessage());
        }
        return true;
    }
}
