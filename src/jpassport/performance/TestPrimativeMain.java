package jpassport.performance;

import com.sun.jna.Native;
import jpassport.PassportFactory;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.stream.IntStream;

@State(Scope.Benchmark)
public class TestPrimativeMain {
    static JNITestLink jni;
    static TestLink JNA, JNADirect, passport, pureJava;

    public static void main(String[] args) throws Throwable {
        Options opt = new OptionsBuilder()
                .include(TestPrimativeMain.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

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

    @Benchmark
    @Fork(value = 2, warmups = 1)
    public void sumDJava()
    {
        pureJava.sumD(200, 400);
    }

    @Benchmark
    @Fork(value = 2, warmups = 1)
    public void sumDPassport()
    {
        passport.sumD(200, 400);
    }

    @Benchmark
    @Fork(value = 2, warmups = 1)
    public void sumDJNI()
    {
        jni.sumD(200, 400);
    }

    @Benchmark
    @Fork(value = 2, warmups = 1)
    public void sumDJNA()
    {
        JNA.sumD(200, 400);
    }


    @Benchmark
    @Fork(value = 2, warmups = 1)
    public void sumDJNADirect()
    {
        JNADirect.sumD(200, 400);
    }
}
