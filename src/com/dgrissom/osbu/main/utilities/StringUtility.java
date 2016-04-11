package com.dgrissom.osbu.main.utilities;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StringUtility extends OSBUUtility implements Iterable<Character> {
    private final String string;

    public StringUtility(Object object) {
        super(object);
        this.string = (String) object;
    }

    public StringUtility format() {
        return new StringUtility(ChatColor.translateAlternateColorCodes('&', this.string));
    }
    public StringUtility capitalize() {
        return new StringUtility(Character.toUpperCase(this.string.charAt(0)) + this.string.substring(1).toLowerCase());
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
    @Override
    public Iterator<Character> iterator() {
        List<Character> chars = new ArrayList<>();
        for (char c : this.string.toCharArray())
            chars.add(c);
        return chars.iterator();
    }
}
