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

import org.azkfw.servlet.jsp.tag.html.AbstractHtmlTag;
import org.azkfw.web.constant.WebConstant;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class LinkTag extends AbstractHtmlTag {

	/**
	 * rel
	 */
	private String rel = null;

	/**
	 * href
	 */
	private String href = null;

	/**
	 * type
	 */
	private String type = null;

	private boolean absolute = false;

	public final void setRel(final String aRel) {
		rel = aRel;
	}

	public final void setHref(final String aHref) {
		href = aHref;
	}

	public final void setType(final String aType) {
		type = aType;
	}

	protected final String getRel() {
		return rel;
	}

	protected final String getHref() {
		return href;
	}

	protected final String getType() {
		return type;
	}

	public final void setAbsolute(final boolean aAbsolute) {
		absolute = aAbsolute;
	}

	protected final boolean isAbsolute() {
		return absolute;
	}

	@Override
	protected final String getTagName() {
		return "link";
	}

	@Override
	protected void doAppendAttributes() {
		super.doAppendAttributes();

		addAttribute("rel", getRel());
		addAttribute("href", WebConstant.getUrl(getHref(), isAbsolute()));
		addAttribute("type", getType());
	}
}
