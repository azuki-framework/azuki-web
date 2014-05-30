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
package org.azkfw.web.tags.logic;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

/**
 * このクラスは、値が同じ場合にボディが実行されるタグクラスです。
 * 
 * @since 1.0.1
 * @version 1.0.1 2014/05/30
 * @author Kawakicchi
 */
public abstract class AbstractEqualValueTag extends AbstractLogicTag {

	/**
	 * 名前
	 */
	private String srcName;

	/**
	 * キー
	 */
	private String srcKey;

	/**
	 * スコープ
	 */
	private String srcScope;

	/**
	 * 値
	 */
	private String dstValue;

	/**
	 * コンストラクタ
	 */
	public AbstractEqualValueTag() {
		super(AbstractEqualValueTag.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractEqualValueTag(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractEqualValueTag(final Class<?> aClass) {
		super(aClass);
	}

	/**
	 * 名前を設定する。
	 * 
	 * @param aName 名前
	 */
	public final void setSrcName(final String aName) {
		srcName = aName;
	}

	/**
	 * 名前を取得する。
	 * 
	 * @return 名前
	 */
	protected final String getSrcName() {
		return srcName;
	}

	/**
	 * キーを設定する。
	 * 
	 * @param aKey キー
	 */
	public final void setSrcKey(final String aKey) {
		srcKey = aKey;
	}

	/**
	 * キーを取得する。
	 * 
	 * @return キー
	 */
	protected final String getSrcKey() {
		return srcKey;
	}

	/**
	 * スコープを設定する。
	 * 
	 * @param aScope スコープ
	 */
	public final void setSrcScope(final String aScope) {
		srcScope = aScope;
	}

	/**
	 * スコープを取得する。
	 * 
	 * @return スコープ
	 */
	protected final String getSrcScope() {
		return srcScope;
	}

	/**
	 * 値を設定する。
	 * 
	 * @param aValue 値
	 */
	public final void setDstValue(final String aValue) {
		dstValue = aValue;
	}

	/**
	 * 値を取得する。
	 * 
	 * @return 値
	 */
	protected final String getDstValue() {
		return dstValue;
	}

	@Override
	public int doStartTag() throws JspException {
		Object src = getAttribute(getSrcScope(), getSrcName(), getSrcKey());
		String dst = getDstValue();
		if (isEqual(src, dst)) {
			return (Tag.EVAL_BODY_INCLUDE);
		} else {
			return (Tag.SKIP_BODY);
		}
	}

	@Override
	public int doEndTag() throws JspException {
		return (Tag.EVAL_PAGE);
	}

	/**
	 * 値に対する条件を判定する。
	 * 
	 * @param aSrc 値
	 * @param aDst 値
	 * @return 結果
	 */
	protected abstract boolean isEqual(final Object aSrc, final String aDst);
}
