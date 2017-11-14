package com.sds.android.ttpod.activities.musiccircle.shake;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/* Shaker */
public class c implements SensorEventListener {
    private Sensor a;
    private SensorManager b;
    private float[] c;
    private a d;
    private long e;
    private int f = 1;

    /* Shaker */
    public interface a {
        void onShake();
    }

    public c(Context context, a aVar) {
        this.d = aVar;
        this.b = (SensorManager) context.getSystemService("sensor");
        this.a = this.b.getDefaultSensor(1);
    }

    public void a() {
        this.f = 0;
        this.b.registerListener(this, this.a, 1);
    }

    public void b() {
        this.f = 1;
        this.b.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1) {
            float[] fArr = sensorEvent.values;
            if (this.c == null) {
                this.c = new float[3];
                a(fArr);
                return;
            }
            float abs = Math.abs(fArr[0] - this.c[0]);
            float abs2 = Math.abs(fArr[1] - this.c[1]);
            float abs3 = Math.abs(fArr[2] - this.c[2]);
            a(fArr);
            if (this.f == 0 && System.currentTimeMillis() - this.e > 500 && a(abs, abs2, abs3) && this.d != null) {
                this.e = System.currentTimeMillis();
                this.d.onShake();
            }
        }
    }

    private void a(float[] fArr) {
        if (this.c != null) {
            this.c[0] = fArr[0];
            this.c[1] = fArr[1];
            this.c[2] = fArr[2];
        }
    }

    private boolean a(float f, float f2, float f3) {
        return f > 9.0f || f2 > 9.0f || f3 > 9.0f;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
