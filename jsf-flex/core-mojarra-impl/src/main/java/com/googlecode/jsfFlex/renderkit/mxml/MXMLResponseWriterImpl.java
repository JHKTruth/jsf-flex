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
package com.googlecode.jsfFlex.renderkit.mxml;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Constructor;

import javax.faces.component.UIComponent;
import javax.faces.context.ResponseWriter;

/**
 * @author Ji Hoon Kim
 */
class MXMLResponseWriterImpl extends AbstractMXMLResponseWriter {
    
    private static final String MXML_RESPONSE_WRITER_BASE_IMPL = "com.sun.faces.renderkit.html_basic.HtmlResponseWriter";
    private static final Constructor MXML_RESPONSE_WRITER_BASE_IMPLEMENTOR_CONSTRUCTOR;
    
    private final ResponseWriter _mxmlResponseWriterBaseImplementor;
    
    static{
        
        Class mxmlResponseWriterBaseImplementorClass;
        
        try{
            mxmlResponseWriterBaseImplementorClass = Class.forName(MXML_RESPONSE_WRITER_BASE_IMPL, false, Thread.currentThread().getContextClassLoader());
        }catch(ClassNotFoundException classNotFound){
            throw new RuntimeException("Failure in retrieving the class for " + MXML_RESPONSE_WRITER_BASE_IMPL, classNotFound);
        }
        
        try{
            MXML_RESPONSE_WRITER_BASE_IMPLEMENTOR_CONSTRUCTOR = mxmlResponseWriterBaseImplementorClass.getDeclaredConstructor(new Class[]{Writer.class, String.class, String.class});
        }catch(NoSuchMethodException noSuchMethod){
            throw new RuntimeException("Failure in retrieving the constructor for " +  MXML_RESPONSE_WRITER_BASE_IMPL, noSuchMethod);
        }
        
    }
    
    MXMLResponseWriterImpl(){
        super();
        _mxmlResponseWriterBaseImplementor = null;
    }
    
    MXMLResponseWriterImpl(Writer writer, String contentType, String characterEncoding){
        super();
        
        try{
            _mxmlResponseWriterBaseImplementor = (ResponseWriter) MXML_RESPONSE_WRITER_BASE_IMPLEMENTOR_CONSTRUCTOR.newInstance(new Object[]{writer, contentType, characterEncoding});
        }catch(Exception instantiatingException){
            throw new RuntimeException("Failure in instantiating a class for " + MXML_RESPONSE_WRITER_BASE_IMPL, instantiatingException);
        }
        
    }
    
    public ResponseWriter cloneWithWriter(Writer writer) {
        
        return new MXMLResponseWriterImpl(writer, getContentType(), getCharacterEncoding());
    }
    
    public void endDocument() throws IOException {
        _mxmlResponseWriterBaseImplementor.endDocument();
    }
    
    public void endElement(String name) throws IOException {
        _mxmlResponseWriterBaseImplementor.endElement(name);
    }
    
    public void flush() throws IOException {
        _mxmlResponseWriterBaseImplementor.flush();
    }
    
    public String getCharacterEncoding() {
        return _mxmlResponseWriterBaseImplementor.getCharacterEncoding();
    }
    
    public String getContentType() {
        return _mxmlResponseWriterBaseImplementor.getContentType();
    }
    
    public void startDocument() throws IOException {
        _mxmlResponseWriterBaseImplementor.startDocument();
    }
    
    public void startElement(String name, UIComponent uiComponent) throws IOException {
        _mxmlResponseWriterBaseImplementor.startElement(name, uiComponent);
    }
    
    public void writeAttribute(String name, Object value, String componentPropertyName) throws IOException {
        _mxmlResponseWriterBaseImplementor.writeAttribute(name, value, componentPropertyName);
    }
    
    public void writeComment(Object value) throws IOException {
        _mxmlResponseWriterBaseImplementor.writeComment(value);
    }
    
    public void writeText(Object value, String componentPropertyName) throws IOException {
        _mxmlResponseWriterBaseImplementor.writeText(value, componentPropertyName);
    }
    
    public void writeText(char cbuf[], int off, int len) throws IOException {
        _mxmlResponseWriterBaseImplementor.writeText(cbuf, off, len);
    }
    
    public void writeText(Object text, UIComponent component, String property) throws IOException {
        _mxmlResponseWriterBaseImplementor.writeText(text, component, property);
    }
    
    public void writeURIAttribute(String name, Object value, String componentPropertyName) throws IOException {
        _mxmlResponseWriterBaseImplementor.writeURIAttribute(name, value, componentPropertyName);
    }
    
    public void close() throws IOException {
        _mxmlResponseWriterBaseImplementor.close();
    }
    
    public void write(char cbuf[], int off, int len) throws IOException {
        _mxmlResponseWriterBaseImplementor.write(cbuf, off, len);
    }
    
}
