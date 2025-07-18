package de.adrian.customStatus;

import de.adrian.customStatus.Utility.Importer;
import de.adrian.customStatus.Utility.SafeManager;
import de.adrian.customStatus.Utility.TranslationManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class CustomStatus extends JavaPlugin {

    public static HashMap<UUID, String> prefix = new HashMap<>();
    public static HashMap<String, String> prefixs = new HashMap<>();
    public static HashMap<UUID, String> creating = new HashMap<>();
    public static HashMap<UUID, String> prefixname = new HashMap<>();
    public static Boolean use;
    public static Boolean joinMessageNormal;

    private static CustomStatus instance;
    private static TranslationManager translationManager;
    private String language;

    @Override
    public void onEnable() {
        instance = this;

        // Load configuration
        saveDefaultConfig();
        reloadConfig();

        // Initialize translation system
        translationManager = new TranslationManager(this);

        // Load settings
        joinMessageNormal = getConfig().getBoolean("useNormalJoinMessage", true);
        use = getConfig().getBoolean("usePlayerNamePrefix", true);
        language = getConfig().getString("language", "en");

        // Validate language setting
        if (!translationManager.hasLanguage(language)) {
            getLogger().warning("Configured language '" + language + "' not found, defaulting to 'en'");
            language = "en";
        }

        getLogger().info("CustomStatus plugin is being enabled...");
        getLogger().info("Using language: " + language);

        // Register commands and events
        Importer.ImportAll(Bukkit.getPluginManager(), this);

        // Load or initialize data
        if (!getConfig().contains("prefixs")) {
            SafeManager.SafeAll(getConfig(), this);
        } else {
            SafeManager.LoadAll(getConfig());
        }
    }

    @Override
    public void onDisable() {
        SafeManager.SafeAll(getConfig(), this);
    }

    public static CustomStatus getInstance() {
        return instance;
    }

    public static TranslationManager getTranslationManager() {
        return translationManager;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        if (translationManager.hasLanguage(language)) {
            this.language = language;
            getConfig().set("language", language);
            saveConfig();
        } else {
            getLogger().warning("Attempted to set invalid language: " + language);
        }
    }

}