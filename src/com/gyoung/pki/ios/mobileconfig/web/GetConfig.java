package com.gyoung.pki.ios.mobileconfig.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * Servlet implementation class GetConfig
 */
public class GetConfig extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetConfig() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        Security.addProvider(new BouncyCastleProvider());

        MobileConfig mc = new MobileConfig();

        final String CommonName = request.getParameter("CN");
        final String ECID = request.getParameter("ECID");
        final String OU = request.getParameter("OU");
        final String challenge = request.getParameter("challenge");

        //test server challenge:
        //Serial# GB020MQHA90
        //ChipID# 4083005940494
        //Challenge "76D3469DF3FB00E4E989EA718F89C529"

        String xmlString = mc.MobileConfig("IOS" + OU + CommonName + ECID + ".corp.acme.local", OU, challenge);

        response.setContentType("application/mobileconfig");

        ServletOutputStream stream = null;

        stream = response.getOutputStream();

        //set response headers
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-apple-aspen-config");

        response.addHeader("Content-Disposition", "attachment; filename=ACMEPKI.mobileconfig");
        response.setContentLength((int) xmlString.length());


        // String FS = System.getProperty("file.separator");
        //	String ctx = getServletContext().getRealPath("") + FS;
        //	URL p12file = new URL("file", "", ctx + "gyoung.p12");

        //		String psswd = "Password1!";

        /* Open the keystore */
        //KeyStore keystore = null;

        //keystore = KeyStore.getInstance("PKCS12", "BC");
        //keystore.load(p12file.openStream(), psswd.toCharArray());


        //		 byte[] signedConfig = null;
        //		try {
        //			signedConfig = SignConfig.Go(xmlString, keystore);
        //		} catch (Exception e) {
        //			// TODO Auto-generated catch block
        //			e.printStackTrace();
        //		}


        stream.write(xmlString.getBytes());

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
