package com.leeddev.digitalspeedometerapp;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class OverSpeedDialogClass {


    public void showDialog(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_box_speed_alert);
        TextView dialogButton = (TextView) dialog.findViewById(R.id.btn_cancel);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}



//    }
//
//
//    @Override
//    public void onClick(View view) {
//
//    }
//}

