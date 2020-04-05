package me.Lucas.EvilSlaughters.staff.staffchat.commands;

import me.Lucas.EvilSlaughters.Main;
import me.Lucas.EvilSlaughters.managers.SubCommand;
import me.Lucas.EvilSlaughters.utils.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StaffChatCommand extends SubCommand {

    private Main plugin;

    public static Set<String> staffChatToggle = new HashSet<>();
    public static Set<String> staffChatHidden = new HashSet<>();

    private List<String> subCommands = new ArrayList<>();

    public StaffChatCommand(Main plugin) {
        this.plugin = plugin;

        subCommands.add("toggle");
        subCommands.add("hide");
        subCommands.add("show");
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("evilslaughters.staff.chat")) {
            if (args.length == 1) {
                p.sendMessage(Utils.prefix + Utils.chat("Voer een geldig subcommando in!"));
                return;
            }

            if (args[1].equalsIgnoreCase("toggle")) {
                if (!(staffChatToggle.contains(p.getName()))) {
                    staffChatToggle.add(p.getName());
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt staffchat aangezet, al je berichten komen nu in de StaffChat!"));
                } else {
                    staffChatToggle.remove(p.getName());
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt staffchat uitgezet, je berichten komen nu niet meer in de StaffChat!"));
                }
                return;
            }

            if (args[1].equalsIgnoreCase("hide")) {
                if (!(staffChatHidden.contains(p.getName()))) {
                    staffChatHidden.add(p.getName());
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt de staffchat verborgen, je zal nu geen berichten meer in de staffchat zien!"));
                }
            }

            if (args[1].equalsIgnoreCase("show")) {
                if (staffChatHidden.contains(p.getName())) {
                    staffChatHidden.remove(p.getName());
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt de staffchat weer zichtbaar gemaakt, je zal nu weer berichten in de staffchat zien!"));
                }
            }
        } else {
            p.sendMessage(Utils.noPermission);
        }
    }

    @Override
    public String name() {
        return plugin.cmdMngr.staffchat;
    }

    @Override
    public String info() {
        return null;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

    @Override
    public List<String> getArguments(Player p, String[] args) {
        if (args.length == 2) {
            List<String> completionList = new ArrayList<>();
            if (!args[1].equals("")) {
                for (String s : subCommands) {
                    if (s.startsWith(args[1].toLowerCase())) {
                        completionList.add(s);
                    }
                }
                return completionList;
            }
            return new ArrayList<>(subCommands);
        }
        return null;
    }
}
