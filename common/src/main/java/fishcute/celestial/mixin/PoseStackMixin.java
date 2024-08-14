package fishcute.celestial.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Deque;

@Mixin(PoseStack.class)
public interface PoseStackMixin {
    @Accessor
    Deque<PoseStack.Pose> getPoseStack();
}
