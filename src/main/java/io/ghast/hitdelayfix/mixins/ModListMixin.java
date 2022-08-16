package io.ghast.hitdelayfix.mixins;

import io.ghast.hitdelayfix.HitDelayFix;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(FMLHandshakeMessage.ModList.class)
public class ModListMixin extends FMLHandshakeMessage {

    @Shadow(remap = false)
    private Map<String, String> modTags;

    /**
     * Removes mod ID from the map that forge sends to the server.
     *
     * @author __fastcall
     * @see net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage
     */
    @Inject(method = "<init>(Ljava/util/List;)V", at = @At("RETURN"), remap = false)
    private void removeMod(List<ModContainer> modList, CallbackInfo ci) {
        if (!Minecraft.getMinecraft().isSingleplayer())
            modTags.keySet().removeIf(key -> key.equalsIgnoreCase(HitDelayFix.ID));
    }

}