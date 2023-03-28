package com.example.application.views;

import java.util.Locale;
import java.util.ResourceBundle;

public class TranslationUtils {

    
    private static Locale currentLocale = Locale.getDefault(); // You can set the default locale or use the system's default locale.

    public static ResourceBundle getMessages() {
        return ResourceBundle.getBundle("messages", currentLocale);
    }

    public static void setCurrentLocale(Locale locale) {
        currentLocale = locale;
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }
}
