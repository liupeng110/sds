package com.mradar.sdk.record;

import android.media.AudioRecord;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* Record */
public class k extends Thread {
    private boolean a;
    private boolean b;
    private long c;
    private long d;
    private a e;
    private File f;

    protected void a() {
        this.a = true;
    }

    protected void b() {
        this.b = true;
        this.e = null;
    }

    public void run() {
        int minBufferSize = AudioRecord.getMinBufferSize(8000, 16, 2);
        AudioRecord audioRecord = new AudioRecord(6, 8000, 16, 2, minBufferSize);
        if (audioRecord.getState() == 1) {
            FileOutputStream fileOutputStream = null;
            try {
                if (this.f != null) {
                    File parentFile = this.f.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(this.f);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                if (this.e != null) {
                    this.e.a(4005, "CREATE_FILE_ERROR");
                    return;
                }
            }
            try {
                byte[] bArr = new byte[minBufferSize];
                audioRecord.startRecording();
                while (!this.a && !this.b) {
                    int read = audioRecord.read(bArr, 0, bArr.length);
                    if (read > 0) {
                        Object obj = new byte[read];
                        System.arraycopy(bArr, 0, obj, 0, read);
                        double a = c.a(bArr, read);
                        if (this.e != null) {
                            this.e.a(a);
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.write(obj);
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                if (this.e != null) {
                                    this.e.a(4006, "WRITE_FILE_ERROR");
                                }
                                if (audioRecord != null && audioRecord.getState() == 1) {
                                    audioRecord.stop();
                                    audioRecord.release();
                                }
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                        return;
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                        return;
                                    }
                                }
                                return;
                            }
                        }
                    }
                    this.d += (long) read;
                    if (this.c != 0 && this.d >= this.c) {
                        a();
                    }
                }
                if (this.e != null) {
                    this.e.a();
                }
                if (audioRecord != null && audioRecord.getState() == 1) {
                    audioRecord.stop();
                    audioRecord.release();
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
            } catch (Exception e4) {
                e4.printStackTrace();
                if (audioRecord != null && audioRecord.getState() == 1) {
                    audioRecord.stop();
                    audioRecord.release();
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e322) {
                        e322.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                if (audioRecord != null && audioRecord.getState() == 1) {
                    audioRecord.stop();
                    audioRecord.release();
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3222) {
                        e3222.printStackTrace();
                    }
                }
            }
        } else if (this.e != null) {
            this.e.a(4004, "RECORD_INIT_FAIL");
        }
    }
}
