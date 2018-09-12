package com.cultivation.javaBasic.showYourIntelligence;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class StackFrameHelper {
    public static String getCurrentMethodName() {
        // TODO: please modify the following code to pass the test
        // <--start
        String callerClassName = new Exception().getStackTrace()[1].getClassName();
        callerClassName += "." + new Exception().getStackTrace()[1].getMethodName();
        return callerClassName;
//        throw new NotImplementedException();
        // --end-->
    }
}
