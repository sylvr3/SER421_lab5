
package com.example.user.lab5;


import android.webkit.JavascriptInterface;

/**
 * Created by Sylvia on 11/23/2014.
 */
public class JSInterface {
    private GameActivity game;
    public JSInterface(GameActivity game){
        this.game = game;
    }
    @JavascriptInterface
    public void newGame(){game.newGame();}

}
