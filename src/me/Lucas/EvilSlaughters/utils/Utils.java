package me.Lucas.EvilSlaughters.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

import static org.bukkit.Material.PLAYER_HEAD;

public class Utils {

    public static String prefix = "§8[§4§lES§8] §7";

    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String staffChat = prefix + chat("&8[&6&lStaffChat&8] &7");

    public static String noPermission = prefix + chat("Jij hebt geen toestemming om dit te gebruiken");

    public static ItemStack crimsonWand() {
        ItemStack i = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = i.getItemMeta();

        meta.setDisplayName(chat("&cCrimson Wand"));

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack unvanish() {
        ItemStack i = new ItemStack(Material.GRAY_DYE);
        ItemMeta meta = i.getItemMeta();

        assert meta != null;
        meta.setDisplayName(chat("&7Go Visible"));

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack vanish() {
        ItemStack i = new ItemStack(Material.LIME_DYE);
        ItemMeta meta = i.getItemMeta();

        assert meta != null;
        meta.setDisplayName(chat("&aGo Invisible"));

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack onlineStaff(Player p) {
        ItemStack i = new ItemStack(PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) i.getItemMeta();

        assert meta != null;
        meta.setDisplayName(chat("&cOnline Staff"));
        meta.setOwningPlayer(p);

        i.setItemMeta(meta);
        return i;
    }

    public static ItemStack createItem(Inventory inv, Material materialName, int amount, int invSlot, String displayName) {

        ItemStack item;

        item = new ItemStack(materialName, amount);

        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(Utils.chat(displayName));

        item.setItemMeta(meta);
        inv.setItem(invSlot - 1, item);
        return item;
    }

    public static ItemStack createItemLore(Inventory inv, Material materialName, int amount, int invSlot, String displayName, String... loreString) {

        ItemStack item;
        ArrayList<String> lore = new ArrayList<>();

        item = new ItemStack(materialName, amount);

        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(Utils.chat(displayName));

        for (String s : loreString) {
            lore.add(Utils.chat(s));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(invSlot - 1, item);
        return item;
    }

    public static void createItemHead(Inventory inv, ItemStack stackName, int amount, int invSlot, String displayName, String... loreString) {
        ItemStack item;
        ArrayList<String> lore = new ArrayList<>();

        item = stackName;

        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(Utils.chat(displayName));

        for (String s : loreString) {
            lore.add(Utils.chat(s));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(invSlot - 1, item);
    }
}
