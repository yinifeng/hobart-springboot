package com.hobart.boot.quartz.enums;

import java.util.stream.Stream;

/**
 * @author hubo
 */
public enum JobStaus {

    RUNNING(1), STOP(0);

    private int status;

    JobStaus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static JobStaus getJobStaus(int status) {
        return Stream.of(JobStaus.values())
                .filter(e -> e.getStatus() == status)
                .findFirst()
                .orElse(JobStaus.STOP);
    }
}
