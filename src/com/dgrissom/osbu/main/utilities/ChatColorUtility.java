package com.dgrissom.osbu.main.utilities;

import org.bukkit.ChatColor;

public class ChatColorUtility extends OSBUUtility {
    private final ChatColor chatColor;

    public ChatColorUtility(Object object) {
        super(object);
        this.chatColor = (ChatColor) object;
    }

    public boolean isColor() {
        return Character.isDigit(this.chatColor.getChar());
    }
    public boolean isFormat() {
        return !Character.isDigit(this.chatColor.getChar());
    }

    @Override
    public ChatColor getObject() {
        return this.chatColor;
    }
}
