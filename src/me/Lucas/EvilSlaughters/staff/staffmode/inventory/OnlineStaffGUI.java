package me.Lucas.EvilSlaughters.staff.staffmode.inventory;

import me.Lucas.EvilSlaughters.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import static org.bukkit.Material.PLAYER_HEAD;

public class OnlineStaffGUI {

    public static Inventory onlineStaff;
    public static String invname;
    public static int inv_rows = 4 * 9;

    public static void initialize() {
        invname = Utils.chat("Online Staff");

        onlineStaff = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory onlineStaffUI(Player p) {
        int i = 0;
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, invname);

            for (Player plr : Bukkit.getOnlinePlayers()) {
                if (plr.hasPermission("evilslaughters.staff")) {
                    Utils.createItemHead(onlineStaff, adminHead(plr), 1, i + 1, plr.getName());
                    i++;
                }
            }

        toReturn.setContents(onlineStaff.getContents());
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
