package me.Lucas.EvilSlaughters.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {

    public static String prefix = "§8[§4§lES§8] §7";
    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    public static String noPermission = prefix + chat("Jij hebt geen toestemming om dit te gebruiken");

    public static ItemStack empireWand() {
        ItemStack i = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = i.getItemMeta();

        meta.setDisplayName(chat("&cCrimson Wand"));

        i.setItemMeta(meta);
        return i;
    }
}
