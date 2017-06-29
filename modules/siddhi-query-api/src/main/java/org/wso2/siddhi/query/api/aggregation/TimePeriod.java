/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.siddhi.query.api.aggregation;

import java.util.ArrayList;
import java.util.List;

/**
 * Time Period API. This defines all the time durations supported in Incremental Aggregation,
 * and the relevant operators
 */
public class TimePeriod {

    /**
     * Durations supported in Incremental Aggregation
     */
    public enum Duration {
        SECONDS, MINUTES, HOURS, DAYS, MONTHS, YEARS
    }

    /**
     * Operators supported in Incremental Aggregation.
     * RANGE operator allows a range of time durations to be defined (e.g. sec ... year)
     * INTERVAL operator allows comma separated time durations to be specified (e.g. sec, month, year)
     */
    public enum Operator {
        RANGE, INTERVAL
    }

    private Operator operator;
    private List<Duration> durations;

    private TimePeriod(Operator operator) {
        this.durations = new ArrayList<>();
        this.operator = operator;
    }

    public Operator getOperator() {
        return this.operator;
    }

    public List<Duration> getDurations() {
        return this.durations;
    }

    // TODO: 5/12/17 check whether names "range", "interval" are ok
    public static TimePeriod range(Duration left, Duration right) { // range sec ... min
        TimePeriod timePeriod = new TimePeriod(Operator.RANGE);
        timePeriod.durations.add(left);
        timePeriod.durations.add(right);
        return timePeriod;
    }

    public static TimePeriod interval(Duration... durations) { // interval sec, min, time
        TimePeriod timePeriod = new TimePeriod(Operator.INTERVAL);
        for (Duration duration : durations) {
            timePeriod.durations.add(duration);
        }
        return timePeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TimePeriod that = (TimePeriod) o;

        if (operator != that.operator) {
            return false;
        }
        return durations != null ? durations.equals(that.durations) : that.durations == null;
    }

    @Override
    public int hashCode() {
        int result = operator.hashCode();
        result = 31 * result + (durations != null ? durations.hashCode() : 0);
        return result;
    }
}
