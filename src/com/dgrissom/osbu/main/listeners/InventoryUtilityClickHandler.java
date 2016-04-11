package com.dgrissom.osbu.main.listeners;

import com.dgrissom.osbu.main.events.InventoryClickSlotEvent;

@FunctionalInterface
public interface InventoryUtilityClickHandler {
    void onClick(InventoryClickSlotEvent evt);
}
