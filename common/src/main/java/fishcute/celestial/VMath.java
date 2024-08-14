package fishcute.celestial;

import net.minecraft.util.Mth;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class VMath {
    public static float sin(float o) {
        return Mth.sin(o);
    }
    public static float cos(float o) {
        return Mth.cos(o);
    }
    public static void matrix3fCopyQuaternion(Matrix3f matrix3f, Object quaternion) {
        var quat = (Quaternionf) quaternion;
        float f = quat.w;
        float g = quat.x;
        float h = quat.y;
        float i = quat.z;
        float j = 2.0F * f * f;
        float k = 2.0F * g * g;
        float l = 2.0F * h * h;
        matrix3f.m00(1.0F - k - l);
        matrix3f.m11(1.0F - l - j);
        matrix3f.m22(1.0F - j - k);
        float m = f * g;
        float n = g * h;
        float o = h * f;
        float p = f * i;
        float q = g * i;
        float r = h * i;
        matrix3f.m10(2.0F * (m + r));
        matrix3f.m01(2.0F * (m - r));
        matrix3f.m20(2.0F * (o - q));
        matrix3f.m02(2.0F * (o + q));
        matrix3f.m21(2.0F * (n + p));
        matrix3f.m12(2.0F * (n - p));
    }
    public static void matrix3fSetAxisAngle(Matrix3f matrix3f, Vector3f axis, float angle) {
        angle *= 0.017453292F;

        float g = Mth.sin(angle / 2.0F);
        var a = axis.x() * g;
        var b = axis.y() * g;
        var c = axis.z() * g;
        var d = Mth.cos(angle / 2.0F);

        float j = 2.0F * a * a;
        float k = 2.0F * b * b;
        float l = 2.0F * c * c;
        matrix3f.m00(1.0F - k - l);
        matrix3f.m11(1.0F - l - j);
        matrix3f.m22(1.0F - j - k);
        float m = a * b;
        float n = b * c;
        float o = c * a;
        float p = a * d;
        float q = b * d;
        float r = c * d;
        matrix3f.m10(2.0F * (m + r));
        matrix3f.m01(2.0F * (m - r));
        matrix3f.m20(2.0F * (o - q));
        matrix3f.m02(2.0F * (o + q));
        matrix3f.m21(2.0F * (n + p));
        matrix3f.m12(2.0F * (n - p));
    }

    public static void matrix4fCopyQuaternion(Matrix4f matrix4f, Quaternionf quaternion) {
        float f = quaternion.w;
        float g = quaternion.x;
        float h = quaternion.y;
        float i = quaternion.z;
        float j = 2.0F * f * f;
        float k = 2.0F * g * g;
        float l = 2.0F * h * h;
        matrix4f.m00(1.0F - k - l);
        matrix4f.m11(1.0F - l - j);
        matrix4f.m22(1.0F - j - k);
        matrix4f.m33(1.0F);
        float m = f * g;
        float n = g * h;
        float o = h * f;
        float p = f * i;
        float q = g * i;
        float r = h * i;
        matrix4f.m10(2.0F * (m + r));
        matrix4f.m01(2.0F * (m - r));
        matrix4f.m20(2.0F * (o - q));
        matrix4f.m02(2.0F * (o + q));
        matrix4f.m21(2.0F * (n + p));
        matrix4f.m12(2.0F * (n - p));
    }

    public static void matrix4fCopyMatrix3f(Matrix4f matrix4f, Matrix3f matrix3f) {
        matrix4f.m00((matrix3f).m00());
        matrix4f.m11((matrix3f).m11());
        matrix4f.m22((matrix3f).m22());
        matrix4f.m10((matrix3f).m10());
        matrix4f.m01((matrix3f).m01());
        matrix4f.m20((matrix3f).m20());
        matrix4f.m02((matrix3f).m02());
        matrix4f.m21((matrix3f).m21());
        matrix4f.m12((matrix3f).m12());

        matrix4f.m03(0.0f);
        matrix4f.m13(0.0f);
        matrix4f.m23(0.0f);
        matrix4f.m30(0.0f);
        matrix4f.m31(0.0f);
        matrix4f.m32(0.0f);
        matrix4f.m33(0.0f);
    }
}
