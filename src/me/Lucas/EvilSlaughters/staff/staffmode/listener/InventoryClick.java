package me.Lucas.EvilSlaughters.staff.staffmode.listener;

import me.Lucas.EvilSlaughters.Main;
import me.Lucas.EvilSlaughters.staff.staffmode.inventory.InspectGui;
import me.Lucas.EvilSlaughters.staff.staffmode.inventory.OnlineStaffGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;

public class InventoryClick implements Listener {

    private Main plugin;

    public InventoryClick(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        String title = e.getView().getTitle();

        if (title.equals(OnlineStaffGUI.invname)) {
            e.setCancelled(true);
        }

        if (title.equals(InspectGui.invname)) {
            if (e.getCurrentItem() == null) {
                return;
            }

            StaffModeEvents.target.getInventory().setContents(InspectGui.inspectInventory((Player) e.getWhoClicked(), StaffModeEvents.target).getContents());

            StaffModeEvents.target.getInventory().setHelmet(InspectGui.inspectInventory((Player) e.getWhoClicked(), StaffModeEvents.target).getItem(36));
            StaffModeEvents.target.getInventory().setChestplate(InspectGui.inspectInventory((Player) e.getWhoClicked(), StaffModeEvents.target).getItem(37));
            StaffModeEvents.target.getInventory().setLeggings(InspectGui.inspectInventory((Player) e.getWhoClicked(), StaffModeEvents.target).getItem(38));
            StaffModeEvents.target.getInventory().setBoots(InspectGui.inspectInventory((Player) e.getWhoClicked(), StaffModeEvents.target).getItem(39));
        }
    }
}
