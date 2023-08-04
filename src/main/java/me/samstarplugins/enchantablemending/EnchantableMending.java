package me.samstarplugins.enchantablemending;


import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;
import java.util.Random;



public final class EnchantableMending extends JavaPlugin implements Listener {

    private static final int MAX_LEVEL = 30;

    private boolean pluginEnabled;
    private boolean onlyMaxLvlEnchants;
    private int mendingChance;

    @Override
    public void onEnable() {
        loadConfig();
        // Register the event listener
        getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("EnchantableMending enabled successfully!");
    }

    @Override
    public void onDisable() {
        getLogger().info("EnchantableMending disabled.");
    }

    @EventHandler
    public void onEnchantItem(EnchantItemEvent event) {

        Random random = new Random();
        int randomNumber = random.nextInt(100);
        getLogger().info("Random number: ");
        getLogger().info(String.valueOf(randomNumber));

        ItemStack item = event.getItem();

        getLogger().info("Xp Cost: ");
        getLogger().info(String.valueOf(event.getExpLevelCost()));

        getLogger().info("Mending Chance:");
        getLogger().info(String.valueOf(mendingChance));

        if (event.getExpLevelCost() < MAX_LEVEL && onlyMaxLvlEnchants)
        {
            return;
        }
        if (randomNumber < mendingChance) {
            item.addUnsafeEnchantment(Enchantment.MENDING, 1);
        }




    }

    public void loadConfig()
    {
        saveDefaultConfig();

        pluginEnabled = getConfig().getBoolean("enable-plugin", true);

        if (!pluginEnabled) {
            // Disables the plugin if the setting is false
            getLogger().info("EnchantableMending disabled by config");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        onlyMaxLvlEnchants = getConfig().getBoolean("only-max-level-enchants", true);
        mendingChance = getConfig().getInt("mending-enchantment-percent-chance", 5);


    }

}
