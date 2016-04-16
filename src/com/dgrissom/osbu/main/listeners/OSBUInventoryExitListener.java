package com.dgrissom.osbu.main.listeners;

import com.dgrissom.osbu.main.events.InventoryExitEvent;
import com.dgrissom.osbu.main.utilities.InventoryUtility;
import com.dgrissom.osbu.main.utilities.PlayerUtility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class OSBUInventoryExitListener implements Listener {
    @EventHandler
    public void onInventoryOpen(InventoryCloseEvent evt) {
        // so we can make a PlayerUtility class. anyway, how could the entity who opened NOT be a player?
        if (!(evt.getPlayer() instanceof Player))
            return;
        PlayerUtility player = new PlayerUtility(evt.getPlayer());
        InventoryUtility inventory = InventoryUtility.fromViewerId(player.getUniqueId());
        if (inventory != null) {
            if (inventory.autoUnegister())
                inventory.unregister();
            InventoryExitEvent closeEvent = new InventoryExitEvent(player, inventory);
            inventory.triggerCloseHandlers(closeEvent);
            Bukkit.getPluginManager().callEvent(closeEvent);
        }
    }
}
