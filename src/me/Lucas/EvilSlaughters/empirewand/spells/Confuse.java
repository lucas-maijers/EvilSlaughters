package me.Lucas.EvilSlaughters.empirewand.spells;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Confuse {

    public static void fireCrimsonSpell(Plugin plugin, Player p) {
        World w = p.getWorld();

        Location target = p.getTargetBlock(null, 40).getLocation();


        final Firework fw = w.spawn(getCenter(target), Firework.class);

        fw.setSilent(true);
        FireworkMeta fmeta = fw.getFireworkMeta();
        FireworkEffect fwEffect = FireworkEffect.builder().flicker(true).trail(false).with(FireworkEffect.Type.BURST).withColor(Color.fromRGB(99, 17, 16), Color.BLACK).build();

        fmeta.addEffect(fwEffect);
        fw.setFireworkMeta(fmeta);

        for (int x = target.getBlockX() - 5; x < target.getBlockX() + 5; x++) {
            for (int z = target.getBlockZ() - 7; z < target.getBlockZ() + 7; z++) {
                for (int y = target.getBlockY(); y < target.getBlockY() + 7; y++) {
                    w.spawnParticle(Particle.SMOKE_NORMAL, x, y, z, 2, 0, 0, 0, 0.01);
                }
            }
        }
        for (Entity t : fw.getNearbyEntities(5, 7, 7)) {
            if (t instanceof Player) {
                Player plr = ((Player) t).getPlayer();
                plr.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20 * 15, 1));
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
