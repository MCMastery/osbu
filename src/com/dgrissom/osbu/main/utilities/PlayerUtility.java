package com.dgrissom.osbu.main.utilities;

import com.dgrissom.osbu.main.OSBUCommand;
import com.dgrissom.osbu.main.Title;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import java.util.HashSet;
import java.util.Set;
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

    private void setupTitleTimings(Title title) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + getName() + " times " + title.getFadeInTicks() + " " + title.getDisplayTimeTicks() + " " + title.getFadeOutTicks());
    }
    //todo colors and formatting
    public @Deprecated PlayerUtility sendTitle(Title title) {
        setupTitleTimings(title);
        //todo JSONBuilder
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + getName() + " title " + "[{\"text\":\"" + title.getText() + "\"}]");
        return this;
    }
    //todo colors and formatting
    public @Deprecated PlayerUtility sendSubtitle(Title subtitle) {
        setupTitleTimings(subtitle);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + getName() + " subtitle " + "[{\"text\":\"" + subtitle.getText() + "\"}]");
        return this;
    }

    public boolean hasPermission(String permission) {
        return this.player.hasPermission(permission);
    }
    public boolean hasPermission(OSBUCommand command) {
        return !command.hasPermission() || hasPermission(command.getPermission());
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
    public PlayerUtility closeInventory() {
        this.player.closeInventory();
        return this;
    }
    public InventoryView getOpenedInventory() {
        return this.player.getOpenInventory();
    }


    public Block getTargetBlock(int maxDistance) {
        return this.player.getTargetBlock(new HashSet<Material>(), maxDistance);
    }

    @Override
    public Player getObject() {
        return this.player;
    }






    public static PlayerUtility getPlayer(UUID id) {
        for (Player player : Bukkit.getOnlinePlayers())
            if (player.getUniqueId().equals(id))
                return new PlayerUtility(player);
        return null;
    }
    public static PlayerUtility getPlayer(String name) {
        for (Player player : Bukkit.getOnlinePlayers())
            if (player.getName().equals(name))
                return new PlayerUtility(player);
        return null;
    }
    public static PlayerUtility getPlayerIgnoreCase(String name) {
        for (Player player : Bukkit.getOnlinePlayers())
            if (player.getName().equalsIgnoreCase(name))
                return new PlayerUtility(player);
        return null;
    }
    public static Set<PlayerUtility> getOnlinePlayers() {
        Set<PlayerUtility> players = new HashSet<>();
        for (Player player : Bukkit.getOnlinePlayers())
            players.add(new PlayerUtility(player));
        return players;
    }
}
