package fishcute.celestial.mixin.api;

import com.mojang.blaze3d.vertex.VertexBuffer;
import fishcute.celestialmain.api.minecraft.wrappers.IShaderInstanceWrapper;
import fishcute.celestialmain.api.minecraft.wrappers.IVertexBufferWrapper;
import net.minecraft.client.renderer.ShaderInstance;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(VertexBuffer.class)
public class VertexBufferMixin implements IVertexBufferWrapper {
    @Override
    public void celestial$bind() {
        ((VertexBuffer)(Object) this).bind();
    }

    @Override
    public void celestial$drawWithShader(Object matrix, Object projectionMatrix, IShaderInstanceWrapper shader) {
        ((VertexBuffer)(Object) this).drawWithShader((Matrix4f) matrix, (Matrix4f) projectionMatrix, (ShaderInstance) shader);
    }
}
