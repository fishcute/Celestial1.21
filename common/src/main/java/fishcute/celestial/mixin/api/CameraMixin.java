package fishcute.celestial.mixin.api;

import fishcute.celestialmain.api.minecraft.wrappers.ICameraWrapper;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Camera.class)
public class CameraMixin implements ICameraWrapper {
    @Override
    public boolean celestial$doesFogBlockSky() {
        var self = (Camera)(Object) this;
        return self.getFluidInCamera() != FogType.POWDER_SNOW && self.getFluidInCamera() != FogType.LAVA;
    }

    @Override
    public boolean celestial$doesMobEffectBlockSky() {
        Entity entity = Minecraft.getInstance().getCameraEntity();
        if (!(entity instanceof LivingEntity livingEntity)) {
            return false;
        } else {
            return livingEntity.hasEffect(MobEffects.BLINDNESS) || livingEntity.hasEffect(MobEffects.DARKNESS);
        }
    }
}
