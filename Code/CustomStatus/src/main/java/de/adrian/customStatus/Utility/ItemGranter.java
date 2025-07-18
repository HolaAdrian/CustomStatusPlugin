package de.adrian.customStatus.Utility;

import de.adrian.customStatus.CustomStatus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemGranter {



    public static ItemStack CreateStatus() {
        String lang = CustomStatus.getInstance().getLanguage();
        TranslationManager tm = CustomStatus.getTranslationManager();

        ItemStack i = new ItemStack(Material.ANVIL);
        ItemMeta im = i.getItemMeta();

        im.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                tm.getTranslation("create_status_title", lang)));

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&',
                tm.getTranslation("create_status_button", lang)));

        im.setLore(lore);
        im.setCustomModelData(1);
        i.setItemMeta(im);

        return i;
    }

    public static ItemStack RemoveStatus() {
        String lang = CustomStatus.getInstance().getLanguage();
        TranslationManager tm = CustomStatus.getTranslationManager();

        ItemStack i = new ItemStack(Material.BARRIER);
        ItemMeta im = i.getItemMeta();

        im.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                tm.getTranslation("remove_status_title", lang)));

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&',
                tm.getTranslation("remove_status_button", lang)));

        im.setLore(lore);
        im.setCustomModelData(1);
        i.setItemMeta(im);

        return i;
    }

    public static ItemStack DeleteStatus() {
        String lang = CustomStatus.getInstance().getLanguage();
        TranslationManager tm = CustomStatus.getTranslationManager();

        ItemStack i = new ItemStack(Material.RED_DYE);
        ItemMeta im = i.getItemMeta();

        im.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                tm.getTranslation("delete_status_title", lang)));

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&',
                tm.getTranslation("delete_status_button", lang)));

        im.setLore(lore);
        im.setCustomModelData(1);
        i.setItemMeta(im);

        return i;
    }

    public static Inventory AdminInventory(){
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "" + ChatColor.BOLD + "Custom Status ADMIN Panel:");


        inv.setItem(11, RemoveStatus());
        inv.setItem(13, CreateStatus());
        inv.setItem(15, DeleteStatus());

        return inv;
    }



}
