package me.Lucas.EvilSlaughters.empirewand.spells;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;

public class Spark {

    public static void fireCrimsonSpell(Plugin plugin, Player p) {
        World w = p.getWorld();

        Location target = p.getTargetBlock(null, 40).getLocation();

        final Firework fw = w.spawn(getCenter(target), Firework.class);

        fw.setSilent(true);
        FireworkMeta fmeta = fw.getFireworkMeta();
        FireworkEffect fwEffect = FireworkEffect.builder().flicker(true).trail(false).with(FireworkEffect.Type.BURST).withColor(Color.fromRGB(99, 17, 16), Color.BLACK).build();

        fmeta.addEffect(fwEffect);
        fw.setFireworkMeta(fmeta);

        for (Entity e : fw.getNearbyEntities(1, 1, 1)) {
            if (e instanceof LivingEntity) {
                ((LivingEntity) e).damage(2);
            }
        }

        fw.detonate();
    }

    public static void fireWarpedSpell(Plugin plugin, Player p) {
        World w = p.getWorld();

        Location target = p.getTargetBlock(null, 40).getLocation();

        final Firework fw = w.spawn(getCenter(target), Firework.class);

        fw.setSilent(true);
        FireworkMeta fmeta = fw.getFireworkMeta();
        FireworkEffect fwEffect = FireworkEffect.builder().flicker(true).trail(false).with(FireworkEffect.Type.BURST).withColor(Color.TEAL, Color.BLACK).build();

        fmeta.addEffect(fwEffect);
        fw.setFireworkMeta(fmeta);

        for (Entity e : fw.getNearbyEntities(1, 1, 1)) {
            if (e instanceof LivingEntity) {
                ((LivingEntity) e).damage(2);
            }
        }
        fw.detonate();
    }

    public static void fireEnderSpell(Plugin plugin, Player p) {
        World w = p.getWorld();

        Location target = p.getTargetBlock(null, 40).getLocation();

        final Firework fw = w.spawn(getCenter(target), Firework.class);

        fw.setSilent(true);
        FireworkMeta fmeta = fw.getFireworkMeta();
        FireworkEffect fwEffect = FireworkEffect.builder().flicker(true).trail(false).with(FireworkEffect.Type.BURST).withColor(Color.PURPLE, Color.BLACK).build();

        fmeta.addEffect(fwEffect);
        fw.setFireworkMeta(fmeta);

        for (Entity e : fw.getNearbyEntities(1, 1, 1)) {
            if (e instanceof LivingEntity) {
                ((LivingEntity) e).damage(2);
            }
        }
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
