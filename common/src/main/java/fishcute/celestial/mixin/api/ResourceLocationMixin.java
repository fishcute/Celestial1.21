package fishcute.celestial.mixin.api;

import fishcute.celestialmain.api.minecraft.wrappers.IResourceLocationWrapper;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ResourceLocation.class)
public class ResourceLocationMixin implements IResourceLocationWrapper {
}
