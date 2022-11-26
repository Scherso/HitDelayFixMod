package io.ghast.hitdelayfix.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class ClassTransformer implements IClassTransformer
{

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes)
    {
        if (!transformedName.equals("net.minecraft.client.Minecraft")) return (bytes);

        final ClassReader READER = new ClassReader(bytes);
        final ClassNode NODE = new ClassNode();
        READER.accept(NODE, 0);

        for (MethodNode method : NODE.methods)
        {
            final String methodName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(NODE.name, method.name, method.desc);
            if (methodName.equals("clickMouse") || methodName.equals("func_147116_af"))
            {
                for (final AbstractInsnNode INSN : method.instructions.toArray())
                {
                    if (INSN.getOpcode() == BIPUSH && ((IntInsnNode) INSN).operand == 10)
                    {
                        method.instructions.set(INSN, new MethodInsnNode(
                                INVOKESTATIC,
                                "io/ghast/hitdelayfix/HitDelayFix",
                                "getHitDelay",
                                "()I",
                                false
                        ));
                    }
                }
            }
        }

        final ClassWriter WRITER = new ClassWriter(READER, 0);
        NODE.accept(WRITER);
        return (WRITER.toByteArray());
    }

}
