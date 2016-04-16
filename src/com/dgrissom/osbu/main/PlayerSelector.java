package com.dgrissom.osbu.main;

import com.dgrissom.osbu.main.utilities.InventoryUtility;
import com.dgrissom.osbu.main.utilities.PlayerUtility;
import com.dgrissom.osbu.main.utilities.ScoreboardUtility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PlayerSelector implements Iterable<PlayerUtility>{
    // stored as UUIDs to avoid memory leaks. use build() to get a set of players
    private Set<UUID> players;

    public PlayerSelector() {
        this.players = new HashSet<>();
    }


    public PlayerSelector selectAll() {
        for (Player player : Bukkit.getOnlinePlayers())
            this.players.add(player.getUniqueId());
        return this;
    }
    // added for completeness (you could just do new PlayerSelector()
    public PlayerSelector deselectAll() {
        this.players.clear();
        return this;
    }


    public PlayerSelector selectExplicit(PlayerUtility... players) {
        for (PlayerUtility player : players)
            this.players.add(player.getUniqueId());
        return this;
    }


    public PlayerSelector selectExplicit(Collection<PlayerUtility> players) {
        for (PlayerUtility player : players)
            this.players.add(player.getUniqueId());
        return this;
    }


    public PlayerSelector deselectExplicit(PlayerUtility... players) {
        for (PlayerUtility player : players)
            this.players.remove(player.getUniqueId());
        return this;
    }
    public PlayerSelector deselectExplicit(Collection<PlayerUtility> players) {
        for (PlayerUtility player : players)
            this.players.remove(player.getUniqueId());
        return this;
    }


    /**
     * Selects players with any of the given permissions
     */
    public PlayerSelector selectPermissions(String... permissions) {
        for (Player player : Bukkit.getOnlinePlayers())
            for (String permission : permissions)
                if (player.hasPermission(permission))
                    this.players.add(player.getUniqueId());
        return this;
    }


    /**
     * Deselects players with any of the given permissions
     */
    public PlayerSelector deselectPermissions(String... permissions) {
        for (Player player : Bukkit.getOnlinePlayers())
            for (String permission : permissions)
                if (player.hasPermission(permission))
                    this.players.remove(player.getUniqueId());
        return this;
    }


    /**
     * Selects players with all of the given permissions
     */
    public PlayerSelector selectWithAllPermissions(String... permissions) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            boolean hasAll = true;
            for (String permission : permissions) {
                if (!player.hasPermission(permission)) {
                    hasAll = false;
                    break;
                }
            }
            if (hasAll)
                this.players.add(player.getUniqueId());
        }
        return this;
    }


    /**
     * Deselects players with all of the given permissions
     */
    public PlayerSelector deselectWithAllPermissions(String... permissions) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            boolean hasAll = true;
            for (String permission : permissions) {
                if (!player.hasPermission(permission)) {
                    hasAll = false;
                    break;
                }
            }
            if (hasAll)
                this.players.remove(player.getUniqueId());
        }
        return this;
    }






    /**
     * Only returns the ONLINE selected players!!! (PlayerUtility is always an online player)
     */
    public Set<PlayerUtility> build() {
        Set<PlayerUtility> players = new HashSet<>();
        for (UUID id : this.players) {
            PlayerUtility player = PlayerUtility.getPlayer(id);
            if (player != null)
                players.add(player);
        }
        return players;
    }










    /*
    "Getters"
     */
    public boolean containsAll(UUID... players) {
        for (UUID id : players) {
            boolean inSelection = false;
            for (UUID id2 : this.players) {
                if (id.equals(id2)) {
                    inSelection = true;
                    break;
                }
            }
            if (!inSelection)
                return false;
        }
        return true;
    }
    public boolean containsAll(PlayerUtility... players) {
        for (PlayerUtility player : players) {
            boolean inSelection = false;
            for (UUID id : this.players) {
                if (player.getUniqueId().equals(id)) {
                    inSelection = true;
                    break;
                }
            }
            if (!inSelection)
                return false;
        }
        return true;
    }
    public boolean containsAny(UUID... players) {
        for (UUID id : players)
            for (UUID id2 : this.players)
                if (id.equals(id2))
                    return true;
        return false;
    }
    public boolean containsAny(PlayerUtility... players) {
        for (PlayerUtility player : players)
            for (UUID id : this.players)
                if (player.getUniqueId().equals(id))
                    return true;
        return false;
    }









    /*
    "Shortcut" Actions
    You could just loop through this selector object and do these, but these methods make it easier to read the code
     */




    public PlayerSelector setHealth(double health) {
        for (PlayerUtility player : build())
            player.setHealth(health);
        return this;
    }
    public PlayerSelector setHealthPercentage(double healthPercentage) {
        for (PlayerUtility player : build())
            player.setHealthPercentage(healthPercentage);
        return this;
    }
    public PlayerSelector setMaxHealth(double maxHealth) {
        for (PlayerUtility player : build())
            player.setMaxHealth(maxHealth);
        return this;
    }
    public PlayerSelector setFoodLevel(int foodLevel) {
        for (PlayerUtility player : build())
            player.setFoodLevel(foodLevel);
        return this;
    }
    public PlayerSelector setFoodLevelPercentage(int foodLevelPercentage) {
        for (PlayerUtility player : build())
            player.setFoodLevelPercentage(foodLevelPercentage);
        return this;
    }

    public PlayerSelector sendFormattedMessage(String msg) {
        for (PlayerUtility player : build())
            player.sendFormattedMessage(msg);
        return this;
    }
    public PlayerSelector setScoreboard(ScoreboardUtility scoreboard) {
        for (PlayerUtility player : build())
            player.setScoreboard(scoreboard);
        return this;
    }
    public PlayerSelector removeScoreboards() {
        for (PlayerUtility player : build())
            player.removeScoreboard();
        return this;
    }
    public PlayerSelector updateInventories() {
        for (PlayerUtility player : build())
            player.updateInventory();
        return this;
    }
    public PlayerSelector openInventory(InventoryUtility inventory) {
        for (PlayerUtility player : build())
            player.openInventory(inventory);
        return this;
    }
    public PlayerSelector clearInventories() {
        for (PlayerUtility player : build())
            player.getInventory().clear();
        return this;
    }
    public PlayerSelector giveItems(ItemStack... items) {
        for (PlayerUtility player : build())
            player.getInventory().add(items);
        return this;
    }
    public PlayerSelector takeItems(ItemStack... items) {
        for (PlayerUtility player : build())
            player.getInventory().remove(items);
        return this;
    }







    @Override
    public Iterator<PlayerUtility> iterator() {
        return build().iterator();
    }
}
