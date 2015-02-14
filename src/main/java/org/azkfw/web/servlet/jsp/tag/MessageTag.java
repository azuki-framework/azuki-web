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
package org.azkfw.web.servlet.jsp.tag;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.azkfw.servlet.jsp.tag.AbstractBodyRenderingTag;
import org.azkfw.servlet.jsp.tag.base.ParameterTagSupport;

import org.azkfw.business.message.Message;
import org.azkfw.business.message.MessageFactory;
import org.azkfw.util.StringUtility;

/**
 * このクラスは、メッセージ表示を行うタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/16
 * @author Kawakicchi
 */
public class MessageTag extends AbstractBodyRenderingTag implements ParameterTagSupport {

	/**
	 * 名前空間
	 */
	private String namespace;

	/**
	 * メッセージ名
	 */
	private String name;

	/**
	 * パラメータ情報
	 */
	private Map<String, Object> parameter;

	/**
	 * コンストラクタ
	 */
	public MessageTag() {
		super(MessageTag.class);
		parameter = new HashMap<String, Object>();
	}

	/**
	 * 名前空間を設定する。
	 * 
	 * @param aNamespace 名前空間
	 */
	public final void setNamespace(final String aNamespace) {
		namespace = aNamespace;
	}

	/**
	 * メッセージ名を設定する。
	 * 
	 * @param aName メッセージ名
	 */
	public final void setName(final String aName) {
		name = aName;
	}

	@Override
	public final void setParameter(final String aKey, final Object aValue) {
		parameter.put(aKey, aValue);
	}

	@Override
	public final void setParameters(final Map<String, Object> aParams) {
		parameter.putAll(aParams);
	}

	/**
	 * 名前空間を取得する。
	 * 
	 * @return 名前空間
	 */
	protected final String getNamespace() {
		return namespace;
	}

	/**
	 * メッセージ名を取得する。
	 * 
	 * @return メッセージ名
	 */
	protected final String getName() {
		return name;
	}

	@Override
	protected void doRendering(final StringBuffer aReader) throws JspException {
		String ns = getNamespace();
		String nm = getName();

		Message msg = null;
		if (StringUtility.isNotEmpty(ns)) {
			msg = MessageFactory.create(ns, nm);
		} else {
			msg = MessageFactory.create(nm);
		}

		if (null != msg) {
			String str = msg.generate(parameter);
			aReader.append(toStringEscapeHTML(str));
		}
	}

}
