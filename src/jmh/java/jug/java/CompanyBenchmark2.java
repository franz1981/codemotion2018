package jug.java;

import kotlin.sequences.SequencesKt;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(2)
public class CompanyBenchmark2 extends SkeletalCompanyBenchmark{


    @Setup
    public void init() {
        super.init();
    }

    @Benchmark
    public int javaSumWeights() {
        return javaCompany.getTrains()
                .map(jug.java.Train::getWeight)
                .mapToInt(jug.java.Ton::getMass)
                .sum();
    }

    @Benchmark
    public int kotlinSumWeights() {
        return SequencesKt.sumBy(kotlinCompany.getTrains(),
                jug.kotlin.Train::getWeight);
    }


}
