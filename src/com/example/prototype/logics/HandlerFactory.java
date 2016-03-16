package com.example.prototype.logics;

/**
 * Simple class factory with static builder method to generate a specific DataHandler. The factory is currently very
 * simplistic and generates the instance according to the string passed to the builder method
 * <p/>
 * This may be extended in the future to scan the incoming data for content and then select and appropriate handler upon
 * detection with a degree of certainty
 * <p/>
 * Created by Patrick
 */
public class HandlerFactory {

    /**
     * Finds a handler instance appropriate to the type that is passed as a parameter
     *
     * @param type The type of handler to select
     * @return The DataHandler instance that is appropriate for the type of data
     */
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
