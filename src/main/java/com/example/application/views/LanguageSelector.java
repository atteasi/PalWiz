package com.example.application.views;

import com.vaadin.flow.component.select.Select;

import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

public class LanguageSelector extends Select<String> {

    private final Map<String, Locale> languageMap;

    public LanguageSelector() {
        // setLabel("Language");
        languageMap = new HashMap<>();
        languageMap.put("fi", new Locale("fi", "FI"));
        languageMap.put("en", new Locale("en", "EN"));
        

        setItems(languageMap.keySet());
        addValueChangeListener(event -> updateLanguage(event.getValue()));
        setValue(getLanguageName(TranslationUtils.getCurrentLocale()));
    }

    private String getLanguageName(Locale locale) {
        return languageMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(locale))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("en"); // Default to "English" if the locale is not found in the map
    }

    private void updateLanguage(String language) {
        Locale newLocale = languageMap.getOrDefault(language, new Locale("en", "EN"));
        TranslationUtils.setCurrentLocale(newLocale);

        // Trigger a UI refresh to apply the new language
        getUI().ifPresent(ui -> ui.getPage().reload());
    }

}
