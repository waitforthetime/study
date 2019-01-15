package com.wy.study.calldll;

public class Runner {

    public native void hello();

    static {
        String property = System.getProperty("java.library.path");
        System.out.println(property);
        //设置查找路径为当前项目路径
//        System.setProperty("java.library.path", "/Users/wangyuan/wy/gitCode/study/callDll/resources");
//        加载动态库的名称
        String runner = System.mapLibraryName("Runner");
        System.out.println(runner);
        System.loadLibrary("Runner");


    }

    public static void main(String[] args) {
//        File file = new File(".");
//        for (File file1 : file.listFiles()) {
//            System.out.println(file1.getName());
//        }
        new Runner().hello();
    }

}
