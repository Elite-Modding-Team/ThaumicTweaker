package mod.emt.thaumictweaker.mixins.client.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.api.casters.ICaster;
import thaumcraft.common.lib.events.KeyHandler;

@SideOnly(Side.CLIENT)
@Mixin(value = KeyHandler.class, remap = false)
public class KeyHandlerMixin {
    @Shadow public static KeyBinding keyF;
    @Shadow public static KeyBinding keyG;

    /**
     * @author Invadermonky
     * @reason Fixes Thaumcraft's Change Caster Focus keybind overwriting all other conflicting keybinds.
     */
    @Inject(method = "<clinit>", at = @At(value = "TAIL"))
    private static void testMixin(CallbackInfo ci) {
        //TODO: Add this to readme and changelog if we keep the fix.
        keyF.setKeyModifierAndCode(KeyModifier.SHIFT, keyF.getKeyCode());
        keyF.setKeyConflictContext(
                new IKeyConflictContext() {
                    @Override
                    public boolean isActive() {
                        EntityPlayerSP playerSP = Minecraft.getMinecraft().player;
                        return playerSP.getHeldItemMainhand().getItem() instanceof ICaster || playerSP.getHeldItemOffhand().getItem() instanceof ICaster;
                    }

                    @Override
                    public boolean conflicts(IKeyConflictContext other) {
                        return true;
                    }
                }
        );

        keyG.setKeyConflictContext(
                new IKeyConflictContext() {
                    @Override
                    public boolean isActive() {
                        if(FMLClientHandler.instance().getClient().inGameHasFocus) {
                            EntityPlayerSP playerSP = Minecraft.getMinecraft().player;
                            return playerSP.getHeldItemMainhand().getItem() instanceof ICaster || playerSP.getHeldItemOffhand().getItem() instanceof ICaster;
                        }
                        return false;
                    }

                    @Override
                    public boolean conflicts(IKeyConflictContext other) {
                        return true;
                    }
                }
        );
    }
}
