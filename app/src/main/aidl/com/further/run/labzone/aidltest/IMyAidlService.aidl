// IMyAidlService.aidl
package com.further.run.labzone.aidltest;
import com.further.run.labzone.aidltest.IMyAidlInterface;

// Declare any non-default types here with import statements

interface IMyAidlService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void perform(String aString);
    void register(IMyAidlInterface i);
}
