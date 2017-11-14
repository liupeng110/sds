package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument.Page;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintAttributes.MediaSize;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentAdapter.WriteResultCallback;
import android.print.PrintDocumentInfo;
import android.print.PrintDocumentInfo.Builder;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PrintHelperKitkat {
    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    private static final String LOG_TAG = "PrintHelperKitkat";
    private static final int MAX_PRINT_SIZE = 3500;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    int mColorMode = 2;
    final Context mContext;
    int mScaleMode = 2;

    PrintHelperKitkat(Context context) {
        this.mContext = context;
    }

    public void setScaleMode(int i) {
        this.mScaleMode = i;
    }

    public int getScaleMode() {
        return this.mScaleMode;
    }

    public void setColorMode(int i) {
        this.mColorMode = i;
    }

    public int getColorMode() {
        return this.mColorMode;
    }

    public void printBitmap(final String str, final Bitmap bitmap) {
        if (bitmap != null) {
            final int i = this.mScaleMode;
            PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
            MediaSize mediaSize = MediaSize.UNKNOWN_PORTRAIT;
            if (bitmap.getWidth() > bitmap.getHeight()) {
                mediaSize = MediaSize.UNKNOWN_LANDSCAPE;
            }
            printManager.print(str, new PrintDocumentAdapter() {
                private PrintAttributes mAttributes;

                public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, LayoutResultCallback layoutResultCallback, Bundle bundle) {
                    boolean z = true;
                    this.mAttributes = printAttributes2;
                    PrintDocumentInfo build = new Builder(str).setContentType(1).setPageCount(1).build();
                    if (printAttributes2.equals(printAttributes)) {
                        z = false;
                    }
                    layoutResultCallback.onLayoutFinished(build, z);
                }

                public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, WriteResultCallback writeResultCallback) {
                    PrintedPdfDocument printedPdfDocument = new PrintedPdfDocument(PrintHelperKitkat.this.mContext, this.mAttributes);
                    try {
                        Page startPage = printedPdfDocument.startPage(1);
                        RectF rectF = new RectF(startPage.getInfo().getContentRect());
                        Matrix matrix = new Matrix();
                        float width = rectF.width() / ((float) bitmap.getWidth());
                        if (i == 2) {
                            width = Math.max(width, rectF.height() / ((float) bitmap.getHeight()));
                        } else {
                            width = Math.min(width, rectF.height() / ((float) bitmap.getHeight()));
                        }
                        matrix.postScale(width, width);
                        matrix.postTranslate((rectF.width() - (((float) bitmap.getWidth()) * width)) / 2.0f, (rectF.height() - (width * ((float) bitmap.getHeight()))) / 2.0f);
                        startPage.getCanvas().drawBitmap(bitmap, matrix, null);
                        printedPdfDocument.finishPage(startPage);
                        printedPdfDocument.writeTo(new FileOutputStream(parcelFileDescriptor.getFileDescriptor()));
                        writeResultCallback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                    } catch (Throwable e) {
                        Log.e(PrintHelperKitkat.LOG_TAG, "Error writing printed content", e);
                        writeResultCallback.onWriteFailed(null);
                    } catch (Throwable th) {
                        if (printedPdfDocument != null) {
                            printedPdfDocument.close();
                        }
                        if (parcelFileDescriptor != null) {
                            try {
                                parcelFileDescriptor.close();
                            } catch (IOException e2) {
                            }
                        }
                    }
                    if (printedPdfDocument != null) {
                        printedPdfDocument.close();
                    }
                    if (parcelFileDescriptor != null) {
                        try {
                            parcelFileDescriptor.close();
                        } catch (IOException e3) {
                        }
                    }
                }
            }, new PrintAttributes.Builder().setMediaSize(mediaSize).setColorMode(this.mColorMode).build());
        }
    }

    public void printBitmap(String str, Uri uri) throws FileNotFoundException {
        printBitmap(str, loadConstrainedBitmap(uri, MAX_PRINT_SIZE));
    }

    private Bitmap loadConstrainedBitmap(Uri uri, int i) throws FileNotFoundException {
        if (i <= 0 || uri == null || this.mContext == null) {
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        loadBitmap(uri, options);
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        if (i2 <= 0 || i3 <= 0) {
            return null;
        }
        int max = Math.max(i2, i3);
        int i4 = 1;
        while (max > i) {
            max >>>= 1;
            i4 <<= 1;
        }
        if (i4 <= 0 || Math.min(i2, i3) / i4 <= 0) {
            return null;
        }
        Options options2 = new Options();
        options2.inMutable = true;
        options2.inSampleSize = i4;
        return loadBitmap(uri, options2);
    }

    private Bitmap loadBitmap(Uri uri, Options options) throws FileNotFoundException {
        InputStream inputStream = null;
        if (uri == null || this.mContext == null) {
            throw new IllegalArgumentException("bad argument to loadBitmap");
        }
        try {
            inputStream = this.mContext.getContentResolver().openInputStream(uri);
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e) {
                    Log.w(LOG_TAG, "close fail ", e);
                }
            }
            return decodeStream;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e2) {
                    Log.w(LOG_TAG, "close fail ", e2);
                }
            }
        }
    }
}
