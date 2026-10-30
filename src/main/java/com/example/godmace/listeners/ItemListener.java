package com.example.godmace.listeners;
import com.example.godmace.GodMacePlugin;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ItemListener implements Listener {
    private GodMacePlugin plugin;
    private Set<UUID> dashImmunity = new HashSet<>();

    public ItemListener(GodMacePlugin plugin) { this.plugin = plugin; }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        ItemStack result = event.getRecipe().getResult();
        if (result == null || !result.hasItemMeta()) return;
        String name = result.getItemMeta().getDisplayName();
        
        String key = null; int limit = 0;
        if (name.contains("Dragon Mace")) { key = "mace"; limit = 1; }
        else if (name.contains("Dash Rune")) { key = "dash"; limit = 2; }
        else if (name.contains("Strength Rune")) { key = "strength"; limit = 2; }
        else if (name.contains("Lifesteal Rune")) { key = "lifesteal"; limit = 3; }
        else if (name.contains("Luck Rune")) { key = "luck"; limit = 2; }
        else if (name.contains("Speed Rune")) { key = "speed"; limit = 2; }

        if (key != null) {
            int current = plugin.getDataManager().getCraftCount(key);
            if (current >= limit) {
                event.setCancelled(true);
                event.getWhoClicked().sendMessage("§cCrafting Failed: Only " + limit + " " + name + "§c can exist on the server!");
            } else {
                plugin.getDataManager().incrementCraftCount(key);
                Bukkit.broadcastMessage("§6" + event.getWhoClicked().getName() + " has crafted the legendary " + name + "§6!");
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction().name().contains("RIGHT_CLICK") && event.getItem() != null) {
            if (event.getItem().hasItemMeta() && event.getItem().getItemMeta().getDisplayName().contains("Dash Rune")) {
                Player p = event.getPlayer();
                p.setVelocity(p.getLocation().getDirection().multiply(1.5).setY(1.2));
                dashImmunity.add(p.getUniqueId());
            }
        }
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            Player p = (Player) event.getEntity();
            if (dashImmunity.contains(p.getUniqueId())) {
                event.setCancelled(true);
                dashImmunity.remove(p.getUniqueId());
            } else {
                // Check if they passively have it in inventory
                for (ItemStack item : p.getInventory().getContents()) {
                    if (item != null && item.hasItemMeta() && item.getItemMeta().getDisplayName().contains("Dash Rune")) {
                        event.setCancelled(true);
                        break;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();
        if (killer != null) {
            boolean hasLifesteal = false;
            for (ItemStack item : killer.getInventory().getContents()) {
                if (item != null && item.hasItemMeta() && item.getItemMeta().getDisplayName().contains("Lifesteal Rune")) {
                    hasLifesteal = true; break;
                }
            }
            if (hasLifesteal) {
                double kMax = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
                double vMax = victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
                if (kMax < 30.0) killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(kMax + 2.0); // +1 heart
                if (vMax > 2.0) victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(vMax - 2.0);   // -1 heart
            }
        }
    }
}
