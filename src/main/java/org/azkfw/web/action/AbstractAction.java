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
package org.azkfw.web.action;

import org.azkfw.log.LoggingObject;
import org.azkfw.web.WebServiceException;
import org.azkfw.web.view.View;

/**
 * このクラスは、アクション機能の実装を行うための基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/12/30
 * @author Kawakicchi
 */
public abstract class AbstractAction extends LoggingObject implements Action {

	/**
	 * コンストラクタ
	 */
	public AbstractAction() {
		super(Action.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractAction(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractAction(final Class<?> aClass) {
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
	public final View action() throws WebServiceException {
		View view;
		view = doAction();
		return view;
	}

	/**
	 * 初期か処理を行う。
	 */
	protected abstract void doInitialize();

	/**
	 * アクション実行直前の処理を行う。
	 * <p>
	 * アクション実行直前に処理を行いたい場合、このメソッドをオーバーライドしスーパークラスの同メソッドを呼び出した後で処理を記述すること。
	 * </p>
	 */
	protected void doBeforeAction() {

	}

	/**
	 * アクション実行直後の処理を行う。
	 * <p>
	 * アクション実行直後に処理を行いたい場合、このメソッドをオーバーライドしスーパークラスの同メソッドを呼び出した後で処理を記述すること。
	 * </p>
	 */
	protected void doAfterAction() {

	}

	/**
	 * 解放処理を行う。
	 */
	protected abstract void doDestroy();

	/**
	 * アクションを実行する。
	 * 
	 * @return ビュー
	 * @throws WebServiceException ウェブサービス層に起因する問題が発生した場合
	 */
	protected abstract View doAction() throws WebServiceException;

}
