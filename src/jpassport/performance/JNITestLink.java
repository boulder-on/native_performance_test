package jpassport.performance;


public class JNITestLink
{
    static {
        System.loadLibrary(TestLinkHelp.getLibName());
    }

    public native double sumD(double d, double d2);

    
    public native double sumArrD(double[] d, int len);

    
//    public native double sumArrDD(double[] d, double[] d2, int len);
//
//
//    public native void readD(double[] d, int set);
//
//
//    public native float sumArrF(float[] i, int len);
//
//
//    public native void readF(float[] d, float set);
//
//
//    public native long sumArrL(long[] i, long len);
//
//
//    public native void readL(long[] d, long set) ;
//
//
//    public native int sumArrI(int[] i, int len) ;
//
//
//    public native void readI(int[] d, int set) ;
//
//
//    public native short sumArrS(short[] i, short len) ;
//
//
//    public native void readS(short[] d, short set);
//
//
//    public native byte sumArrB(byte[] i, byte len);
//
//
//    public native void readB(byte[] d, byte set) ;
//
//
//    public native double sumMatD(int rows, int cols, double[][] mat) ;
//
//
//    public native double sumMatDPtrPtr(int rows, int cols, double[][] mat) ;
//
//
//    public native float sumMatF(int rows, int cols, float[][] mat) ;
//
//
//    public native float sumMatFPtrPtr(int rows, int cols, float[][] mat) ;
//
//
//    public native long sumMatL(int rows, int cols, long[][] mat) ;
//
//
//    public native long sumMatLPtrPtr(int rows, int cols, long[][] mat) ;
//
//
//    public native int sumMatI(int rows, int cols, int[][] mat) ;
//
//
//    public native int sumMatIPtrPtr(int rows, int cols, int[][] mat) ;
//
//
//    public native int sumMatS(int rows, int cols, short[][] mat) ;
//
//
//    public native int sumMatSPtrPtr(int rows, int cols, short[][] mat) ;
//
//
//    public native int sumMatB(int rows, int cols, byte[][] mat) ;
//
//
//    public native int sumMatBPtrPtr(int rows, int cols, byte[][] mat) ;
//
//
//    public native int cstringLength(String s) ;
//
//
//    public native String mallocString(String origString) ;
}
