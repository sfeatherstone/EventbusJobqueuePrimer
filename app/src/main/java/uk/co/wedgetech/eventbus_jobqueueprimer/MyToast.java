package uk.co.wedgetech.eventbus_jobqueueprimer;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StringRes;
import android.widget.Toast;


class MyToast {

    void show(final Context context, final String message) {
        show(context, message, Toast.LENGTH_SHORT);
    }

    private void show(final Context context, final String message, final int toastLength) {
        Handler handler = new Handler(Looper.getMainLooper());

        handler.post(() -> showToast(context, message, toastLength));
    }

    private void showToast(Context context, String message, int toastLength) {
        Toast.makeText(context.getApplicationContext(), message, toastLength).show();
    }
}
