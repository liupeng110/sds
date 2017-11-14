package com.mradar.sdk.record;

import android.content.Context;
import android.os.AsyncTask;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* GetIpAddress */
public class d extends AsyncTask<Void, Void, Void> {
    private Context a;
    private String b = "";

    protected /* synthetic */ Object doInBackground(Object... objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Void) obj);
    }

    public d(Context context) {
        this.a = context;
    }

    protected Void a(Void... voidArr) {
        try {
            this.b = InetAddress.getByName(f.d).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            this.b = "";
        } catch (Exception e2) {
            this.b = "";
        }
        return null;
    }

    protected void a(Void voidR) {
        super.onPostExecute(voidR);
        c.a(this.a, this.b);
    }
}
