package fishcute.celestial.mixin;

import fishcute.celestialmain.sky.CelestialSky;
import fishcute.celestialmain.sky.objects.ICelestialObject;
import fishcute.celestialmain.sky.objects.TwilightObject;
import fishcute.celestialmain.util.Util;
import fishcute.celestialmain.version.independent.Instances;
import fishcute.celestialmain.version.independent.VersionSky;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FogRenderer.class)
public class FogRendererMixin {

    @ModifyVariable(method = "setupFog", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private static boolean setupFog(boolean thickFog) {
        return VersionSky.checkThickFog(thickFog);
    }
    @Inject(method = "setupFog", at = @At("RETURN"))
    private static void setupFog(Camera camera, FogRenderer.FogMode fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo info) {
        VersionSky.setupFogStartEnd(fogType == FogRenderer.FogMode.FOG_SKY, viewDistance, thickFog);
        VersionSky.setupFog();
    }

    @Shadow
    private static float fogRed;

    @Shadow
    private static float fogGreen;

    @Shadow
    private static float fogBlue;

    @Inject(method = "setupColor", at = @At("RETURN"))
    private static void setupColor(Camera camera, float f, ClientLevel clientLevel, int i, float g, CallbackInfo ci) {
        float[] color = VersionSky.setupFogColor();

        if (color != null) {
            fogRed = color[0];
            fogGreen = color[1];
            fogBlue = color[2];
        }

        color = VersionSky.applyPostFogChanges(fogRed, fogGreen, fogBlue);
        fogRed = color[0];
        fogGreen = color[1];
        fogBlue = color[2];

        Instances.renderSystem.clearColor(fogRed, fogGreen, fogBlue, 0.0F);
    }
}