package de.adrian.customStatus.Listeners;

import de.adrian.customStatus.Utility.ItemGranter;
import de.adrian.customStatus.Utility.Logic;
import de.adrian.customStatus.Utility.TabListUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    private boolean isSpecialItem(ItemStack item) {
        if (item == null) return false;
        return item.isSimilar(ItemGranter.RemoveStatus()) ||
                item.isSimilar(ItemGranter.CreateStatus()) ||
                item.isSimilar(ItemGranter.DeleteStatus());
    }

    @EventHandler
    public void onInventoryPickupItem(InventoryPickupItemEvent event) {
        if (isSpecialItem(event.getItem().getItemStack())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryMoveItem(InventoryMoveItemEvent event) {
        if (isSpecialItem(event.getItem())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {


        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        Player p = (Player) event.getWhoClicked();


        if (event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT) {
            if (isSpecialItem(event.getCurrentItem())){
                event.setCancelled(true);
                p.updateInventory();
                return;
            }
            if (event.getView().getTitle().contains("Custom Status ADMIN Panel:")){
                event.setCancelled(true);
                p.updateInventory();
                return;
            }
            }

        if (event.getCurrentItem() != null) {
            if (event.getCurrentItem().isSimilar(ItemGranter.DeleteStatus())) {
                Logic.DeleteStatus(p);
                event.setCancelled(true);
                p.closeInventory();
                p.updateInventory();
            } else if (event.getCurrentItem().isSimilar(ItemGranter.RemoveStatus())) {
                Logic.RemoveStatus(p);
                event.setCancelled(true);
                p.closeInventory();
                p.updateInventory();
            } else if (event.getCurrentItem().isSimilar(ItemGranter.CreateStatus())) {
                Logic.CreateStatus(p);
                event.setCancelled(true);
                p.closeInventory();
                p.updateInventory();
            }

            return;
        }

        if (event.getView().getTitle().contains("Custom Status ADMIN Panel:")){
            if (event.getInventory().contains(ItemGranter.CreateStatus())|| event.getInventory().contains(ItemGranter.DeleteStatus()) || event.getInventory().contains(ItemGranter.RemoveStatus())){
                event.setCancelled(true);
                p.updateInventory();
            }
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        for (ItemStack item : event.getNewItems().values()) {
            if (isSpecialItem(item)) {
                event.setCancelled(true);
                ((Player) event.getWhoClicked()).updateInventory();
                return;
            }
        }
        if (event.getView().getTitle().contains("Custom Status ADMIN Panel:")){
            if (event.getInventory().contains(ItemGranter.CreateStatus())|| event.getInventory().contains(ItemGranter.DeleteStatus()) || event.getInventory().contains(ItemGranter.RemoveStatus())){
                event.setCancelled(true);
                ((Player) event.getWhoClicked()).updateInventory();
            }

        }
    }
}