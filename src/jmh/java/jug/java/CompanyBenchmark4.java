package jug.java;

import jug.java.optimized.Company;
import kotlin.sequences.SequencesKt;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(2)
public class CompanyBenchmark4 extends SkeletalCompanyBenchmark{

    protected jug.java.optimized.Company optimizedJavaCompany;

    @Setup
    public void init() {
        super.init();
        optimizedJavaCompany = new Company("jug");
    }


    @Benchmark
    @CompilerControl(CompilerControl.Mode.EXCLUDE)
    public int javaOptimizedSumWeights() {
        return optimizedJavaCompany.getTrains()
                .mapToInt(jug.java.optimized.Train::getWeight)
                .sum();
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.EXCLUDE)
    public int javaSumWeights() {
        return javaCompany.getTrains()
                .map(jug.java.Train::getWeight)
                .mapToInt(jug.java.Ton::getMass)
                .sum();
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.EXCLUDE)
    public int kotlinSumWeights() {
        return SequencesKt.sumBy(kotlinCompany.getTrains(),
                jug.kotlin.Train::getWeight);
    }


}
