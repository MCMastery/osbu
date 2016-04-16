package com.dgrissom.osbu.main.listeners;

import com.dgrissom.osbu.main.events.InventoryExitEvent;

@FunctionalInterface
public interface InventoryUtilityCloseHandler {
    void onClose(InventoryExitEvent evt);
}
