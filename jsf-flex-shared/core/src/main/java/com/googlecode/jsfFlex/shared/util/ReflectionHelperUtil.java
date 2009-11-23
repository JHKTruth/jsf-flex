/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.googlecode.jsfFlex.shared.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * Helper class for performing reflection related tasks
 * 
 * @author Ji Hoon Kim
 */
public final class ReflectionHelperUtil {
    
    private ReflectionHelperUtil(){
        super();
    }
    
    public static Object getValue(Object objectInstance, String methodToInvoke) throws NoSuchMethodException, InvocationTargetException,
                                                                                        IllegalAccessException {
        
        Method method = objectInstance.getClass().getMethod(methodToInvoke);
        Object retVal = method.invoke(objectInstance);
        
        return retVal;
    }
    
    public static Object invokeMethod(Object objectInstance, String methodToInvoke, Class[] parameterTypes, Object[] instanceParameters) 
                                                                                throws NoSuchMethodException, InvocationTargetException, 
                                                                                        IllegalAccessException {
        
        Method method = objectInstance.getClass().getMethod(methodToInvoke, parameterTypes);
        Object retVal = method.invoke(objectInstance, instanceParameters);
        
        return retVal;
    }
    
}
