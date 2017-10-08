package mjandroiddev.superfastdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.widget.ProgressBar;

/**
 * Created by manoj.rawal on 08-Oct-17.
 */

class ProgressDialog {

    Dialog dialog;

    private ProgressDialog(Context context) {
        dialog = new AlertDialog.Builder(context)
                .setTitle(R.string.please_wait)
                .setView(new ProgressBar(context))
                .setCancelable(false)
                .create();
    }

    public static ProgressDialog getInstance(Context context) {
        return new ProgressDialog(context);
    }

    public ProgressDialog show() {
        dialog.show();
        return this;
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

    public void dismiss() {
        dialog.dismiss();
    }
}
