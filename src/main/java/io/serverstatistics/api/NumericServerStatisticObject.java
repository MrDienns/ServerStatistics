package io.serverstatistics.api;

/**
 * An interface which can be implemented if your custom statistical object, used for profiling, represents it's own
 * numeric value. For example, a list of donation objects, profiled by each configured tag, can still be monitored on
 * the value of the donation.
 */
public interface NumericServerStatisticObject {

    /**
     * The numeric value of this statistical object.
     * @return double
     */
    double getNumericValue();
}
