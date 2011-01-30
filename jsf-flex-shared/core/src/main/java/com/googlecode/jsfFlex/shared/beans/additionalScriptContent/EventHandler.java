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
package com.googlecode.jsfFlex.shared.beans.additionalScriptContent;

import org.json.JSONObject;

import com.googlecode.jsfFlex.shared.adapter.IFlexEvent;
import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * @author Ji Hoon Kim
 */
public final class EventHandler {
    
    private static final char DELIM_CHARACTER = '_';
    
    private final String _srcId;
    private final String _tgtId;
    private final String _evtHandlerId;
    private final IFlexEvent.EVENT_HANDLER_TYPE _eventType;
    private final String _eventName;
    private final String _collectedUniqueId;
    private final JSONObject _additionalArguments;
    
    EventHandler(String srcId, String tgtId, String evtHandlerId, IFlexEvent.EVENT_HANDLER_TYPE eventType, 
                        String eventName, JSONObject additionalArguments){
        super();
        _srcId = srcId;
        _tgtId = tgtId;
        _evtHandlerId = evtHandlerId;
        _eventName = eventName;
        _eventType = eventType;
        _additionalArguments = additionalArguments;
        _collectedUniqueId = _srcId + DELIM_CHARACTER + _tgtId + DELIM_CHARACTER + _eventName + DELIM_CHARACTER + _eventType;
    }
    
    public String getActionScriptConstructor(){
        return _eventType.getActionScriptConstructor();
    }
    public String getAdditionalArguments(){
        String argumentContent;
        
        if(_additionalArguments != null){
            argumentContent = _additionalArguments.toString();
        }else{
            argumentContent = JSONObject.NULL.toString();
        }
        
        return argumentContent;
    }
    public String getCollectedUniqueId(){
        return _collectedUniqueId;
    }
    public String getEventName(){
        return _eventName;
    }
    public IFlexEvent.EVENT_HANDLER_TYPE getEventType(){
        return _eventType;
    }
    public String getSrcId(){
        return _srcId;
    }
    public String getTgtId(){
        return _tgtId;
    }
    public String getEventHandlerId(){
        return _evtHandlerId;
    }
    
    @Override
    public boolean equals(Object instance) {
        if(!(instance instanceof EventHandler)){
            return false;
        }
        
        EventHandler evtHandlerInstance = EventHandler.class.cast( instance );
        return _srcId.equals(evtHandlerInstance._srcId) && _tgtId.equals(evtHandlerInstance._tgtId) && _evtHandlerId.equals(evtHandlerInstance._evtHandlerId) &&
                _eventType == evtHandlerInstance._eventType && _eventName.equals(evtHandlerInstance._eventName);
    }
    
    @Override
    public int hashCode() {
        int hashCodeVal = FlexConstants.HASH_CODE_INIT_VALUE;
        hashCodeVal = FlexConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _srcId.hashCode();
        hashCodeVal = FlexConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _tgtId.hashCode();
        hashCodeVal = FlexConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _eventType.toString().hashCode();
        hashCodeVal = FlexConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _eventName.hashCode();
        
        return super.hashCode();
    }
    
}
