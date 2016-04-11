package com.dgrissom.osbu.main.utilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
    public PlayerUtility sendFormattedMessage(String s) {
        this.player.sendMessage(new StringUtility(s).format().toString());
        return this;
    }
    public void setScoreboard(ScoreboardUtility scoreboard) {
        this.player.setScoreboard(scoreboard.getObject());
    }
    public void removeScoreboard() {
        this.player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    @Override
    public Player getObject() {
        return this.player;
    }
}
