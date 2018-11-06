package jug.java.optimized;

import jug.java.Ton;
import jug.java.Type;

import javax.annotation.Nonnull;
import java.util.Objects;

public final class Util {

    private Util() {
    }

    private static Type[] typeValues = Type.values();

    @Nonnull
    public static Train makeTrain(@Nonnull final Company company, @Nonnull final int trainCode) {
        Objects.requireNonNull(company);
        var seed = company.getName().hashCode();
        for (var i = 0; i < trainCode; i++) {
            seed += seed ^ i;
        }
        seed = Math.abs(seed);

        final var type = typeValues[seed % typeValues.length];
        @Nonnull Ton weight;
        switch (type) {
            case FAST:
                weight = new Ton(150);
                break;
            case LOCAL:
                weight = new Ton(140);
                break;
            case FREIGHT:
                weight = new Ton(180);
                break;
            default:
                throw new AssertionError("Unknown type " + type);
        }
        return new Train(trainCode, type, weight.getMass());
    }
}
