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
package com.googlecode.jsfFlex.examples.flex.overallExample;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.googlecode.jsfFlex.shared.util.FlexConstants;

/**
 * @author Ji Hoon Kim
 */
@ManagedBean
@SessionScoped
public final class FlexFaceletOverallBean extends FlexOverallBean {
    
    private static final long serialVersionUID = 1940801309822597786L;
    
    private List<LabelBeanEntry> _reallySimpleArray;
    private List<XMLBeanEntry> _xmlBeanEntries;
    
    public FlexFaceletOverallBean(){
        super();
        
        _reallySimpleArray = new LinkedList<LabelBeanEntry>();
        _reallySimpleArray.add(new LabelBeanEntry("First"));
        _reallySimpleArray.add(new LabelBeanEntry("Second"));
        _reallySimpleArray.add(new LabelBeanEntry("Third"));
        
        _xmlBeanEntries = new LinkedList<XMLBeanEntry>();
        _xmlBeanEntries.add(new XMLBeanEntry("menuitem", "SubMenuItem 3-A", "radio", "one", "3A"));
        _xmlBeanEntries.add(new XMLBeanEntry("menuitem", "SubMenuItem 3-B", "radio", "one", "3B"));
        
    }
    
    public List<LabelBeanEntry> getReallySimpleArray() {
        return _reallySimpleArray;
    }
    public void setReallySimpleArray(List<LabelBeanEntry> reallySimpleArray) {
        _reallySimpleArray = reallySimpleArray;
    }
    public List<XMLBeanEntry> getXmlBeanEntries() {
        return _xmlBeanEntries;
    }
    public void setXmlBeanEntries(List<XMLBeanEntry> xmlBeanEntries) {
        _xmlBeanEntries = xmlBeanEntries;
    }
    
    public final static class LabelBeanEntry implements Serializable {
        
        private static final long serialVersionUID = 6328489430684572894L;
        
        private String _label;
        
        public LabelBeanEntry(String label){
            super();
        
            _label = label;
            
        }
        
        public String getLabel() {
            return _label;
        }
        public void setLabel(String label) {
            _label = label;
        }
        
        @Override
        public boolean equals(Object instance) {
            
            if(!(instance instanceof LabelBeanEntry)){
                return false;
            }
            
            LabelBeanEntry labelBeanEntryInstance = LabelBeanEntry.class.cast( instance );
            return _label.equals(labelBeanEntryInstance._label);
        }
        
        @Override
        public int hashCode() {
            return _label.hashCode();
        }
        
    }
    
    public final static class XMLBeanEntry implements Serializable {
        
        private static final long serialVersionUID = 4444816136231378050L;
        
        private String _nodeName;
        private String _label;
        private String _type;
        private String _groupName;
        private String _data;
        
        public XMLBeanEntry(String nodeName, String label, String type, String groupName,
                                String data){
            super();
            
            _nodeName = nodeName;
            _label = label;
            _type = type;
            _groupName = groupName;
            _data = data;
            
        }
        
        public String getData() {
            return _data;
        }
        public void setData(String data) {
            _data = data;
        }
        public String getGroupName() {
            return _groupName;
        }
        public void setGroupName(String groupName) {
            _groupName = groupName;
        }
        public String getLabel() {
            return _label;
        }
        public void setLabel(String label) {
            _label = label;
        }
        public String getNodeName() {
            return _nodeName;
        }
        public void setNodeName(String nodeName) {
            _nodeName = nodeName;
        }
        public String getType() {
            return _type;
        }
        public void setType(String type) {
            _type = type;
        }
        
        @Override
        public boolean equals(Object instance) {
            
            if(!(instance instanceof XMLBeanEntry)){
                return false;
            }
            
            XMLBeanEntry xmlBeanEntryInstance = XMLBeanEntry.class.cast( instance );
            return _data.equals(xmlBeanEntryInstance._data) && _groupName.equals(xmlBeanEntryInstance._groupName) &&
                    _label.equals(xmlBeanEntryInstance._label) && _nodeName.equals(xmlBeanEntryInstance._nodeName) && 
                    _type.equals(xmlBeanEntryInstance._type);
        }
        
        @Override
        public int hashCode() {
            int hashCodeVal = FlexConstants.HASH_CODE_INIT_VALUE;
            hashCodeVal = FlexConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _data.hashCode();
            hashCodeVal = FlexConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _groupName.hashCode();
            hashCodeVal = FlexConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _label.hashCode();
            hashCodeVal = FlexConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _nodeName.hashCode();
            hashCodeVal = FlexConstants.HASH_CODE_MULTIPLY_VALUE * hashCodeVal + _type.hashCode();
            return hashCodeVal;
        }
        
    }
    
}
