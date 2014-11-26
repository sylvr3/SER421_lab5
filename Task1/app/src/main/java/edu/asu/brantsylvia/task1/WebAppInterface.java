package edu.asu.brantsylvia.task1;

import android.webkit.JavascriptInterface;

public class WebAppInterface {
    private GameActivity game;

    public WebAppInterface(GameActivity game) {
        this.game = game;
    }

    @JavascriptInterface
    public void overwriteHistory(final String text) {

        game.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                game.writeHistory(text);
            }
        });

    }
}
