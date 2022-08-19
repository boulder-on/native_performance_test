module performance.test.nat{
//    requires jdk.incubator.foreign;
    requires jpassport;
    requires com.sun.jna;
    requires jmh.core;
    requires jmh.generator.annprocess;
    requires jdk.unsupported;
    exports jpassport.performance.jmh_generated;

    exports jpassport.performance;
}