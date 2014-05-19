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
 * このクラスは、条件制御を行うためのタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/23
 * @author Kawakicchi
 */
public abstract class AbstractValueEqualTag extends AbstractLogicTag {

	/**
	 * 名前
	 */
	private String srcName;

	/**
	 * 名前
	 */
	private String dstName;

	/**
	 * キー
	 */
	private String srcKey;

	/**
	 * キー
	 */
	private String dstKey;

	/**
	 * スコープ
	 */
	private String srcScope;

	/**
	 * スコープ
	 */
	private String dstScope;

	/**
	 * コンストラクタ
	 */
	public AbstractValueEqualTag() {
		super(AbstractValueEqualTag.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractValueEqualTag(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractValueEqualTag(final Class<?> aClass) {
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
	 * 名前を設定する。
	 * 
	 * @param aName 名前
	 */
	public final void setDstName(final String aName) {
		dstName = aName;
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
	 * 名前を取得する。
	 * 
	 * @return 名前
	 */
	protected final String getDstName() {
		return dstName;
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
	 * キーを設定する。
	 * 
	 * @param aKey キー
	 */
	public final void setDstKey(final String aKey) {
		dstKey = aKey;
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
	 * キーを取得する。
	 * 
	 * @return キー
	 */
	protected final String getDstKey() {
		return dstKey;
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
	 * スコープを設定する。
	 * 
	 * @param aScope スコープ
	 */
	public final void setDstScope(final String aScope) {
		dstScope = aScope;
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
	 * スコープを取得する。
	 * 
	 * @return スコープ
	 */
	protected final String getDstScope() {
		return dstScope;
	}

	@Override
	public int doStartTag() throws JspException {
		Object src = getAttribute(getSrcScope(), getSrcName(), getSrcKey());
		Object dst = getAttribute(getDstScope(), getDstName(), getDstKey());
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
	protected abstract boolean isEqual(final Object aSrc, final Object aDst);
}
