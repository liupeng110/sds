package com.sds.android.ttpod.framework.modules.core.b.a;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import com.alibaba.wireless.security.SecExceptionCode;
import com.tencent.open.yyb.TitleBar;

/* ShakeManager */
public class a implements SensorEventListener {
    private float a = -12.0f;
    private float b = 12.0f;
    private float c = -12.0f;
    private float d = 12.0f;
    private float e = -12.0f;
    private float f = 12.0f;
    private float[] g;
    private float h = 0.0f;
    private float i = 0.0f;
    private float j = 0.0f;
    private boolean k = false;
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;
    private boolean o = false;
    private long p = 0;
    private long q = 0;
    private long r = 0;
    private SensorManager s;
    private a t;
    private int u;
    private int v;
    private int w;
    private Handler x = new Handler(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (this.a.t != null) {
                        if (this.a.h > 0.0f) {
                            this.a.t.a(1);
                        } else {
                            this.a.t.a(2);
                        }
                    }
                    this.a.k = false;
                    return;
                case 1:
                    if (this.a.t != null) {
                        if (this.a.i > 0.0f) {
                            this.a.t.b(1);
                        } else {
                            this.a.t.b(2);
                        }
                    }
                    this.a.l = false;
                    return;
                case 2:
                    if (this.a.t != null) {
                        if (this.a.j > 0.0f) {
                            this.a.t.c(1);
                        } else {
                            this.a.t.c(2);
                        }
                    }
                    this.a.m = false;
                    return;
                default:
                    return;
            }
        }
    };

    /* ShakeManager */
    public interface a {
        void a(int i);

        void b(int i);

        void c(int i);
    }

    public a(Context context, boolean z, int i) {
        this.s = (SensorManager) context.getSystemService("sensor");
        if (-2 == i) {
            this.u = 3;
            this.w = 1000;
            this.o = false;
        } else {
            this.u = i;
            this.w = SecExceptionCode.SEC_ERROR_PKG_VALID;
            this.o = z;
        }
        this.v = 1;
    }

    public void a(int i, int i2) {
        if (i <= 0 && i >= -600 && i2 >= 0 && i2 <= SecExceptionCode.SEC_ERROR_SIGNATRUE) {
            a(((float) (i - 30)) / TitleBar.SHAREBTN_RIGHT_MARGIN, ((float) (i2 + 30)) / TitleBar.SHAREBTN_RIGHT_MARGIN, 0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    private void a(float f, float f2, float f3, float f4, float f5, float f6) {
        if (0.0f != f) {
            this.a = f;
        }
        if (0.0f != f2) {
            this.b = f2;
        }
        if (0.0f != f3) {
            this.c = f3;
        }
        if (0.0f != f4) {
            this.d = f4;
        }
        if (0.0f != f5) {
            this.e = f5;
        }
        if (0.0f != f6) {
            this.f = f6;
        }
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.t = aVar;
            if (!this.n) {
                this.s.registerListener(this, this.s.getDefaultSensor(1), this.u);
                this.n = true;
            }
        }
    }

    public void a() {
        if (this.n) {
            this.s.unregisterListener(this);
            this.n = false;
        }
        this.t = null;
    }

    private float a(float f, float f2) {
        return Math.abs(f) >= Math.abs(f2) ? f : f2;
    }

    private void a(float f) {
        if (f < this.a || f > this.b) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.o) {
                if (this.k) {
                    this.h = a(this.h, f);
                } else if (currentTimeMillis - this.p > ((long) this.w)) {
                    this.k = true;
                    this.h = f;
                    this.x.sendEmptyMessageDelayed(0, 500);
                }
            } else if (currentTimeMillis - this.p > ((long) this.w) && this.t != null) {
                this.t.a(this.v);
            }
            this.p = currentTimeMillis;
        }
    }

    private void b(float f) {
        if (f < this.c || f > this.d) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.o) {
                if (this.l) {
                    this.i = a(this.i, f);
                } else if (currentTimeMillis - this.q > ((long) this.w)) {
                    this.l = true;
                    this.i = f;
                    this.x.sendEmptyMessageDelayed(1, 500);
                }
            } else if (currentTimeMillis - this.q > ((long) this.w) && this.t != null) {
                this.t.b(this.v);
            }
            this.q = currentTimeMillis;
        }
    }

    private void c(float f) {
        if (f < this.e || f > this.f) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.o) {
                if (this.m) {
                    this.j = a(this.j, f);
                } else if (currentTimeMillis - this.r > ((long) this.w)) {
                    this.m = true;
                    this.j = f;
                    this.x.sendEmptyMessageDelayed(2, 500);
                }
            } else if (currentTimeMillis - this.r > ((long) this.w) && this.t != null) {
                this.t.c(this.v);
            }
            this.r = currentTimeMillis;
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() != 1) {
            return;
        }
        if (this.g == null) {
            this.g = new float[3];
            this.g[0] = sensorEvent.values[0];
            this.g[1] = sensorEvent.values[1];
            this.g[2] = sensorEvent.values[2];
            return;
        }
        float[] fArr = sensorEvent.values;
        float f = fArr[0] - this.g[0];
        float f2 = fArr[1] - this.g[1];
        float f3 = fArr[2] - this.g[2];
        this.g[0] = sensorEvent.values[0];
        this.g[1] = sensorEvent.values[1];
        this.g[2] = sensorEvent.values[2];
        a(f);
        b(f2);
        c(f3);
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
