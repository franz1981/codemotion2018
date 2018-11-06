package jug.java;

import jug.java.optimized.Company;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(2)
public class CompanyBenchmark3 extends CompanyBenchmark2{

    protected jug.java.optimized.Company optimizedJavaCompany;

    @Setup
    public void init() {
        super.init();
        optimizedJavaCompany = new Company("jug");
    }

    @Benchmark
    public int javaOptimizedSumWeights() {
        return optimizedJavaCompany.getTrains()
                .mapToInt(jug.java.optimized.Train::getWeight)
                .sum();
    }

}
