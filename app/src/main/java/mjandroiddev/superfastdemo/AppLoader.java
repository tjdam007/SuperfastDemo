package mjandroiddev.superfastdemo;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.stetho.Stetho;

/**
 * Created by manoj.rawal on 07-Oct-17.
 */

public class AppLoader extends Application {

    private static RequestQueue queue;

    public static RequestQueue getQueue() {
        return queue;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        queue = Volley.newRequestQueue(this);
    }

    public static void addRequest(Request request) {
        getQueue().add(request);
    }
}
