package com.dgrissom.osbu.main.utilities;

import com.dgrissom.osbu.main.UpdatedText;
import com.dgrissom.osbu.main.OSBU;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardUtility extends OSBUUtility {
    public static final int MAX_DISPLAY_NAME = 16;

    public enum Criteria {
        DUMMY("dummy"),
        TRIGGER("trigger"),
        DEATH_COUNT("deathCount"),
        PLAYER_KILL_COUNT("playerKillCount"),
        TOTAL_KILL_COUNT("totalKillCount"),
        HEALTH("health"),
        XP("xp"),
        LEVEL("level"),
        FOOD("food"),
        AIR("air");

        private String string;

        Criteria(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return this.string;
        }
    }

    private final Scoreboard scoreboard;

    public ScoreboardUtility() {
        this(Bukkit.getScoreboardManager().getNewScoreboard());
    }
    public ScoreboardUtility(Object object) {
        super(object);
        this.scoreboard = (Scoreboard) object;
    }

    public ScoreboardUtility addObjective(String name, Criteria criteria, String displayName, DisplaySlot slot) {
        Objective objective = this.scoreboard.registerNewObjective(name, criteria.toString());
        objective.setDisplayName(new StringUtility(displayName).format().toString());
        objective.setDisplaySlot(slot);
        return this;
    }
    public ScoreboardUtility addObjective(String name, Criteria criteria, UpdatedText displayName, DisplaySlot slot) {
        Objective objective = this.scoreboard.registerNewObjective(name, criteria.toString());
        objective.setDisplaySlot(slot);

        long delay = Math.round(20 / displayName.getFramesPerSecond());
        // so we can access it from inner class
        int[] taskID = new int[1];
        taskID[0] = Bukkit.getScheduler().runTaskTimer(OSBU.getInstance(), () -> {
            if (this.scoreboard.getObjective(slot) == null) {
                Bukkit.getScheduler().cancelTask(taskID[0]);
                return;
            }
            objective.setDisplayName(new StringUtility(displayName.getCurrentTextFrame()).format().toString());
            displayName.update();
        }, 0, delay).getTaskId();
        return this;
    }
    public ScoreboardUtility removeObjective(DisplaySlot slot) {
        // just to be safe, do both of these
        this.scoreboard.getObjective(slot).unregister();
        this.scoreboard.clearSlot(slot);
        return this;
    }

    public ScoreboardUtility addRow(String text, int value, DisplaySlot slot) {
        Objective objective = this.scoreboard.getObjective(slot);
        objective.getScore(new StringUtility(text).format().toString()).setScore(value);
        return this;
    }
    public ScoreboardUtility addRow(UpdatedText text, int value, DisplaySlot slot) {
        Objective objective = this.scoreboard.getObjective(slot);
        objective.getScore(new StringUtility(text.getCurrentTextFrame()).format().toString()).setScore(value);

        long delay = Math.round(20.0 / text.getFramesPerSecond());
        // so we can access it from inner class
        int[] taskID = new int[1];
        taskID[0] = Bukkit.getScheduler().runTaskTimer(OSBU.getInstance(), () -> {
            if (this.scoreboard.getObjective(slot) == null) {
                Bukkit.getScheduler().cancelTask(taskID[0]);
                return;
            }
            removeRow(text.getCurrentTextFrame());
            objective.getScore(new StringUtility(text.update()).format().toString()).setScore(value);
        }, delay, delay).getTaskId();
        return this;
    }
    public ScoreboardUtility removeRow(String text) {
        this.scoreboard.resetScores(new StringUtility(text).format().toString());
        return this;
    }

    @Override
    public Scoreboard getObject() {
        return this.scoreboard;
    }
}
