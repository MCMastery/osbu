package com.dgrissom.osbu.main;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Title {
    private String text;
    private @Nullable ChatColor color;
    private Set<ChatColor> formatting;
    // seconds
    private double fadeIn, displayTime, fadeOut;

    public Title() {
        this.text = "";
        this.color = null;
        this.formatting = new HashSet<>();
        this.fadeIn = this.fadeOut = 0;
        this.displayTime = 5;
    }

    public String getText() {
        return this.text;
    }
    public Title setText(String text) {
        this.text = text;
        return this;
    }
    public ChatColor getColor() {
        return this.color;
    }
    public Title setColor(@Nullable ChatColor color) {
        this.color = color;
        return this;
    }
    public Set<ChatColor> getFormatting() {
        return this.formatting;
    }
    public Title setFormatting(Set<ChatColor> formatting) {
        this.formatting = formatting;
        return this;
    }
    public Title setFormatting(ChatColor... formatting) {
        this.formatting = new HashSet<>(Arrays.asList(formatting));
        return this;
    }
    public Title addFormat(ChatColor format) {
        this.formatting.add(format);
        return this;
    }
    public Title removeFormat(ChatColor format) {
        this.formatting.remove(format);
        return this;
    }
    public Title removeFormatting() {
        this.formatting.clear();
        return this;
    }
    public double getFadeIn() {
        return this.fadeIn;
    }
    public Title setFadeIn(double seconds) {
        this.fadeIn = seconds;
        return this;
    }
    public int getFadeInTicks() {
        return (int) Math.round(this.fadeIn * 20);
    }
    public double getDisplayTime() {
        return this.displayTime;
    }
    public Title setDisplayTime(double seconds) {
        this.displayTime = seconds;
        return this;
    }
    public int getDisplayTimeTicks() {
        return (int) Math.round(this.displayTime * 20);
    }
    public double getFadeOut() {
        return this.fadeOut;
    }
    public Title setFadeOut(double seconds) {
        this.fadeOut = seconds;
        return this;
    }
    public int getFadeOutTicks() {
        return (int) Math.round(this.fadeOut * 20);
    }
}
