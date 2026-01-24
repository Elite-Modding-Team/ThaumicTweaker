package mod.emt.thaumictweaker.mixins.misc;

import mod.emt.thaumictweaker.commands.CommandBaseTT;
import mod.emt.thaumictweaker.config.ConfigEnhancementsTT;
import net.minecraft.command.ICommand;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.Thaumcraft;

@Mixin(value = Thaumcraft.class, remap = false)
public class ThaumcraftModMixin {
    @Redirect(
            method = "serverLoad",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/fml/common/event/FMLServerStartingEvent;registerServerCommand(Lnet/minecraft/command/ICommand;)V"
            )
    )
    private void overhaulTCCommandMixin(FMLServerStartingEvent event, ICommand tcCommand) {
        if(ConfigEnhancementsTT.enableImprovedCommands) {
            event.registerServerCommand(new CommandBaseTT());
        } else {
            event.registerServerCommand(tcCommand);
        }
    }
}
