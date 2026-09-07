package it.radio.tv;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private WebView webView;
    private AlertDialog exitDialog;
    private boolean reloadOnResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN |
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        );
        webView = new WebView(this);
        webView.setBackgroundColor(android.graphics.Color.BLACK);
        setContentView(webView);

        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setMediaPlaybackRequiresUserGesture(false);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccessFromFileURLs(true);
        s.setAllowUniversalAccessFromFileURLs(true);
        s.setCacheMode(WebSettings.LOAD_DEFAULT);

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        hideSystemUI();
        webView.loadUrl("file:///android_asset/player.html");
    }

    private void hideSystemUI() {
        webView.setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_FULLSCREEN |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) hideSystemUI();
    }

    @Override public void onBackPressed() {
        if (isFinishing() || (exitDialog != null && exitDialog.isShowing())) return;
        exitDialog = new AlertDialog.Builder(this)
            .setTitle("Radio Brianza TV")
            .setMessage("Vuoi uscire da Radio Brianza TV?")
            .setNegativeButton("Resta", (dialog, which) -> hideSystemUI())
            .setPositiveButton("Esci", (dialog, which) -> {
                webView.loadUrl("about:blank");
                finishAndRemoveTask();
            })
            .create();
        exitDialog.setOnDismissListener(dialog -> { exitDialog = null; });
        exitDialog.show();
        exitDialog.getButton(AlertDialog.BUTTON_NEGATIVE).requestFocus();
    }

    @Override protected void onResume() {
        super.onResume();
        webView.onResume();
        if (reloadOnResume) {
            reloadOnResume = false;
            webView.loadUrl("file:///android_asset/player.html");
        } else {
            webView.evaluateJavascript("(function(){var v=document.getElementById('video');if(v){var p=v.play();if(p&&p.catch)p.catch(function(){});}})()", null);
        }
    }
    @Override protected void onPause() {
        webView.evaluateJavascript("(function(){var v=document.getElementById('video');if(v)v.pause();})()", null);
        webView.onPause();
        super.onPause();
    }
    @Override protected void onStop() {
        if (exitDialog != null) exitDialog.dismiss();
        webView.loadUrl("about:blank");
        reloadOnResume = true;
        super.onStop();
    }
    @Override protected void onDestroy() {
        if (exitDialog != null) exitDialog.dismiss();
        webView.destroy();
        super.onDestroy();
    }
}

