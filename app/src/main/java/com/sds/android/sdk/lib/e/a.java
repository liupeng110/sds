package com.sds.android.sdk.lib.e;

import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Looper;
import com.sds.android.sdk.lib.util.g;

/* TaskScheduler */
public class a {

    /* TaskScheduler */
    public static abstract class a<Input, Output> extends AsyncTask<Input, Object, Output> {
        private Input mInput;
        private Object mObject;

        protected abstract Output onDoInBackground(Input input);

        protected abstract void onPostExecuteForeground(Output output);

        public a(Input input) {
            this.mInput = input;
        }

        protected final Output doInBackground(Input... inputArr) {
            try {
                return onDoInBackground(inputArr[0]);
            } catch (SQLiteException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected final void onPostExecute(Output output) {
            onPostExecuteForeground(output);
            releaseData();
        }

        protected void onCancelled() {
            super.onCancelled();
            releaseData();
        }

        private void releaseData() {
            com.sds.android.sdk.lib.util.a.b(this.mObject);
            this.mObject = null;
            if (!(this.mInput instanceof Number) && !(this.mInput instanceof Boolean) && !(this.mInput instanceof Character) && !(this.mInput instanceof Enum)) {
                this.mInput = null;
            }
        }

        private void execute() {
            execute(new Object[]{this.mInput});
        }

        private void executeAtUI(Object obj) {
            this.mObject = obj;
            com.sds.android.sdk.lib.util.a.a(obj);
            execute(new Object[]{this.mInput});
        }
    }

    public static void a(Runnable runnable) {
        a(null, runnable, null);
    }

    public static void a(Runnable runnable, Runnable runnable2) {
        a(null, runnable, runnable2);
    }

    public static void a(Object obj, Runnable runnable) {
        a(obj, runnable, null);
    }

    public static void a(final Object obj, final Runnable runnable, final Runnable runnable2) {
        if (runnable == null) {
            if (runnable2 != null) {
                runnable2.run();
            }
        } else if (Looper.myLooper() != Looper.getMainLooper()) {
            runnable.run();
            if (runnable2 != null) {
                runnable2.run();
            }
        } else {
            com.sds.android.sdk.lib.util.a.a(obj);
            new AsyncTask<Object, Object, Object>() {
                protected Object doInBackground(Object... objArr) {
                    runnable.run();
                    return null;
                }

                protected void onPostExecute(Object obj) {
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                    com.sds.android.sdk.lib.util.a.b(obj);
                }

                protected void onCancelled() {
                    g.a("TaskScheduler", "AsyncTask onCancelled class=%s", runnable.getClass().getName());
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                    com.sds.android.sdk.lib.util.a.b(obj);
                }
            }.execute(new Object[0]);
        }
    }

    public static void a(Object obj, a aVar) {
        aVar.executeAtUI(obj);
    }

    public static void a(a aVar) {
        aVar.execute();
    }
}
