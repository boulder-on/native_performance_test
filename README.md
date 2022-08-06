# Native Performance Test

This code is an attempt at comparing the performance of various
Java methods for interacting with native code. The native code access
methods tested are:

- JNA
- JNA Direct
- JNI
- Foreign Linker API (using JPassport)

The testing I did was on Win 10 with and i7-10850H.

The tests look at the overhead in passing arrays of various sizes 
as well as passing primitives.

The method called here passes 2 doubles to the native code and the native code sums them:

| Benchmark   | Mode   | Cnt |          Score |               Error | Units |
|---|---|-----|---------------:|--------------------:|----|
| TestPrimativeMain.sumDJava   | thrpt  | 5   |     1782358674 |      ± 46727598 | ops/s |
| TestPrimativeMain.sumDJNA  |  thrpt | 5   |        1844900 |        ± 128607 | ops/s |
| TestPrimativeMain.sumDJNADirect  | thrpt  | 5   |       12900678 |        ± 331764 | ops/s |
| TestPrimativeMain.sumDJNI   | thrpt  | 5   |       91683788 |       ± 1455563 | ops/s |
| TestPrimativeMain.sumDPassport  | thrpt  | 5   |       93783830 | ± 706989 | ops/s |

JNI and The Foreign Linker are neck and neck for passing a primitive.

The next method I tried accepted an array of doubles and sums the first 5 elements. Only
the first 5 elements are summed in order to a) make sure the method isn't a no-op, b) as
much as possible to compare the transfer time to native only without native time affecting
things:

| Benchmark   | (array_size) | Mode   | Cnt |        Score |        Error | Units |
|---------------------------|-------------:|---|---|-------------:|-------------:|---|
|TestArrMain.sumArrDJava |           32 |  thrpt |    5| 91334755.748 | ± 775844.570 |  ops/s |
|TestArrMain.sumArrDJava |           64 | thrpt |   5 | 33710152.819 | ± 586613.831 | ops/s |
|TestArrMain.sumArrDJava |          128 | thrpt |   5 | 15137250.588 | ± 471617.257 | ops/s |
|TestArrMain.sumArrDJava |          256 | thrpt |   5 |  5747931.688 | ±  74696.000 | ops/s |
|TestArrMain.sumArrDJava |          512 |  thrpt |    5|  2485175.415 | ±  10049.625 |  ops/s |
|TestArrMain.sumArrDJava |         1024 |  thrpt |    5|  1169852.568 | ±  60076.255 |  ops/s |
|TestArrMain.sumArrDJava |        16384 |  thrpt |    5|    70040.174 | ±    319.466 |  ops/s |
|TestArrMain.sumArrDJNA |            32 | thrpt |   5 |   965025.944 | ±   16695.311 | ops/s |
|TestArrMain.sumArrDJNA |            64 | thrpt |   5 |   948315.409 | ±  15394.704 | ops/s |
|TestArrMain.sumArrDJNA |           128 | thrpt |   5 |   908491.307 | ±  17186.015 | ops/s |
|TestArrMain.sumArrDJNA |           256 | thrpt |   5 |   804422.590 | ±   4370.988 | ops/s |
|TestArrMain.sumArrDJNA |           512 | thrpt |   5 |   704481.922 | ±   17630.933 | ops/s |
|TestArrMain.sumArrDJNA |          1024 | thrpt |   5 |   558143.266 | ±    3294.863 | ops/s |
|TestArrMain.sumArrDJNA |         16384 | thrpt |   5 |    67235.177 | ±    2339.102 | ops/s |
|TestArrMain.sumArrDJNADirect |      32 | thrpt |   5 |  4910614.266 | ±   73626.251 | ops/s |
|TestArrMain.sumArrDJNADirect |      64 | thrpt |   5 |  4531601.613 | ±  35901.417 | ops/s |
|TestArrMain.sumArrDJNADirect |     128 | thrpt |   5 |  3629602.693 | ±  62248.271 | ops/s |
|TestArrMain.sumArrDJNADirect |     256 | thrpt |   5 |  2760306.793 | ±  19049.793 | ops/s |
|TestArrMain.sumArrDJNADirect |     512 | thrpt |   5 |  1864532.210 | ±   23614.753 | ops/s |
|TestArrMain.sumArrDJNADirect |    1024 | thrpt |   5 |  1126374.412 | ±   15975.735 | ops/s |
|TestArrMain.sumArrDJNADirect |   16384 | thrpt |   5 |    76438.943 | ±    2716.492 | ops/s |
|TestArrMain.sumArrDJNI |            32 | thrpt |   5 |  8752710.836 | ±   96588.358 | ops/s |
|TestArrMain.sumArrDJNI |            64 | thrpt |   5 |  7473582.774 | ±  93759.836 | ops/s |
|TestArrMain.sumArrDJNI |           128 | thrpt |   5 |  5382671.381 | ±  71644.094 | ops/s |
|TestArrMain.sumArrDJNI |           256 | thrpt |   5 |  3671953.233 | ±  45954.144 | ops/s |
|TestArrMain.sumArrDJNI |           512 | thrpt |   5 |  2226667.995 | ±   61307.160 | ops/s |
|TestArrMain.sumArrDJNI |          1024 | thrpt |   5 |  1232497.799 | ±   41455.176 | ops/s |
|TestArrMain.sumArrDJNI |         16384 | thrpt |   5 |    76333.053 | ±    1708.272 | ops/s |
|TestArrMain.sumArrDPassport |       32 | thrpt |   5 |  4980282.256 | ±   36781.491 | ops/s |
|TestArrMain.sumArrDPassport |       64 | thrpt |   5 |  4864624.277 | ±  48523.799 | ops/s |
|TestArrMain.sumArrDPassport |      128 | thrpt |   5 |  4623509.697 | ±  69654.343 | ops/s |
|TestArrMain.sumArrDPassport |      256 | thrpt |   5 |  4055315.136 | ±  38364.492 | ops/s |
|TestArrMain.sumArrDPassport |      512 | thrpt |   5 |  3611582.085 | ±   30586.740 | ops/s |
|TestArrMain.sumArrDPassport |     1024 | thrpt |   5 |  2603529.338 | ±   82543.662 | ops/s |
|TestArrMain.sumArrDPassport |    16384 | thrpt |   5 |   166257.326 | ±    2793.988 | ops/s |
<img src="plots/passing_primitives.png" alt="primative_performance" width="50%"/>

|                                                                                    |                                                                                    |
|------------------------------------------------------------------------------------|------------------------------------------------------------------------------------|
| <img src="plots/array_length_32.png" alt="Array_performance_32" width="100%"/>     | <img src="plots/array_length_64.png" alt="Array_performance_512" width="100%"/>    |
| <img src="plots/array_length_128.png" alt="Array_performance_128" width="100%"/>   | <img src="plots/array_length_256.png" alt="Array_performance_256" width="100%"/>   |
| <img src="plots/array_length_512.png" alt="Array_performance_512" width="100%"/>   | <img src="plots/array_length_1024.png" alt="Array_performance_1024" width="100%"/> |
| <img src="plots/array_length_16384.png" alt="Array_performance_16k" width="100%"/> |                                                                                    |

Interestingly, at very small array sizes JNI is the clear leader, but that lead is lost
to the Foreign Linker API (JPassport) when the arrays get larger. This points towards the
foreign linker potentially working better with machine learning data.

It's actually quite impressive here that JNA Direct competes so closely to custom JNI code. 
