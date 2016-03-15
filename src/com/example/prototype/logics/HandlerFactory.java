package com.example.prototype.logics;

/**
 * Created by Patrick on 15.03.2016.
 */
public class HandlerFactory {

    public static DataHandler getHandlerInstance(String type) {
        switch (type) {
            case "image":
                return new BitmapHandler();
            case "text":
                return new TextHandler();
            default:
                return new TextHandler();
        }
    }
}
