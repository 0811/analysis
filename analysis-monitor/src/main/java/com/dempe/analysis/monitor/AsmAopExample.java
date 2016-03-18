package com.dempe.analysis.monitor;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/18
 * Time: 14:23
 * To change this template use File | Settings | File Templates.
 */

import org.objectweb.asm.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class AsmAopExample extends ClassLoader implements Opcodes {




    public static void main(String[] args) throws IOException, IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {

        ClassReader cr = new ClassReader("com.dempe.analysis.monitor.Test");
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new MethodChangeClassAdapter(cw);
        cr.accept(cv, Opcodes.ASM4);

        // gets the bytecode of the Example class, and loads it dynamically
        byte[] code = cw.toByteArray();

        AsmAopExample loader = new AsmAopExample();
        loader.defineClass("com.dempe.analysis.monitor.Test", code, 0, code.length);

        Test test = new Test();
        test.test("------------------");
    }

    static class MethodChangeClassAdapter extends ClassVisitor implements Opcodes {

        public MethodChangeClassAdapter(final ClassVisitor cv) {
            super(Opcodes.ASM4, cv);
        }


        @Override
        public MethodVisitor visitMethod(
                int access,
                String name,
                String desc,
                String signature,
                String[] exceptions) {
            if ("test".equals(name))  //此处的execute即为需要修改的方法  ，修改方法內容
            {
                MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);//先得到原始的方法
                MethodVisitor newMethod = null;
                newMethod = new AsmMethodVisit(mv); //访问需要修改的方法
                return newMethod;
            }

            return null;
        }


    }

    static class AsmMethodVisit extends MethodVisitor {
        private long bgTime;

        public AsmMethodVisit(MethodVisitor mv) {
            super(Opcodes.ASM4, mv);
        }

        @Override
        public void visitCode() {
            //此方法在访问方法的头部时被访问到，仅被访问一次

            bgTime = System.currentTimeMillis();
            System.out.println(bgTime);;
            super.visitCode();

        }

        @Override
        public void visitInsn(int opcode) {
            //此方法可以获取方法中每一条指令的操作类型，被访问多次
            //如应在方法结尾处添加新指令，则应判断：
            if (opcode == Opcodes.RETURN) {

                System.out.println(System.currentTimeMillis()-bgTime);
            }
            super.visitInsn(opcode);
        }

    }

}
