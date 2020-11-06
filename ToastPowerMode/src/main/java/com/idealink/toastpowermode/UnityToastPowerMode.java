package com.idealink.toastpowermode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.unity3d.player.UnityPlayer;

import static com.unity3d.player.UnityPlayer.currentActivity;

public class UnityToastPowerMode {
    private static UnityToastPowerMode m_instance;
    private Context context;

    public static UnityToastPowerMode instance(){
        if(m_instance == null){
            m_instance = new UnityToastPowerMode();
        }
        return m_instance;
    }

    private void setContext(Context context){
        this.context = context;
    }

    private void ShowToast(String toaststr){
        Toast.makeText(this.context, toaststr, Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void PowerSavingModeCheck(String objName, String objMethod){
        PowerManager powerManager = (PowerManager)currentActivity.getSystemService(Context.POWER_SERVICE);
        if(powerManager.isPowerSaveMode()) {
            //Toast.makeText(this.context, "Power saving mode", Toast.LENGTH_LONG).show();
            UnityPlayer.UnitySendMessage(objName, objMethod, "true");
        }
        else {
            //Toast.makeText(this.context, "Normal mode", Toast.LENGTH_LONG).show();
            UnityPlayer.UnitySendMessage(objName, objMethod, "false");
        }
    }
}
