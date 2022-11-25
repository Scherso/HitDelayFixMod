package io.ghast.hitdelayfix;

import io.ghast.hitdelayfix.asm.ClassTransformer;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@IFMLLoadingPlugin.Name("FMLPlugin")
@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
@IFMLLoadingPlugin.TransformerExclusions({"io.ghast.hitdelayfix.asm"})
public class FMLPlugin implements IFMLLoadingPlugin
{

    @NotNull
    @Override
    public String[] getASMTransformerClass()
    {
        return (new String[]{ClassTransformer.class.getName()});
    }

    @Nullable
    @Override
    public String getModContainerClass()
    {
        return (null);
    }

    @Nullable
    @Override
    public String getSetupClass()
    {
        return (null);
    }

    @Override
    public void injectData(Map<String, Object> map)
    {
        /* Hello! */
    }

    @Nullable
    @Override
    public String getAccessTransformerClass()
    {
        return (null);
    }

}
