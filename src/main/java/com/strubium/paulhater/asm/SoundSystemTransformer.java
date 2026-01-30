package com.strubium.paulhater.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.*;

public class SoundSystemTransformer implements IClassTransformer {

    private static final String TARGET_CLASS = "paulscode.sound.SoundSystem";

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (!transformedName.equals(TARGET_CLASS)) {
            return basicClass;
        }

        ClassReader cr = new ClassReader(basicClass);
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

        ClassVisitor cv = new ClassVisitor(ASM5, cw) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc,
                                             String signature, String[] exceptions) {

                if (name.equals("importantMessage")
                        && desc.equals("(Ljava/lang/String;I)V")) {

                    MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
                    mv.visitCode();
                    mv.visitInsn(RETURN);
                    mv.visitMaxs(0, 3);
                    mv.visitEnd();
                    return null;
                }

                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        };

        cr.accept(cv, 0);
        return cw.toByteArray();
    }
}
