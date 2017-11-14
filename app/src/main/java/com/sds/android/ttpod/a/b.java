package com.sds.android.ttpod.a;

import java.io.IOException;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* MySSLSocketFactory */
public class b extends SSLSocketFactory {
    private SSLContext a = SSLContext.getInstance("TLS");

    public b(KeyStore keyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        super(keyStore);
        AnonymousClass1 anonymousClass1 = new X509TrustManager(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }
        };
        this.a.init(null, new TrustManager[]{anonymousClass1}, null);
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return this.a.getSocketFactory().createSocket(socket, str, i, z);
    }

    public Socket createSocket() throws IOException {
        return this.a.getSocketFactory().createSocket();
    }
}
