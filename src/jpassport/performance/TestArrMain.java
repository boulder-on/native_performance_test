package jpassport.performance;

import com.sun.jna.Native;
import jpassport.PassportFactory;
//import jpassport.performance.jextract.UsingJExtract;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.stream.IntStream;

@State(Scope.Benchmark)
public class TestArrMain
{
    public static long loops = 10000;

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(TestArrMain.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    static JNITestLink jni;
    static TestLink JNA, JNADirect, passport, pureJava;

    @Setup()
    public void setUp() throws Throwable
    {
        System.setProperty("jna.library.path", System.getProperty("java.library.path"));
        jni = new JNITestLink();

        passport = PassportFactory.link("libpassport_test.dll", TestLink.class);
        JNA =  Native.load("libpassport_test.dll", TestLink.class);
        JNADirect =  new TestLinkJNADirect.JNADirect();
        pureJava = new PureJava();
    }

    @Param({"32", "512", "1024", "16384"})
    public int array_size;

    public double[] test_arr;

    @Setup(Level.Trial)
    public void updateArray()
    {
        test_arr = IntStream.range(0, array_size).mapToDouble(i -> i).toArray();

    }


    @Benchmark
    @Fork(value = 2, warmups = 1)
    public void sumArrDJava()
    {
        pureJava.sumArrD(test_arr, test_arr.length);
    }

    @Benchmark
    @Fork(value = 2, warmups = 1)
    public void sumArrDPassport()
    {
        passport.sumArrD(test_arr, test_arr.length);
    }

    @Benchmark
    @Fork(value = 2, warmups = 1)
    public void sumArrDJNI()
    {
        jni.sumArrD(test_arr, test_arr.length);
    }

    @Benchmark
    @Fork(value = 2, warmups = 1)
    public void sumArrDJNA()
    {
        JNA.sumArrD(test_arr, test_arr.length);
    }

    @Benchmark
    @Fork(value = 2, warmups = 1)
    public void sumArrDJNADirect()
    {
        JNADirect.sumArrD(test_arr, test_arr.length);
    }
}
