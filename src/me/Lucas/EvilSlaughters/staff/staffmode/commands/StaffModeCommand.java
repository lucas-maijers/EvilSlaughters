package me.Lucas.EvilSlaughters.staff.staffmode.commands;

import me.Lucas.EvilSlaughters.Main;
import me.Lucas.EvilSlaughters.managers.SubCommand;
import me.Lucas.EvilSlaughters.staff.staffmode.inventory.StaffMode;
import me.Lucas.EvilSlaughters.staff.staffmode.listener.StaffModeEvents;
import me.Lucas.EvilSlaughters.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StaffModeCommand extends SubCommand {

    private Main plugin;

    private File cfgFile;
    private FileConfiguration cfg;

    public static Set<String> inStaffMode = new HashSet<>();
    public static Set<String> hasHiddenStaff = new HashSet<>();

    public StaffModeCommand(Main plugin) {
        this.plugin = plugin;

        cfgFile = new File(plugin.getDataFolder(), "staffmode.yml");
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("evilslaughters.staff.staffmode")) {
            if (plugin.cfgm.getStaffmodeCFG().isConfigurationSection("StaffMode." + p.getName())) {
                if (!inStaffMode.contains(p.getName())) {
                    p.sendMessage(Utils.prefix + Utils.chat("Je bent uit de StaffMode gegaan!"));
                    restoreInv(p);
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.showPlayer(plugin, p);
                    }
                    StaffModeEvents.vanishedPlayers.remove(p);
                    inStaffMode.remove(p.getName());
                    return;
                }
            }

            if (!inStaffMode.contains(p.getName())) {
                p.sendMessage(Utils.prefix + Utils.chat("Je bent in de StaffMode gegaan!"));
                storeInv(p);
                p.getInventory().setContents(StaffMode.staffModeInv(p).getContents());
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (!(all.hasPermission("evilslaughters.staff.staffmode"))) {
                        all.hidePlayer(plugin, p);
                    }
                    if (hasHiddenStaff.contains(all.getName())) {
                        all.hidePlayer(plugin, p);
                    }
                }

                if (!(p.getGameMode().equals(GameMode.CREATIVE))) {
                    p.setAllowFlight(true);
                    p.setHealth(20);
                    p.setFoodLevel(20);
                }
                StaffModeEvents.vanishedPlayers.add(p);
                inStaffMode.add(p.getName());
                return;
            }

            if (inStaffMode.contains(p.getName())) {
                p.sendMessage(Utils.prefix + Utils.chat("Je bent uit de StaffMode gegaan!"));
                restoreInv(p);
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.showPlayer(plugin, p);
                }
                if (!(p.getGameMode().equals(GameMode.CREATIVE))) {
                    p.setAllowFlight(false);
                    p.setFlying(false);
                }
                StaffModeEvents.vanishedPlayers.remove(p);
                inStaffMode.remove(p.getName());
            }
        }
    }

    @Override
    public String name() {
        return plugin.cmdMngr.staffmode;
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
        return null;
    }

    private void storeInv(Player p) {
        cfg = plugin.cfgm.getStaffmodeCFG();

        if (!(cfg.isConfigurationSection("StaffMode"))) {
            cfg.createSection("StaffMode");
        }

        ConfigurationSection cs = cfg.getConfigurationSection("StaffMode");
        assert cs != null;
        if (!cs.isConfigurationSection(p.getName())) {
            cs.createSection(p.getName());
        }

        ConfigurationSection plrInv = cfg.getConfigurationSection("StaffMode." + p.getName());
        assert plrInv != null;
        plrInv.set("armor", p.getInventory().getArmorContents());
        plrInv.set("content", p.getInventory().getContents());

        try {
            cfg.save(cfgFile);
        } catch (IOException e) {
            p.sendMessage(Utils.prefix + Utils.chat("Er is iets fout gegaan tijdens het activeren van de Staff Mode!"));
            e.printStackTrace();
        }
    }

    private void restoreInv(Player p) {
        cfg = plugin.cfgm.getStaffmodeCFG();
        ConfigurationSection cs = cfg.getConfigurationSection("StaffMode." + p.getName());
        ItemStack[] content = ((List<ItemStack>) cs.get("armor")).toArray(new ItemStack[0]);
        p.getInventory().setArmorContents(content);
        content = ((List<ItemStack>) cs.get("content")).toArray(new ItemStack[0]);
        p.getInventory().setContents(content);
        try {
            cfg.set("StaffMode." + p.getName(), null);
            cfg.save(cfgFile);
        } catch (IOException e) {
            p.sendMessage(Utils.prefix + Utils.chat("Er is iets fout gegaan tijdens het verlaten van de Staff Mode!"));
            e.printStackTrace();
        }
    }
}
