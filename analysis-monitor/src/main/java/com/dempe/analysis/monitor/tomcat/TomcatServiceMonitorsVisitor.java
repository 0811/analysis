package com.dempe.analysis.monitor.tomcat;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

//定义扫描待修改class的visitor，visitor就是访问者模式
public class TomcatServiceMonitorsVisitor extends ClassVisitor {
    private String className;

    public TomcatServiceMonitorsVisitor(ClassVisitor cv, String className) {
        super(Opcodes.ASM5, cv);
        this.className = className;
    }

    //扫描到每个方法都会进入，参数详情下一篇博文详细分析
    @Override
    public MethodVisitor visitMethod(int access, final String name, final String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        // 拦截StandardEngine logAccess(Request,Response,long time) 方法，

        if (name.equals("logAccess") && mv != null) {
            mv = new AdviceAdapter(Opcodes.ASM5, mv, access, name, desc) {

                @Override
                public void onMethodEnter() {
                    String name = TomcatLogMonitor.class.getName();
                    name = name.replaceAll("\\.", "/");
                    mv.visitCode();
                    mv.visitVarInsn(ALOAD, 1);
                    mv.visitVarInsn(ALOAD, 2);
                    mv.visitVarInsn(LLOAD, 3);
                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, name, "handle",
                            "(Ljavax/servlet/http/HttpServletRequest;Ljavax/servlet/http/HttpServletResponse;J)V", false);
                }

                @Override
                public void onMethodExit(int opcode) {
                }
            };
        }
        return mv;
    }
}
