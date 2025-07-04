package de.adrian.customStatus.Utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import java.util.ArrayList;

public class ItemGranter {



    public static ItemStack CreateStatus(){
        ItemStack i = new ItemStack(Material.ANVIL);
        ItemMeta im = i.getItemMeta();

        im.setItemName(ChatColor.GOLD + "Create Status!");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GOLD + "Click to create a new status!");

        im.setLore(lore);
        im.setCustomModelData(1);


        i.setItemMeta(im);

        return i;
    }

    public static ItemStack RemoveStatus(){
        ItemStack i = new ItemStack(Material.BARRIER);
        ItemMeta im = i.getItemMeta();

        im.setItemName(ChatColor.GOLD + "Remove Status!");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GOLD + "Remove someones status!");

        im.setLore(lore);
        im.setCustomModelData(1);


        i.setItemMeta(im);

        return i;
    }

    public static ItemStack DeleteStatus(){
        ItemStack i = new ItemStack(Material.RED_DYE);
        ItemMeta im = i.getItemMeta();

        im.setItemName(ChatColor.GOLD + "Delete Status!");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GOLD + "Delete a already existing status!");

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
