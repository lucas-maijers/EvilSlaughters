package me.Lucas.EvilSlaughters.kingdom.commands;

import me.Lucas.EvilSlaughters.Main;
import me.Lucas.EvilSlaughters.managers.SubCommand;
import me.Lucas.EvilSlaughters.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class KingdomCommand extends SubCommand {

    private Main plugin;
    private List<String> subCommands = new ArrayList<>();

    public KingdomCommand(Main plugin) {
        this.plugin = plugin;
        
        subCommands.add("create");
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("evilslaughters.kingdom")) {
            if (args.length == 1) {
                p.sendMessage(Utils.prefix + Utils.chat("Je moet een geldig subcommando invullen!"));
                return;
            }

            if (args[1].equalsIgnoreCase("create")) {

                if (args.length == 2) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je moet de naam van het kingdom nog invullen!"));
                    return;
                }

                if (args.length == 3) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je moet de prefix van het kingdom nog invullen!"));
                    return;
                }

                String kdnaam = args[2];
                String kdprefix = args[3];

                p.sendMessage(Utils.prefix + Utils.chat("Bezig met het aanmaken van het Kingdom, een ogenblik geduld!"));
                createKingdom(kdnaam, kdprefix);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        p.sendMessage(Utils.prefix + Utils.chat("Het kingdom is succesvol aangemaakt!"));
                    }
                }.runTaskLater(plugin, 20 * 2L);
            }
        }
    }

    @Override
    public String name() {
        return plugin.cmdMngr.kingdom;
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

    private void createKingdom(String name, String prefix) {
        // Normal Version
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), String.format("lp creategroup %s", name));
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), String.format("lp group %s meta addprefix 100 \"&8{%s&8}&r \"", name, prefix));
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), String.format("lp group %s parent add kdperms", name));
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), String.format("lp group %s permission set essentials.warps.%s", name, name));
        // King Version
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), String.format("lp creategroup K%s", name));
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), String.format("lp group K%s meta addprefix 100 \"&8{%s&8} &8[&6&lK&8]&r \"", name, prefix));
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), String.format("lp group K%s parent add kdperms", name));
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), String.format("lp group K%s parent add %s", name, name));
    }
}
