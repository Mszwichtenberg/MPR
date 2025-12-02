package Atrapy.Dummy;

import Interfaces.AssignmentConfig;

import java.time.Duration;

public class AssignmentConfigDummy implements AssignmentConfig {


    @Override
    public Duration getDefaultDuration() {
        throw  new UnsupportedOperationException("Dummy config should not be used in this test");
    }
}
