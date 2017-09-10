package com.tiny.rush;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import org.apache.commons.lang3.StringUtils;

/********************************************************
 * File Name : SeedApp.java
 * Author : ManhNV
 * Date : 2017-09-06
 * Description: 
 * Last-modified by : ManhNV
 * Last-modified : 2017-09-06
 ********************************************************/


public class SeedAppSDK {
    private static final String URL_PROPERTY = "com.seedapp.sdk.url";
    private static final String LOG_BODY_PROPERTY = "com.seedapp.sdk.log";
    public static final String BODY = "BODY";
    public static final String HEADER = "HEADER";
    public static final String NONE = "NONE";
    private static Context applicationContext;
    private static boolean initialized;
    private static String url;
    private static String logLevel;

    public static void initialize(Context applicationContext) {
        if (initialized) {
            return;
        }

        Validate.notNull(applicationContext, "applicationContext");

        Validate.hasInternetPermissions(applicationContext, false);

        SeedAppSDK.applicationContext = applicationContext;

        SeedAppSDK.loadDefaultsFromMetadata(SeedAppSDK.applicationContext);


        if (StringUtils.isEmpty(url)) {
            throw new SeedAppException("A valid url must be set in the " +
                    "AndroidManifest.xml " +
                    "before initializing the sdk.");
        }

        SeedAppSDK.initialized = true;
    }

    private static void loadDefaultsFromMetadata(Context context) {
        if (context == null) {
            return;
        }

        ApplicationInfo ai = null;
        try {
            ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return;
        }

        if (ai == null || ai.metaData == null) {
            return;
        }

        if (url == null) {
            Object tempUrl = ai.metaData.get(URL_PROPERTY);
            if (tempUrl instanceof String) {
                String urlString = (String) tempUrl;
                if (urlString.contains("http://")) {
                    url = urlString;
                } else {
                    throw new SeedAppException("Url = %s is wrong format.", urlString);
                }
            } else {
                throw new SeedAppException(
                        "URL cannot be directly placed in the manifest." +
                                "They must be placed in the string resource file.");
            }
        }

        if (logLevel == null) {
            logLevel = ai.metaData.getString(LOG_BODY_PROPERTY);
        }
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static void setInitialized(boolean initialized) {
        SeedAppSDK.initialized = initialized;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        SeedAppSDK.url = url;
    }

    public static String getLogLevel() {
        return logLevel;
    }

    public static void setLogLevel(String logLevel) {
        SeedAppSDK.logLevel = logLevel;
    }
}
