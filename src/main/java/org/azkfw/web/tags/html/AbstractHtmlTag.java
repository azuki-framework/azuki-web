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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import org.azkfw.core.util.StringUtility;
import org.azkfw.web.tags.AbstractTag;

/**
 * このクラスは、HTMLタグの拡張を行うためのタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/23
 * @author Kawakicchi
 */
public abstract class AbstractHtmlTag extends AbstractTag {

	/**
	 * タグ属性
	 */
	private Map<String, String> attributes;

	/**
	 * id
	 */
	private String id = null;

	/**
	 * css
	 */
	private String css = null;

	/**
	 * style
	 */
	private String style = null;

	public final void setId(final String aId) {
		id = aId;
	}

	protected final String getId() {
		return id;
	}

	public final void setCss(final String aCss) {
		css = aCss;
	}

	protected final String getCss() {
		return css;
	}

	public final void setStyle(final String aStyle) {
		style = aStyle;
	}

	protected final String getStyle() {
		return style;
	}

	/**
	 * コンストラクタ
	 * 
	 */
	public AbstractHtmlTag() {
		super(AbstractHtmlTag.class);
		attributes = new HashMap<String, String>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractHtmlTag(final String aName) {
		super(aName);
		attributes = new HashMap<String, String>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractHtmlTag(final Class<?> aClass) {
		super(aClass);
		attributes = new HashMap<String, String>();
	}

	/**
	 * タグ属性を追加する。
	 * 
	 * @param aName 属性名
	 * @param aValue 属性値
	 */
	protected final void addAttribute(final String aName, final String aValue) {
		attributes.put(aName, aValue);
	}

	@Override
	public int doStartTag() throws JspException {
		return (Tag.SKIP_BODY);
	}

	@Override
	public int doEndTag() throws JspException {
		doAppendAttributes();

		StringBuffer s = new StringBuffer();
		s.append("<");
		writeTagName(s);
		writeAttributes(s);
		s.append("/>");
		try {
			JspWriter writer = getPageContext().getOut();
			writer.print(s.toString());
		} catch (IOException ex) {
			throw new JspException(ex);
		}
		return (Tag.EVAL_PAGE);
	}

	protected void doAppendAttributes() {
		addAttribute("id", getId());
		addAttribute("class", getCss());
		addAttribute("style", getStyle());
	}

	protected abstract String getTagName();

	protected final void writeTagName(final StringBuffer s) {
		s.append(getTagName());
	}

	protected final void writeAttributes(final StringBuffer s) {
		for (String key : attributes.keySet()) {
			String value = attributes.get(key);
			appendAttribute(key, value, s);
		}
	}

	protected final boolean appendAttribute(final String aKey, final Object aValue, final StringBuffer aString) {
		if (StringUtility.isEmpty(aKey))
			return false;
		if (null == aValue)
			return false;

		aString.append(" ");
		aString.append(aKey);
		aString.append("=\"");
		aString.append(convertAttributeValue(StringUtility.toStringEmpty(aValue)));
		aString.append("\"");
		return true;
	}

	/**
	 * 文字列をタグの属性値用に変換します。
	 * 
	 * @param aValue 文字列
	 * @return 変換後文字列
	 */
	protected final String convertAttributeValue(final String aValue) {
		String value = "";
		if (StringUtility.isNotEmpty(aValue)) {
			value = aValue;
		}
		return value;
	}

	/**
	 * 文字列をテキストエリア用に変換します。
	 * 
	 * @param aValue 文字列
	 * @return 変換後文字列
	 */
	protected final String convertTextareaValue(final String aValue) {
		String value = "";
		if (StringUtility.isNotEmpty(aValue)) {
			value = aValue;
		}
		return value;
	}
}