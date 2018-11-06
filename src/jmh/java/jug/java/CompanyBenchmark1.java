package jug.java;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(2)
public class CompanyBenchmark1 extends SkeletalCompanyBenchmark{


    @Setup
    public void init() {
        super.init();
    }

    @Benchmark
    public Object javaGetTrains() {
        return javaCompany.getTrains();
    }

    @Benchmark
    public Object kotlinGetTrains() {
        return kotlinCompany.getTrains();
    }

}
