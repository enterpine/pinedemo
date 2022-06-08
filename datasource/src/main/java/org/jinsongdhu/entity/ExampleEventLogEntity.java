package org.jinsongdhu.entity;

import java.util.Objects;

public class ExampleEventLogEntity {


    private Long eventTime;
    private String dims1;
    private String dims2;
    private String dims3;
    private int target1;

    public Long getEventTime() {
        return eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }

    public String getDims1() {
        return dims1;
    }

    public void setDims1(String dims1) {
        this.dims1 = dims1;
    }

    public String getDims2() {
        return dims2;
    }

    public void setDims2(String dims2) {
        this.dims2 = dims2;
    }

    public String getDims3() {
        return dims3;
    }

    public void setDims3(String dims3) {
        this.dims3 = dims3;
    }

    public int getTarget1() {
        return target1;
    }

    public void setTarget1(int target1) {
        this.target1 = target1;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExampleEventLogEntity that = (ExampleEventLogEntity) o;
        return target1 == that.target1 && Objects.equals(eventTime, that.eventTime) && Objects.equals(dims1, that.dims1) && Objects.equals(dims2, that.dims2) && Objects.equals(dims3, that.dims3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventTime, dims1, dims2, dims3, target1);
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
