package me.Lucas.EvilSlaughters.staff.staffmode.inventory;

import me.Lucas.EvilSlaughters.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import static org.bukkit.Material.PLAYER_HEAD;

public class StaffMode {

    public static Inventory staffMode;
    public static String invname;
    public static int inv_rows = 4 * 9;

    public static void initialize() {
        invname = Utils.chat("Staff Mode");

        staffMode = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory staffModeInv(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, invname);

        Utils.createItem(staffMode, Material.COMPASS, 1, 1, "&bCompass");
        Utils.createItem(staffMode, Material.BOOK, 1, 2, "&5Inspect Book");
        Utils.createItem(staffMode, Material.WOODEN_AXE, 1, 3, "&6WorldEdit Wand");

        Utils.createItemHead(staffMode, adminHead(p), 1, 8, "&cOnline Staff");
        Utils.createItem(staffMode, Material.GRAY_DYE, 1, 9, "&7Visible");

        toReturn.setContents(staffMode.getContents());
        return toReturn;
    }

    private static ItemStack adminHead(Player p) {
        ItemStack adminHead = new ItemStack(PLAYER_HEAD);
        SkullMeta adminHeadM = (SkullMeta) adminHead.getItemMeta();

        assert adminHeadM != null;
        adminHeadM.setOwningPlayer(p);
        adminHead.setItemMeta(adminHeadM);
        return adminHead;
    }
}
