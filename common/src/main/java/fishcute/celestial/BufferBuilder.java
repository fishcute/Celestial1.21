package fishcute.celestial;

import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import fishcute.celestialmain.api.minecraft.wrappers.IBufferBuilderWrapper;
import org.joml.Matrix4f;

import static fishcute.celestial.VInstances.bufferBuilder;

public class BufferBuilder implements IBufferBuilderWrapper {
    @Override
    public void celestial$beginTriangleFan() {
        bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
    }

    @Override
    public void celestial$beginObject() {
        bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
    }

    @Override
    public void celestial$beginColorObject() {
        bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
    }

    @Override
    public void celestial$vertex(Object matrix4f, float x, float y, float z, float r, float g, float b, float a) {
        bufferBuilder.addVertex((Matrix4f) matrix4f, x, y, z).setColor(r, g, b, a);
    }

    @Override
    public void celestial$vertexUv(Object matrix4f, float x, float y, float z, float u, float v, float r, float g, float b, float a) {
        bufferBuilder.addVertex((Matrix4f) matrix4f, x, y, z).setUv(u, v).setColor(r, g, b, a);
    }

    @Override
    public void celestial$upload() {
        BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());
    }
}
