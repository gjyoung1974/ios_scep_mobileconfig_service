package com.gyoung.pki.ios.mobileconfig.web;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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

public class SignConfig2 {

    /**
     * @param args
     * @throws Exception
     * @throws MessagingException
     * @throws GeneralSecurityException
     */

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {

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

        MobileConfig mc = new MobileConfig();
        String profile = mc.MobileConfig("IOS-PKI-GB020MQHA904083005940494.ent.wfb.bank.dev", "PKI", "76D3469DF3FB00E4E989EA718F89C529");

        String psswd = "Password1!";

        /* Open the keystore */
        KeyStore keystore = KeyStore.getInstance("PKCS12", "BC");
        keystore.load(new FileInputStream("./gyoung.p12"), psswd.toCharArray());
        Certificate[] chain = keystore.getCertificateChain("Signing Key");

        /* Get the private key to sign the message with */
        PrivateKey privateKey = (PrivateKey) keystore.getKey("Signing Key",
                psswd.toCharArray());

        if (privateKey == null) {
            throw new Exception("cannot find private key for alias: " + args[2]);
        }

        CMSSignedDataGenerator generator = new CMSSignedDataGenerator();
        generator.addSigner(privateKey, (X509Certificate) chain[0],
                CMSSignedDataGenerator.DIGEST_SHA1);

        ArrayList<Certificate> list = new ArrayList<Certificate>();

        for (int i = 0, length = chain == null ? 0 : chain.length; i < length; i++) {
            list.add(chain[i]);
        }

        CertStore sTore0 = CertStore.getInstance("Collection",
                new CollectionCertStoreParameters(list), "BC");
        // sTore0.

        generator.addCertificatesAndCRLs(sTore0);

        CMSProcessable content = new CMSProcessableByteArray(profile.getBytes());

        CMSSignedData signedData = generator.generate(content, true, "BC");

        FileOutputStream fos = new FileOutputStream("./sscep.mobileconfig");
        fos.write(signedData.getEncoded());
        fos.close();

    }

}
