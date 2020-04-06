package me.Lucas.EvilSlaughters;

import me.Lucas.EvilSlaughters.empirewand.listener.SelectSpell;
import me.Lucas.EvilSlaughters.managers.CommandManager;
import me.Lucas.EvilSlaughters.managers.ConfigManager;
import me.Lucas.EvilSlaughters.staff.staffchat.listener.StaffChatMessage;
import me.Lucas.EvilSlaughters.staff.staffmode.inventory.OnlineStaffGUI;
import me.Lucas.EvilSlaughters.staff.staffmode.inventory.StaffMode;
import me.Lucas.EvilSlaughters.staff.staffmode.listener.InventoryClick;
import me.Lucas.EvilSlaughters.staff.staffmode.listener.PlayerJoin;
import me.Lucas.EvilSlaughters.staff.staffmode.listener.StaffModeEvents;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public CommandManager cmdMngr;
    public ConfigManager cfgm;

    @Override
    public void onEnable() {
        cmdMngr = new CommandManager(this);
        cmdMngr.setup();

        loadConfig();
        loadConfigManager();

        getCommand("evilslaughters").setTabCompleter(new CommandManager(this));

        new SelectSpell(this);
        new InventoryClick(this);
        new StaffChatMessage(this);
        new StaffModeEvents(this);
        new PlayerJoin(this);

        StaffMode.initialize();
        OnlineStaffGUI.initialize();
    }

    @Override
    public void onDisable() {
        cfgm.saveStaffmodeFile();
        saveConfig();
    }

    public void loadConfigManager() {
        cfgm = new ConfigManager();
        cfgm.setup();

        cfgm.saveStaffmodeFile();
        cfgm.reloadStaffmode();
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
