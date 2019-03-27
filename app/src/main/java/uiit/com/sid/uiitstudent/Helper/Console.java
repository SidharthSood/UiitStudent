package uiit.com.sid.uiitstudent.Helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class Console {
    public static void showAlert(Context context, String title, String message, @Nullable String positiveButtonTitle, @Nullable String negativeButtonTitle, @Nullable DialogInterface.OnClickListener positiveActionListener, @Nullable DialogInterface.OnClickListener negativeActionListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveButtonTitle, positiveActionListener);
        builder.setNegativeButton(negativeButtonTitle, negativeActionListener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showAlert(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
