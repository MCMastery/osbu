package com.dgrissom.osbu.main.listeners;

import com.dgrissom.osbu.main.events.InventoryClickSlotEvent;
import com.dgrissom.osbu.main.utilities.InventoryUtility;
import com.dgrissom.osbu.main.utilities.PlayerUtility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OSBUInventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent evt) {
        // so we can make a PlayerUtility class. anyway, how could the entity who clicked NOT be a player?
        if (!(evt.getWhoClicked() instanceof Player))
            return;
        PlayerUtility player = new PlayerUtility(evt.getWhoClicked());
        InventoryUtility inventory = InventoryUtility.fromViewerId(evt.getWhoClicked().getUniqueId());
        if (inventory != null) {
            // implement the cancel method
            InventoryClickSlotEvent clickSlotEvent = new InventoryClickSlotEvent(player, inventory, evt.getRawSlot(), evt.getClick()) {
                @Override
                public void setCancelled(boolean isCancelled) {
                    super.setCancelled(isCancelled);
                    evt.setCancelled(true);
                    player.updateInventory();
                }
            };
            inventory.triggerClickHandlers(clickSlotEvent);
            Bukkit.getPluginManager().callEvent(clickSlotEvent);
        }
    }
}
