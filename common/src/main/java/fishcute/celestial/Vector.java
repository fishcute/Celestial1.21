package fishcute.celestial;

import fishcute.celestialmain.api.minecraft.IMcVector;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

public class Vector implements IMcVector {
    private final BlockPos.MutableBlockPos blockPos;
    public static Vector fromVec(Vec3 v) {
        return new Vector(v.x, v.y, v.z);
    }

    public Vec3 toVec() {
        return new Vec3(this.x, this.y, this.z);
    }
    public double x;
    public double y;
    public double z;
    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        blockPos = new BlockPos.MutableBlockPos(x, y, z);
    }

    @Override
    public float x() {
        return (float) this.x;
    }

    @Override
    public float y() {
        return (float) this.y;
    }

    @Override
    public float z() {
        return (float) this.z;
    }

    @Override
    public float setX(float v) {
        this.x = v;
        this.blockPos.set(v, y, z);
        return v;
    }

    @Override
    public float setY(float v) {
        this.y = v;
        this.blockPos.set(x, v, z);
        return v;
    }

    @Override
    public float setZ(float v) {
        this.z = v;
        this.blockPos.set(x, y, v);
        return v;
    }

    @Override
    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.blockPos.set(x, y, z);
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.blockPos.set(x, y, z);
    }
    public BlockPos toBlockPos() {
        return this.blockPos;
    }
    // Putting this here to keep vec things in one file
    public static Vec3 toVecFromArray(double[] a) {
        return new Vec3(a[0], a[1], a[2]);
    }
}
