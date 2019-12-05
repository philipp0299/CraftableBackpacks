package de.randomStone42.craftablebackpacks.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.randomStone42.craftablebackpacks.commands.BigBackpackCommand;
import de.randomStone42.craftablebackpacks.commands.GiveBigBackpack;
import de.randomStone42.craftablebackpacks.commands.GiveMediumBackpack;
import de.randomStone42.craftablebackpacks.commands.GiveSmallBackpack;
import de.randomStone42.craftablebackpacks.commands.MediumBackpackCommand;
import de.randomStone42.craftablebackpacks.commands.SmallBackpackCommand;
import de.randomStone42.craftablebackpacks.items.BigBackpack;
import de.randomStone42.craftablebackpacks.items.MediumBackpack;
import de.randomStone42.craftablebackpacks.items.SmallBackpack;
import de.randomStone42.craftablebackpacks.util.RecipeLoader;

public class Main extends JavaPlugin {

	private static Main plugin;
	private File backpacks;
	private FileConfiguration customConfig;

	public void onEnable() {
		System.out.println("Hello World!");
		plugin = this;
		
		if (!(new File(this.getDataFolder(), "config.yml").exists())) {
			plugin.getConfig().set("SmallBackpack.Size", 9);
			plugin.getConfig().set("SmallBackpack.Color.R", 85);
			plugin.getConfig().set("SmallBackpack.Color.G", 106);
			plugin.getConfig().set("SmallBackpack.Color.B", 253);
			plugin.getConfig().set("MediumBackpack.Size", 18);
			plugin.getConfig().set("MediumBackpack.Color.R", 113);
			plugin.getConfig().set("MediumBackpack.Color.G", 238);
			plugin.getConfig().set("MediumBackpack.Color.B", 106);
			plugin.getConfig().set("BigBackpack.Size", 27);
			plugin.getConfig().set("BigBackpack.Color.R", 234);
			plugin.getConfig().set("BigBackpack.Color.G", 71);
			plugin.getConfig().set("BigBackpack.Color.B", 65);
			plugin.getConfig().getInt("BigBackpack.Size");
			plugin.saveConfig();
		}
		
		new RecipeLoader().registerRecipes();
		backpacks = new File(plugin.getDataFolder() + "/backpacks.yml");
		customConfig = YamlConfiguration.loadConfiguration(backpacks);

		getCommand("backpacksmall").setExecutor(new SmallBackpackCommand());
		getCommand("backpackmedium").setExecutor(new MediumBackpackCommand());
		getCommand("backpackbig").setExecutor(new BigBackpackCommand());
		getCommand("givesmallbackpack").setExecutor(new GiveBigBackpack());
		getCommand("givemediumbackpack").setExecutor(new GiveMediumBackpack());
		getCommand("givebigbackpack").setExecutor(new GiveSmallBackpack());

		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new SmallBackpack(), this);
		pluginManager.registerEvents(new MediumBackpack(), this);
		pluginManager.registerEvents(new BigBackpack(), this);
	}

	public static Main getPlugin() {
		return plugin;
	}

	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		try {
			ymlConfig.save(ymlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the customConfig
	 */
	public FileConfiguration getCustomConfig() {
		return customConfig;
	}

	/**
	 * 
	 * @return the customFile
	 */
	public File getBackpacks() {
		return backpacks;
	}

}
