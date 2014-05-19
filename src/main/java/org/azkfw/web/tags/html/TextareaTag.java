/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.azkfw.web.tags.html;

import org.azkfw.core.util.StringUtility;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class TextareaTag extends AbstractBodyHtmlTag {

	/**
	 * name
	 */
	private String name = null;

	public final void setName(final String aName) {
		name = aName;
	}

	protected final String getName() {
		return name;
	}

	@Override
	protected final String getTagName() {
		return "textarea";
	}

	@Override
	protected void doAppendAttributes() {
		super.doAppendAttributes();

		Object value = null;
		if (StringUtility.isNotEmpty(getName())) {
			value = getRequestAttribute(getName());
		}

		addAttribute("name", getName());

		setBodyString(StringUtility.toStringEmpty(value));
	}
}
