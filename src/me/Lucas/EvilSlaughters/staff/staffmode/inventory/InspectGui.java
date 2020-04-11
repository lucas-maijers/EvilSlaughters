package me.Lucas.EvilSlaughters.staff.staffmode.inventory;

import me.Lucas.EvilSlaughters.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InspectGui {

    public static Inventory playerInventory;
    public static String invname;
    public static int inv_rows = 5 * 9;

    public static void initialize(Player target) {
        invname = Utils.chat(String.format("%s Inventory", target.getName()));

        playerInventory = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory inspectInventory(Player p, Player target) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, invname);
        playerInventory.clear();

        playerInventory.setContents(target.getInventory().getContents());

        if (target.getInventory().getHelmet() != null) {
            Utils.createItemHead(playerInventory, target.getInventory().getHelmet(), 1, 37, target.getInventory().getHelmet().getItemMeta().getDisplayName(), "");
        }
        if (target.getInventory().getChestplate() != null) {
            Utils.createItemHead(playerInventory, target.getInventory().getChestplate(), 1, 38, target.getInventory().getChestplate().getItemMeta().getDisplayName(), "");
        }
        if (target.getInventory().getLeggings() != null) {
            Utils.createItemHead(playerInventory, target.getInventory().getLeggings(), 1, 39, target.getInventory().getLeggings().getItemMeta().getDisplayName(), "");
        }
        if (target.getInventory().getBoots() != null) {
            Utils.createItemHead(playerInventory, target.getInventory().getBoots(), 1, 40, target.getInventory().getBoots().getItemMeta().getDisplayName(), "");
        }

        toReturn.setContents(playerInventory.getContents());
        return toReturn;
    }

}
