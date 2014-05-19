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
package org.azkfw.web.action.urlmapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.azkfw.core.lang.LoggingObject;
import org.azkfw.web.action.Action;

/**
 * このクラスは、URLとアクションのマッピング機能の実装を行うための基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/17
 * @author Kawakicchi
 */
public abstract class AbstractUrlActionMapper extends LoggingObject implements UrlActionMapper {

	/**
	 * コンストラクタ
	 */
	public AbstractUrlActionMapper() {
		super(UrlActionMapper.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractUrlActionMapper(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractUrlActionMapper(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public final Action mapping(final HttpServletRequest aReq, final HttpServletResponse aRes) {
		return doMapping(aReq, aRes);
	}

	/**
	 * URLマッピングを行う。
	 * 
	 * @param aReq リクエスト情報
	 * @param aRes レスポンス情報
	 * @return アクション。マッピング出来ない場合、<code>null</null>を返す。
	 */
	protected abstract Action doMapping(final HttpServletRequest aReq, final HttpServletResponse aRes);

}
