package de.adrian.customStatus.Utility;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TranslationManager {
    private final JavaPlugin plugin;
    private final Map<String, YamlConfiguration> translations = new HashMap<>();
    private String defaultLanguage = "en";

    public TranslationManager(JavaPlugin plugin) {
        this.plugin = plugin;
        saveDefaultLanguageFiles();
        loadLanguages();
    }

    private void loadLanguages() {
        File langFolder = new File(plugin.getDataFolder(), "lang");
        if (!langFolder.exists()) {
            langFolder.mkdirs();
        }

        // First load external files (user modifications)
        for (File file : Objects.requireNonNull(langFolder.listFiles())) {
            if (file.getName().endsWith(".yml")) {
                String langCode = file.getName().replace(".yml", "");
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                translations.put(langCode, config);
                plugin.getLogger().info("Loaded external language file: " + langCode);
            }
        }

        // Then load built-in defaults ONLY for missing languages
        loadBuiltinTranslation("en");
        loadBuiltinTranslation("de");
        loadBuiltinTranslation("cu");

        // Verify we have at least the default language
        if (!translations.containsKey(defaultLanguage)) {
            plugin.getLogger().severe("Failed to load any language files!");
        }
    }

    private void loadBuiltinTranslation(String langCode) {
        // Only load if we don't already have this language
        if (translations.containsKey(langCode)) return;

        try {
            String resourcePath = "lang/" + langCode + ".yml";
            InputStream stream = plugin.getResource(resourcePath);
            if (stream != null) {
                YamlConfiguration config = YamlConfiguration.loadConfiguration(
                        new InputStreamReader(stream, StandardCharsets.UTF_8)
                );
                translations.put(langCode, config);
                plugin.getLogger().info("Loaded built-in language: " + langCode);

                // Save a copy to the external folder for future editing
                File externalFile = new File(plugin.getDataFolder(), "lang/" + langCode + ".yml");
                if (!externalFile.exists()) {
                    config.save(externalFile);
                }
            }
        } catch (Exception e) {
            plugin.getLogger().warning("Failed to load built-in language " + langCode + ": " + e.getMessage());
        }
    }

    /**
     * Get a translated message
     * @param key The translation key
     * @param language The language code (e.g. "en", "de")
     * @return The translated message with color codes formatted
     */
    public String getTranslation(String key, String language) {
        YamlConfiguration langConfig = translations.getOrDefault(language, translations.get(defaultLanguage));
        if (langConfig == null) {
            return "Translation system error";
        }

        String message = langConfig.getString(key);
        if (message == null) {
            plugin.getLogger().warning("Missing translation for key: " + key + " in language: " + language);
            return ChatColor.RED + "[Missing translation: " + key + "]";
        }

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Get a translated message with placeholders
     * @param key The translation key
     * @param language The language code
     * @param args The arguments to replace placeholders ({0}, {1}, etc.)
     * @return The formatted and color-coded message
     */
    public String getTranslation(String key, String language, Object... args) {
        String message = getTranslation(key, language);
        return String.format(message, args);
    }

    /**
     * Check if a language is available
     * @param language The language code to check
     * @return True if the language is loaded
     */
    public boolean hasLanguage(String language) {
        return translations.containsKey(language);
    }


    private void saveDefaultLanguageFiles() {
        String[] languages = {"en", "de"};
        File langFolder = new File(plugin.getDataFolder(), "lang");

        for (String lang : languages) {
            File langFile = new File(langFolder, lang + ".yml");
            if (!langFile.exists()) {
                plugin.saveResource("lang/" + lang + ".yml", false);
                plugin.getLogger().info("Saved default language file: " + lang + ".yml");
            }
        }
    }
}