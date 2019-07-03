package com.google.unity.ads;

import android.app.Activity;
import android.util.Log;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;

public class Interstitial {
    private Activity activity;
    private UnityAdListener adListener;
    private InterstitialAd interstitial;
    private boolean isLoaded = false;

    public Interstitial(Activity activity, UnityAdListener adListener) {
        this.activity = activity;
        this.adListener = adListener;
    }

    public void create(final String adUnitId) {
        this.activity.runOnUiThread(new Runnable() {
            public void run() {
                Interstitial.this.interstitial = new InterstitialAd(Interstitial.this.activity);
                Interstitial.this.interstitial.setAdUnitId(adUnitId);
                Interstitial.this.interstitial.setAdListener(new AdListener() {
                    public void onAdLoaded() {
                        Interstitial.this.isLoaded = true;
                        Interstitial.this.adListener.onAdLoaded();
                    }

                    public void onAdFailedToLoad(int errorCode) {
                        Interstitial.this.adListener.onAdFailedToLoad(PluginUtils.getErrorReason(errorCode));
                    }

                    public void onAdOpened() {
                        Interstitial.this.adListener.onAdOpened();
                    }

                    public void onAdClosed() {
                        Interstitial.this.adListener.onAdClosed();
                    }

                    public void onAdLeftApplication() {
                        Interstitial.this.adListener.onAdLeftApplication();
                    }
                });
            }
        });
    }

    public void loadAd(final AdRequest request) {
        this.activity.runOnUiThread(new Runnable() {
            public void run() {
                Interstitial.this.interstitial.loadAd(request);
            }
        });
    }

    public boolean isLoaded() {
        return this.isLoaded;
    }

    public void show() {
        this.activity.runOnUiThread(new Runnable() {
            public void run() {
                if (Interstitial.this.interstitial.isLoaded()) {
                    Interstitial.this.isLoaded = false;
                    Interstitial.this.interstitial.show();
                    return;
                }
                Log.d(PluginUtils.LOGTAG, "Interstitial was not ready to be shown.");
            }
        });
    }

    public void setPlayStorePurchaseParams(final PlayStorePurchaseListener purchaseListener, final String publicKey) {
        this.activity.runOnUiThread(new Runnable() {
            public void run() {
                Interstitial.this.interstitial.setPlayStorePurchaseParams(purchaseListener, publicKey);
            }
        });
    }

    public void destroy() {
    }
}
