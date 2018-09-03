package com.gyoung.pki.ios.mobileconfig.web;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SignConfig {

    /**
     * @param args
     * @throws Exception
     * @throws MessagingException
     * @throws GeneralSecurityException
     */

    @SuppressWarnings("deprecation")
    public static byte[] Go(String profile, KeyStore keystore) throws Exception {

        Security.addProvider(new BouncyCastleProvider());

        // this is how we do this in OpenSSL!
        // openssl smime \
        // -sign \
        // -signer your-cert.pem \
        // -inkey your-priv-key.pem \
        // -certfile TheCertChain.pem \
        // -nodetach \
        // -outform der \
        // -in ConfigProfile.mobileconfig \
        // -out ConfigProfile_signed.mobileconfig]
        //
        String psswd = "Password1!";


        Certificate[] chain = keystore.getCertificateChain("Signing Key");

        /* Get the private key to sign the message with */
        PrivateKey privateKey = (PrivateKey) keystore.getKey("Signing Key", psswd.toCharArray());

        if (privateKey == null) {
            throw new Exception("cannot find private key for alias: Signing Key");
        }

        CMSSignedDataGenerator generator = new CMSSignedDataGenerator();
        generator.addSigner(privateKey, (X509Certificate) chain[0],
                CMSSignedDataGenerator.DIGEST_SHA1);

        ArrayList<Certificate> list = new ArrayList<Certificate>();

        for (int i = 0, length = chain == null ? 0 : chain.length; i < length; i++) {
            list.add(chain[i]);
        }

        CertStore sTore0 = CertStore.getInstance("Collection", new CollectionCertStoreParameters(list), "BC");


        generator.addCertificatesAndCRLs(sTore0);

        CMSProcessable content = new CMSProcessableByteArray(profile.getBytes());

        CMSSignedData signedData = generator.generate(content, true, "BC");

        return signedData.getEncoded();

    }

}
