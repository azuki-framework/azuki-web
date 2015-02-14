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
package org.azkfw.web.servlet.jsp.tag.html;

import org.azkfw.servlet.jsp.tag.html.AbstractBodyHtmlTag;
import org.azkfw.web.constant.WebConstant;

/**
 * このクラスは、フォームを実装するタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/15
 * @author Kawakicchi
 */
public class FormTag extends AbstractBodyHtmlTag {

	/**
	 * Action
	 */
	private String action = null;

	/**
	 * Method
	 */
	private String method = "get";

	/**
	 * URL absolute
	 */
	private boolean absolute = false;

	public final void setAction(final String aAction) {
		action = aAction;
	}

	protected final String getAction() {
		return action;
	}

	public final void setMethod(final String aMethod) {
		method = aMethod;
	}

	protected final String getMethod() {
		return method;
	}

	public final void setAbsolute(final boolean aAbsolute) {
		absolute = aAbsolute;
	}

	protected final boolean isAbsolute() {
		return absolute;
	}

	@Override
	protected final String getTagName() {
		return "form";
	}

	@Override
	protected void doAppendAttributes() {
		super.doAppendAttributes();

		addAttribute("action", WebConstant.getUrl(getAction(), isAbsolute()));
		addAttribute("method", getMethod());
	}
}
