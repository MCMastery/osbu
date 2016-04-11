package com.dgrissom.osbu.main.utilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import java.util.UUID;

public class PlayerUtility extends OSBUUtility {
    private Player player;

    public PlayerUtility(Object object) {
        super(object);
        this.player = (Player) object;
    }

    public double getHealth() {
        return this.player.getHealth();
    }
    public PlayerUtility setHealth(double health) {
        this.player.setHealth(health);
        return this;
    }
    public int getHealthInt() {
        return (int) Math.round(getHealth());
    }
    public double getHealthPercentage() {
        return getHealth() / getMaxHealth();
    }
    public PlayerUtility setHealthPercentage(double health) {
        this.player.setHealth(health * getMaxHealth());
        return this;
    }
    public double getMaxHealth() {
        return this.player.getMaxHealth();
    }
    public PlayerUtility setMaxHealth(double maxHealth) {
        this.player.setMaxHealth(maxHealth);
        return this;
    }

    public int getFoodLevel() {
        return this.player.getFoodLevel();
    }
    public PlayerUtility setFoodLevel(int foodLevel) {
        this.player.setFoodLevel(foodLevel);
        return this;
    }
    public double getFoodLevelPercentage() {
        return this.player.getFoodLevel() / 20.0;
    }
    public PlayerUtility setFoodLevelPercentage(double foodLevel) {
        this.player.setFoodLevel((int) Math.round(foodLevel * 20.0));
        return this;
    }

    public String getName() {
        return this.player.getName();
    }
    public String getDisplayName() {
        return this.player.getDisplayName();
    }
    public UUID getUniqueId() {
        return this.player.getUniqueId();
    }

    public PlayerUtility sendFormattedMessage(Object msg) {
        this.player.sendMessage(new StringUtility(String.valueOf(msg)).format().toString());
        return this;
    }

    public PlayerUtility setScoreboard(ScoreboardUtility scoreboard) {
        this.player.setScoreboard(scoreboard.getObject());
        return this;
    }
    public PlayerUtility removeScoreboard() {
        this.player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        return this;
    }

    public InventoryUtility getInventory() {
        return new InventoryUtility(this.player.getInventory());
    }
    public PlayerUtility updateInventory() {
        this.player.updateInventory();
        return this;
    }
    public PlayerUtility openInventory(InventoryUtility inventory) {
        this.player.openInventory(inventory.getObject());
        return this;
    }
    public InventoryView getOpenedInventory() {
        return this.player.getOpenInventory();
    }

    @Override
    public Player getObject() {
        return this.player;
    }
}
