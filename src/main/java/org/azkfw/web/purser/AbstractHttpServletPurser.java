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
package org.azkfw.web.purser;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.azkfw.lang.LoggingObject;
import org.azkfw.web.WebServiceException;

/**
 * このクラスは、HTTPサーブレットの解析を行うための基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/29
 * @author Kawakicchi
 */
public abstract class AbstractHttpServletPurser extends LoggingObject implements HttpServletPurser {

	/**
	 * コンストラクタ
	 */
	public AbstractHttpServletPurser() {
		super(HttpServletPurser.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractHttpServletPurser(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractHttpServletPurser(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public final void initialize() {
		doInitialize();
	}

	@Override
	public final void destroy() {
		doDestroy();
	}

	@Override
	public final Map<String, Object> purse(final HttpServletRequest aReq, final HttpServletResponse aRes) throws WebServiceException {
		return doPurse(aReq, aRes);
	}

	/**
	 * 初期化処理を行う。
	 */
	protected abstract void doInitialize();

	/**
	 * 解放処理を行う。
	 */
	protected abstract void doDestroy();

	/**
	 * HTTPサーブレットを解析する。
	 * 
	 * @param aReq リクエスト情報
	 * @param aRes レスポンス情報
	 * @return パラメーター
	 * @throws WebServiceException Webサービス層に起因する問題が発生した場合
	 */
	protected abstract Map<String, Object> doPurse(final HttpServletRequest aReq, final HttpServletResponse aRes) throws WebServiceException;

}
