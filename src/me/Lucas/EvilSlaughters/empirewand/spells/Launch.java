package me.Lucas.EvilSlaughters.empirewand.spells;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class Launch {

    public static void fireCrimsonSpell(Plugin plugin, Player p) {
        World w = p.getWorld();

        Location target = p.getTargetBlock(null, 40).getLocation();

        final Firework fw = w.spawn(Launch.getCenter(target), Firework.class);

        for (Entity t : fw.getNearbyEntities(0.5, 1, 0.5)) {
            Vector newV = t.getLocation().toVector();
            newV.setX(0);
            newV.setZ(0);
            newV.setY(1.5);

            t.setVelocity(newV);
        }

        fw.setSilent(true);
        FireworkMeta fmeta = fw.getFireworkMeta();
        FireworkEffect fwEffect = FireworkEffect.builder().flicker(false).trail(false).withColor(Color.fromRGB(99, 17, 16), Color.BLACK).build();

        fmeta.addEffect(fwEffect);
        fw.setFireworkMeta(fmeta);
        fw.detonate();
    }

    private static Location getCenter(Location loc) {
        return new Location(loc.getWorld(),
                getRelativeCoord(loc.getBlockX()),
                loc.getBlockY() + 1,
                getRelativeCoord(loc.getBlockZ()));
    }

    private static double getRelativeCoord(int i) {
        double d = i;
        d += .5;
        return d;
    }
}
