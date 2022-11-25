package io.ghast.hitdelayfix;

import io.ghast.hitdelayfix.command.Command;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.io.File;

@Mod(modid = HitDelayFix.ID, name = HitDelayFix.NAME, version = HitDelayFix.VER)
public class HitDelayFix
{

    public static final String NAME = "@NAME@", VER = "@VERSION@", ID = "@ID@";

    @Mod.Instance(ID)
    public static HitDelayFix INSTANCE;
    public boolean isEnabled;
    private File configFile;

    /**
     * Used in {@link io.ghast.hitdelayfix.asm.ClassTransformer} in a method instructions node.
     * @return substitute for {@link net.minecraft.client.Minecraft#leftClickCounter}
     */
    @SuppressWarnings("unused")
    public static int getHitDelay()
    {
        if (INSTANCE.isEnabled) return (0);
        return (10);
    }

    @Mod.EventHandler
    protected void onInitialization(FMLInitializationEvent event)
    {
        ClientCommandHandler.instance.registerCommand(new Command());
        this.configFile = new File(Loader.instance().getConfigDir(), "hitdelayfix.cfg");
        this.loadConfig();
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void saveConfig()
    {
        Configuration CONFIG = new Configuration(this.configFile);
        this.updateConfig(CONFIG, true);
        CONFIG.save();
    }

    public void loadConfig()
    {
        final Configuration CONFIG = new Configuration(this.configFile);
        CONFIG.load();
        this.updateConfig(CONFIG, false);
    }

    private void updateConfig(Configuration config, boolean save)
    {
        Property property = config.get("default", "isEnabled", true);
        if (save)
            property.set(this.isEnabled);
        else
            this.isEnabled = property.getBoolean();
    }

}
