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
package org.azkfw.web.servlet.jsp.tag.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.azkfw.servlet.jsp.tag.AbstractBodyRenderingTag;
import org.azkfw.servlet.jsp.tag.base.ParameterTagSupport;
import org.azkfw.util.StringUtility;
import org.azkfw.web.constant.WebConstant;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class UrlTag extends AbstractBodyRenderingTag implements ParameterTagSupport {

	/**
	 * エイリアス
	 */
	private String alias = "";

	/**
	 * 絶対パスフラグ
	 */
	private boolean absolute = false;

	/**
	 * パラメータ情報
	 */
	private Map<String, Object> parameter;

	/**
	 * コンストラクタ
	 */
	public UrlTag() {
		super(UrlTag.class);
		parameter = new HashMap<String, Object>();
	}

	/**
	 * エイリアスを設定する。
	 * 
	 * @param aAlias エイリアス
	 */
	public final void setAlias(final String aAlias) {
		alias = aAlias;
	}

	/**
	 * 絶対URLかどうかを設定する。
	 * 
	 * @param aAbsolute 絶対URLの場合、<code>true</code>を設定する。
	 */
	public final void setAbsolute(final boolean aAbsolute) {
		absolute = aAbsolute;
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
	 * エイリアスを取得する。
	 * 
	 * @return エイリアス
	 */
	protected final String getAlias() {
		return alias;
	}

	/**
	 * 絶対URLか判断する。
	 * 
	 * @return 絶対URLの場合、<code>true</code>を返す。
	 */
	protected final boolean isAbsolute() {
		return absolute;
	}

	@Override
	protected final void doRendering(final StringBuffer aRender) throws JspException {
		String str = WebConstant.getUrl(getAlias(), isAbsolute());
		for (String key : parameter.keySet()) {
			String word = "\\$\\{" + key + "\\}";
			str = str.replaceAll(word, StringUtility.toStringEmpty(parameter.get(key)));
		}

		aRender.append(str);
	}
}
