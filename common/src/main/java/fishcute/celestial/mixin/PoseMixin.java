package fishcute.celestial.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PoseStack.Pose.class)
public interface PoseMixin {
    @Accessor
    Matrix4f getPose();

    @Accessor
    Matrix3f getNormal();
}
