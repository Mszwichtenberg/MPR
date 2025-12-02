package Atrapy.fix;

import Interfaces.AssignmentConfig;

import java.time.Duration;

public class AssignmentConfigFixed implements AssignmentConfig {
    private final Duration duration;

    public AssignmentConfigFixed(Duration duration) {
        this.duration = duration;
    }

    @Override
    public Duration getDefaultDuration() {
        return duration;
    }
}