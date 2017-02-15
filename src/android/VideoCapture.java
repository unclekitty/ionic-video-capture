package ionic.video.capture;

import android.Manifest;
import android.content.Intent;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * This class echoes a string called from JavaScript.
 */
public class VideoCapture extends CordovaPlugin {

    private CallbackContext context;
    private String path;
    private static final int REQ_CODE = 110;
    private static final int RES_CODE = 111;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("videoCapture")) {
            this.context = callbackContext;
            String message = args.getString(0);
            videoCapture(message, callbackContext);
            return true;
        }
        return true;
    }

    private void videoCapture(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {

            String [] permissions = { Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            cordova.requestPermissions(this, 0, permissions);
            //callbackContext.success(message);

        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions,
                                          int[] grantResults) throws JSONException
    {
        for(int r:grantResults)
        {
            if(r == PackageManager.PERMISSION_DENIED)
            {
                return;
            }
        }

        Intent intent = new Intent(cordova.getActivity(), RecordVideoActivity.class);
        Log.i("123", cordova.getActivity().toString());
        cordova.startActivityForResult((CordovaPlugin) this, intent, REQ_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQ_CODE && resultCode == RES_CODE) {
            path = intent.getExtras().getString("path", "");
            Log.i("path", path);
            context.success(path);
        }
    }
}
