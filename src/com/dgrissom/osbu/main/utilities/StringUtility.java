package com.dgrissom.osbu.main.utilities;

import org.bukkit.ChatColor;

public class StringUtility extends OSBUUtility {
    private final String string;

    StringUtility(Object object) {
        super(object);
        this.string = (String) object;
    }

    public StringUtility format() {
        return new StringUtility(ChatColor.translateAlternateColorCodes('&', this.string));
    }
    public StringUtility capitalize() {
        return new StringUtility(Character.toUpperCase(this.string.charAt(0)) + this.string.substring(1));
    }

    public StringUtility toUpperCase() {
        return new StringUtility(this.string.toUpperCase());
    }
    public StringUtility toLowerCase() {
        return new StringUtility(this.string.toLowerCase());
    }
    public StringUtility substring(int begin) {
        return new StringUtility(this.string.substring(begin));
    }
    public StringUtility substring(int begin, int end) {
        return new StringUtility(this.string.substring(begin, end));
    }

    @Override
    public String getObject() {
        return this.string;
    }
    @Override
    public String toString() {
        return this.string;
    }
}
