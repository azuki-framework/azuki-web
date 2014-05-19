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
package org.azkfw.web.tags.base;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import org.azkfw.web.tags.AbstractTag;

/**
 * このクラスは、パラメータの設定を行うタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/03
 * @author Kawakicchi
 */
public final class ParameterTag extends AbstractTag implements BodyTag {

	/**
	 * パラメータキー
	 */
	private String key;

	/**
	 * パラメータ値
	 */
	private Object value;

	/**
	 * ボディコンテンツ
	 */
	private BodyContent bodyContent;

	/**
	 * コンストラクタ
	 */
	public ParameterTag() {
		super(ParameterTag.class);
	}

	/**
	 * パラメータキーを設定する。
	 * 
	 * @param aKey キー
	 */
	public void setKey(final String aKey) {
		key = aKey;
	}

	/**
	 * パラメータキーを取得する。
	 * 
	 * @return キー
	 */
	protected String getKey() {
		return key;
	}

	/**
	 * パラメータ値を設定する。
	 * 
	 * @param aValue 値
	 */
	public void setValue(final String aValue) {
		value = aValue;
	}

	/**
	 * パラメータ値を取得する。
	 * 
	 * @return 値
	 */
	protected Object getValue() {
		return value;
	}

	@Override
	public final int doStartTag() throws JspException {
		if (null != getValue()) {
			return (Tag.SKIP_BODY);
		} else {
			return (BodyTag.EVAL_BODY_BUFFERED);
		}
	}

	@Override
	public final int doEndTag() throws JspException {
		Tag parent = getParent();
		if (null != parent) {
			if (parent instanceof ParameterTagSupport) {
				ParameterTagSupport t = (ParameterTagSupport) parent;
				t.setParameter(getKey(), getValue());
			}
		}
		return (Tag.EVAL_PAGE);
	}

	@Override
	public void setBodyContent(final BodyContent aBodyContent) {
		bodyContent = aBodyContent;
	}

	@Override
	public void doInitBody() throws JspException {
	}

	@Override
	public int doAfterBody() throws JspException {
		value = bodyContent.getString();
		return (Tag.SKIP_BODY);
	}

}
