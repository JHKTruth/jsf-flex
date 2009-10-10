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
package com.googlecode.jsfFlex.validator.ext;

import org.apache.myfaces.buildtools.maven2.plugin.builder.annotation.JSFComponent;

import com.googlecode.jsfFlex.attributes._MXMLUIAllowedFormatCharsAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICardNumberListenerAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICardNumberPropertyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICardNumberSourceAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICardTypeListenerAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICardTypePropertyAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUICardTypeSourceAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIInvalidCharErrorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIInvalidNumberErrorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUINoNumErrorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUINoTypeErrorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIWrongLengthErrorAttribute;
import com.googlecode.jsfFlex.attributes._MXMLUIWrongTypeErrorAttribute;
import com.googlecode.jsfFlex.component.MXMLUISimpleBase;

/**
 * One thing to note about MXML Formatter and Validator is that they are not actually converters or validators<br>
 * respectively but actually are components. This is so because they perform the formatting and validation<br>
 * as Flex components on the client side and not on the server side.<br>
 * 	
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:mxmlCreditCardValidator",
        clazz               =   "com.googlecode.jsfFlex.validator.ext.MXMLUICreditCardValidator",
        type                =   "com.googlecode.jsfFlex.MXMLUICreditCardValidator",
        tagClass            =   "com.googlecode.jsfFlex.taglib.ext.MXMLUICreditCardValidatorTag",
        family              =   "javax.faces.MXMLSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.MXMLCreditCardValidator"
)
public abstract class AbstractMXMLUICreditCardValidator 
						extends MXMLUISimpleBase 
						implements _MXMLUIValidatorAttributes, _MXMLUIAllowedFormatCharsAttribute, _MXMLUICardNumberListenerAttribute, 
                        _MXMLUICardNumberPropertyAttribute, _MXMLUICardNumberSourceAttribute, _MXMLUICardTypeListenerAttribute, 
                        _MXMLUICardTypePropertyAttribute, _MXMLUICardTypeSourceAttribute, _MXMLUIInvalidCharErrorAttribute, 
                        _MXMLUIInvalidNumberErrorAttribute, _MXMLUINoNumErrorAttribute, _MXMLUINoTypeErrorAttribute, 
                        _MXMLUIWrongLengthErrorAttribute, _MXMLUIWrongTypeErrorAttribute {
	
}
