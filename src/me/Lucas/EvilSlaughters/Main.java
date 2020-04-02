package me.Lucas.EvilSlaughters;

import me.Lucas.EvilSlaughters.empirewand.listener.SelectSpell;
import me.Lucas.EvilSlaughters.managers.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public CommandManager cmdMngr;

    @Override
    public void onEnable() {
        cmdMngr = new CommandManager(this);
        cmdMngr.setup();

        getCommand("evilslaughters").setTabCompleter(new CommandManager(this));

        new SelectSpell(this);
    }
}
