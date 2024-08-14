package fishcute.celestial.mixin;

import fishcute.celestial.Vector;
import fishcute.celestialmain.version.independent.VersionSky;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {
    @Inject(method = "getCloudColor", at = @At("RETURN"), cancellable = true)
    private void getCloudColor(float f, CallbackInfoReturnable<Vec3> info) {
        double[] color = VersionSky.getCloudColor(new double[]{info.getReturnValue().x, info.getReturnValue().y, info.getReturnValue().z}, f);
        info.setReturnValue(Vector.toVecFromArray(color));
    }
    @ModifyVariable(method = "getCloudColor", at = @At("STORE"), ordinal = 3)
    private float getRed(float h) {
        return VersionSky.getCloudColorRed(h);
    }
    @ModifyVariable(method = "getCloudColor", at = @At("STORE"), ordinal = 4)
    private float getGreen(float i) {
        return VersionSky.getCloudColorGreen(i);
    }
    @ModifyVariable(method = "getCloudColor", at = @At("STORE"), ordinal = 5)
    private float getBlue(float j) {
        return VersionSky.getCloudColorBlue(j);
    }
    @Inject(method = "getSkyColor", at = @At("RETURN"), cancellable = true)
    private void getSkyColor(Vec3 vec3, float f, CallbackInfoReturnable<Vec3> info) {
        double[] color = VersionSky.getClientLevelSkyColor(new double[]{info.getReturnValue().x, info.getReturnValue().y, info.getReturnValue().z});
        info.setReturnValue(Vector.toVecFromArray(color));
    }
}
