package com.dgrissom.osbu.main.utilities;

import org.bukkit.entity.Player;

public class PlayerUtility extends OSBUUtility {
    private Player player;

    public PlayerUtility(Object object) {
        super(object);
        this.player = (Player) object;
    }

    public PlayerUtility sendFormattedMessage(String s) {
        this.player.sendMessage(new StringUtility(s).format().toString());
        return this;
    }

    @Override
    public Player getObject() {
        return this.player;
    }
}
