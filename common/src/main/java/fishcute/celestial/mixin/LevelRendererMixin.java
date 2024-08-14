package fishcute.celestial.mixin;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexBuffer;
import fishcute.celestialmain.api.minecraft.wrappers.*;
import fishcute.celestialmain.sky.CelestialSky;
import fishcute.celestialmain.version.independent.VersionLevelRenderer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

    @Shadow
    @Nullable
    private VertexBuffer skyBuffer;
    @Shadow
    @Nullable
    private VertexBuffer darkBuffer;
    @Shadow
    @Nullable
    private ClientLevel level;
    private static PoseStack poseStack = new PoseStack();
    @Inject(method = "renderSky", at = @At("HEAD"), cancellable = true)
    private void renderSky(Matrix4f matrices, Matrix4f projectionMatrix, float tickDelta, Camera camera, boolean bl, Runnable runnable, CallbackInfo info) {
        if (CelestialSky.doesDimensionHaveCustomSky()) {
            info.cancel();
            runnable.run();

            poseStack.mulPose(matrices);

            VersionLevelRenderer.renderLevel((Object) projectionMatrix,
                    (IPoseStackWrapper) poseStack,
                    (IVertexBufferWrapper) skyBuffer,
                    (IVertexBufferWrapper) darkBuffer,
                    (ICameraWrapper) camera,
                    (ILevelWrapper) level,
                    tickDelta,
                    null // Not required for 1.19
            );

            poseStack.clear();
            poseStack.setIdentity();
        }
    }
}
