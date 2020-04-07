package me.Lucas.EvilSlaughters.empirewand.commands;

import me.Lucas.EvilSlaughters.Main;
import me.Lucas.EvilSlaughters.empirewand.listener.FlyCloud;
import me.Lucas.EvilSlaughters.managers.SubCommand;
import me.Lucas.EvilSlaughters.utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FlyCloudCommand extends SubCommand {

    private Main plugin;
    private List<String> cloudTypes = new ArrayList<>();

    public FlyCloudCommand(Main plugin) {
        this.plugin = plugin;
        cloudTypes.add("crimson");
        cloudTypes.add("warped");
        cloudTypes.add("ender");
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("evilslaughters.flycloud")) {
            if (args.length == 1) {
                p.sendMessage(Utils.prefix + Utils.chat("Je moet een cloudsoort invullen!"));
                p.sendMessage(Utils.chat("&7Je kan kiezen uit: &cCrimson,&7 &3Warped&7 of &5Ender&7!"));
                return;
            }

            if (args[1].equalsIgnoreCase("crimson")) {
                if (!FlyCloud.crimsonCloud.contains(p.getName())) {
                    p.setAllowFlight(true);
                    FlyCloud.crimsonCloud.add(p.getName());
                    FlyCloud.crimsonFlyCloud(p, plugin);
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt de Crimson Cloud aangezet!"));
                } else {
                    if (!p.getGameMode().equals(GameMode.CREATIVE)) {
                        p.setFlying(false);
                        p.setAllowFlight(false);
                    }

                    FlyCloud.crimsonCloud.remove(p.getName());
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt de Crimson Cloud uitgezet!"));
                }
            }

            if (args[1].equalsIgnoreCase("warped")) {
                if (!FlyCloud.warpedCloud.contains(p.getName())) {
                    p.setAllowFlight(true);
                    FlyCloud.warpedCloud.add(p.getName());
                    FlyCloud.warpedFlyCloud(p, plugin);
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt de Crimson Cloud aangezet!"));
                } else {
                    if (!p.getGameMode().equals(GameMode.CREATIVE)) {
                        p.setFlying(false);
                        p.setAllowFlight(false);
                    }
                    FlyCloud.warpedCloud.remove(p.getName());
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt de Crimson Cloud uitgezet!"));
                }
            }

            if (args[1].equalsIgnoreCase("ender")) {
                if (!FlyCloud.enderCloud.contains(p.getName())) {
                    p.setAllowFlight(true);
                    FlyCloud.enderCloud.add(p.getName());
                    FlyCloud.enderFlyCloud(p, plugin);
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt de Crimson Cloud aangezet!"));
                } else {
                    if (!p.getGameMode().equals(GameMode.CREATIVE)) {
                        p.setFlying(false);
                        p.setAllowFlight(false);
                    }
                    FlyCloud.enderCloud.remove(p.getName());
                    p.sendMessage(Utils.prefix + Utils.chat("Je hebt de Crimson Cloud uitgezet!"));
                }
            }
        }
    }

    @Override
    public String name() {
        return plugin.cmdMngr.flycloud;
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
                for (String s : cloudTypes) {
                    if (s.startsWith(args[1].toLowerCase())) {
                        completionList.add(s);
                    }
                }
                return completionList;
            }
            return new ArrayList<>(cloudTypes);
        }
        return null;
    }
}
