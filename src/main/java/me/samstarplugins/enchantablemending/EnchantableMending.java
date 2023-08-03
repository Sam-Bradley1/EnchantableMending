package me.samstarplugins.enchantablemending;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;


public final class EnchantableMending extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Register the event listener
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Plugin enabled successfully!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled.");
    }

    @EventHandler
    public void onEnchantItem(EnchantItemEvent event) {
        // Get the item being enchanted
        ItemStack item = event.getItem();


        item.addEnchantment(Enchantment.MENDING, 1);
    }
}
