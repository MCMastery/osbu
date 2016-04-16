package com.dgrissom.osbu.main;

import com.dgrissom.osbu.main.utilities.PlayerUtility;
import com.dgrissom.osbu.main.utilities.StringUtility;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Book implements Iterable<List<String>> {
    private List<List<String>> content;

    public Book() {
        this.content = new ArrayList<>();
    }

    public List<List<String>> getContent() {
        return this.content;
    }
    public int getPages() {
        return this.content.size();
    }
    public Book addPage(String... page) {
        return addPage(new ArrayList<>(Arrays.asList(page)));
    }
    public Book addPage(List<String> page) {
        this.content.add(page);
        return this;
    }
    public Book removePage(int page) {
        this.content.remove(page);
        return this;
    }


    public Book append(int page, String... lines) {
        return append(page, new ArrayList<>(Arrays.asList(lines)));
    }
    public Book append(int page, List<String> lines) {
        this.content.get(page).addAll(lines);
        return this;
    }

    public Book append(String... lines) {
        return append(new ArrayList<>(Arrays.asList(lines)));
    }
    public Book append(List<String> lines) {
        if (this.content.size() == 0)
            addPage();
        return append(this.content.size() - 1, lines);
    }

    public Book insert(int page, int line, String... lines) {
        return insert(page, line, new ArrayList<>(Arrays.asList(lines)));
    }
    public Book insert(int page, int line, List<String> lines) {
        this.content.get(page).addAll(line, lines);
        return this;
    }




    public Book generatePages(int pageSize, String... lines) {
        return generatePages(pageSize, new ArrayList<>(Arrays.asList(lines)));
    }
    public Book generatePages(int pageSize, List<String> lines) {
        List<List<String>> pages = new ArrayList<>();
        List<String> currentPage = new ArrayList<>();
        for (int i = 0, pageLine = 0; i < lines.size(); i++, pageLine++) {
            if (pageLine >= pageSize) {
                pages.add(currentPage);
                currentPage = new ArrayList<>();
                pageLine = 0;
            }
            currentPage.add(lines.get(i));
        }
        if (currentPage.size() > 0)
            pages.add(currentPage);
        this.content.addAll(pages);
        return this;
    }
    public Book generatePages(int pageSize) {
        List<String> lines = new ArrayList<>();
        for (List<String> page : this.content)
            lines.addAll(page);
        this.content = new ArrayList<>();
        return generatePages(pageSize, lines);
    }

    public Book messagePage(PlayerUtility player, int page) {
        for (String line : this.content.get(page))
            player.sendFormattedMessage(line);
        return this;
    }
    public Book messagePage(CommandSender sender, int page) {
        for (String line : this.content.get(page))
            sender.sendMessage(new StringUtility(line).format().toString());
        return this;
    }

    @Override
    public Iterator<List<String>> iterator() {
        return this.content.iterator();
    }
}
