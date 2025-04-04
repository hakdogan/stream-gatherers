package org.jugistanbul.gatherer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

/**
 * @author hakdogan (hakdogan75@gmail.com)
 * Created on 16.03.2025
 ***/
public class SuspiciousGatherer {

    public static Gatherer<LogWrapper, List<LogWrapper>, String> of(final int threshold) {
        return Gatherer.of(initializer(), integrator(threshold), combiner(), finisher(threshold));
    }

    static Supplier<List<LogWrapper>> initializer() {
        return ArrayList::new;
    }

    static Gatherer.Integrator<List<LogWrapper>, LogWrapper, String> integrator(final int threshold) {
        return ((internalState, element, downstream) -> {

            if (downstream.isRejecting()) {
                return false;
            }

            if (element.level().equals(LogWrapper.Level.ERROR)) {
                internalState.add(element);
            } else {
                if (internalState.size() >= threshold) {
                    internalState.stream().map(LogWrapper::details).forEach(downstream::push);
                }
                internalState.clear();
            }

            return true;
        });
    }

    static BinaryOperator<List<LogWrapper>> combiner() {
        return (_, _) -> {
            throw new UnsupportedOperationException("Cannot be parallelized");
        };
    }

    static BiConsumer<List<LogWrapper>, Gatherer.Downstream<? super String>> finisher(final int threshold) {
        return (state, downstream) -> {
            if (!downstream.isRejecting() && state.size() >= threshold) {
                state.stream().map(LogWrapper::details).forEach(downstream::push);
            }
        };
    }

}

record LogWrapper(Level level, String details) {

    enum Level {
        INFO,
        DEBUG,
        WARNING,
        ERROR
    }

    @Override
    public String toString() {
        return "LogWrapper{" +
                "level=" + level +
                ", description='" + details + '\'' +
                '}';
    }
}