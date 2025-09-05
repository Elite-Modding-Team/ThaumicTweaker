package mod.emt.thaumictweaker.mixins.client.entity;

import mod.emt.thaumictweaker.config.ConfigEnhancementsTT;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import thaumcraft.client.renderers.entity.mob.RenderEldritchCrab;
import thaumcraft.common.entities.monster.EntityEldritchCrab;

@Mixin(value = RenderEldritchCrab.class, remap = false)
public abstract class RenderEldritchCrabMixin extends RenderLiving<EntityEldritchCrab> {

    public RenderEldritchCrabMixin(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }

    //Makes eldritch crabs rotate all the way during death like spiders, this would make more sense visually.
    @Override
    public float getDeathMaxRotation(@NotNull EntityEldritchCrab entity) {
        return ConfigEnhancementsTT.eldritchCrabFullDeathRotation ? 180.0F : super.getDeathMaxRotation(entity);
    }

}
