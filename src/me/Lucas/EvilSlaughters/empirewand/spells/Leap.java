package me.Lucas.EvilSlaughters.empirewand.spells;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class Leap {

    public static void fireSpell(Plugin plugin, Player p) {
        p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 100, (float) 1);

        Location newLoc = p.getEyeLocation();
        Vector newV = newLoc.getDirection().normalize().multiply(4);

        p.setVelocity(newV);
    }
}
