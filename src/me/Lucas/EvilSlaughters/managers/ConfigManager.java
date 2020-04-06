package me.Lucas.EvilSlaughters.managers;

import me.Lucas.EvilSlaughters.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class ConfigManager {

    private static ConfigManager manager = new ConfigManager();

    private File staffmodeFile;
    private FileConfiguration staffmodeCFG;


    private Main plugin = Main.getPlugin(Main.class);

    public static ConfigManager getManager() {
        return manager;
    }

    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        staffmodeFile = new File(plugin.getDataFolder(), "staffmode.yml");

        if (!staffmodeFile.exists()) {
            try {
                staffmodeFile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().log(Level.WARNING, "File could not be created!");
            }
        }

        staffmodeCFG = YamlConfiguration.loadConfiguration(staffmodeFile);
    }

    public FileConfiguration getStaffmodeCFG() {
        staffmodeFile = new File(plugin.getDataFolder(), "staffmode.yml");
        staffmodeCFG = YamlConfiguration.loadConfiguration(staffmodeFile);
        return staffmodeCFG;
    }

    public void saveStaffmodeFile() {
        try {
            staffmodeFile = new File(plugin.getDataFolder(), "staffmode.yml");
            staffmodeCFG = YamlConfiguration.loadConfiguration(staffmodeFile);
            if (!staffmodeCFG.isConfigurationSection("StaffMode")) {
                staffmodeCFG.createSection("StaffMode");
            }
            staffmodeCFG.save(staffmodeFile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "File could not be saved.");
        }
    }

    public void reloadStaffmode() {
        staffmodeCFG = YamlConfiguration.loadConfiguration(staffmodeFile);
    }
}
