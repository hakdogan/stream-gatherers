package org.jugistanbul.gatherer;

import java.util.List;

/**
 * @author hakdogan (hakdogan75@gmail.com)
 * Created on 16.03.2025
 ***/
public class Main {

    private List<LogWrapper> logWrappers = List.of(
            new LogWrapper(LogWrapper.Level.ERROR, "Process ID: 191, event details ..."),
            new LogWrapper(LogWrapper.Level.INFO, "Process ID: 216, event details ..."),
            new LogWrapper(LogWrapper.Level.DEBUG, "Process ID: 279, event details ..."),
            new LogWrapper(LogWrapper.Level.ERROR, "Process ID: 312, event details ..."),
            new LogWrapper(LogWrapper.Level.WARNING, "Process ID: 340, event details ..."),
            new LogWrapper(LogWrapper.Level.ERROR, "Process ID: 367, event details ..."),
            new LogWrapper(LogWrapper.Level.ERROR, "Process ID: 389, event details ..."),
            new LogWrapper(LogWrapper.Level.INFO, "Process ID: 401, event details ..."),
            new LogWrapper(LogWrapper.Level.ERROR, "Process ID: 416, event details ..."),
            new LogWrapper(LogWrapper.Level.ERROR, "Process ID: 417, event details ..."),
            new LogWrapper(LogWrapper.Level.ERROR, "Process ID: 418, event details ..."),
            new LogWrapper(LogWrapper.Level.WARNING, "Process ID: 432, event details ..."),
            new LogWrapper(LogWrapper.Level.ERROR, "Process ID: 444, event details ..."),
            new LogWrapper(LogWrapper.Level.ERROR, "Process ID: 445, event details ..."),
            new LogWrapper(LogWrapper.Level.ERROR, "Process ID: 446, event details ..."),
            new LogWrapper(LogWrapper.Level.ERROR, "Process ID: 447, event details ...")
    );

    void main(){

        logWrappers.stream().gather(SuspiciousGatherer.of(3))
                .toList().forEach(System.out::println);
    }
}
