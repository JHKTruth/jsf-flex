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

import com.googlecode.jsfFlex.attributes.IFlexUIAllowedFormatCharsAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUICardNumberListenerAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUICardNumberPropertyAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUICardNumberSourceAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUICardTypeListenerAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUICardTypePropertyAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUICardTypeSourceAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIInvalidCharErrorAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIInvalidNumberErrorAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUINoNumErrorAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUINoTypeErrorAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIWrongLengthErrorAttribute;
import com.googlecode.jsfFlex.attributes.IFlexUIWrongTypeErrorAttribute;
import com.googlecode.jsfFlex.component.AbstractFlexUISimpleBase;

/**
 * One thing to note about Flex Formatter and Validator is that they are not actually converters or validators<br>
 * respectively but actually are components. This is so because they perform the formatting and validation<br>
 * as Flex components on the client side and not on the server side.<br>
 * 	
 * @author Ji Hoon Kim
 */
@JSFComponent(
        name                =   "jf:flexCreditCardValidator",
        clazz               =   "com.googlecode.jsfFlex.validator.ext.FlexUICreditCardValidator",
        type                =   "com.googlecode.jsfFlex.FlexUICreditCardValidator",
        tagClass            =   "com.googlecode.jsfFlex.taglib.validator.ext.FlexUICreditCardValidatorTag",
        family              =   "javax.faces.FlexSimple",
        defaultRendererType =   "com.googlecode.jsfFlex.FlexCreditCardValidator"
)
public abstract class AbstractFlexUICreditCardValidator 
						extends AbstractFlexUISimpleBase 
						implements IFlexUIValidatorAttributes, IFlexUIAllowedFormatCharsAttribute, IFlexUICardNumberListenerAttribute, 
                        IFlexUICardNumberPropertyAttribute, IFlexUICardNumberSourceAttribute, IFlexUICardTypeListenerAttribute, 
                        IFlexUICardTypePropertyAttribute, IFlexUICardTypeSourceAttribute, IFlexUIInvalidCharErrorAttribute, 
                        IFlexUIInvalidNumberErrorAttribute, IFlexUINoNumErrorAttribute, IFlexUINoTypeErrorAttribute, 
                        IFlexUIWrongLengthErrorAttribute, IFlexUIWrongTypeErrorAttribute {
	
}