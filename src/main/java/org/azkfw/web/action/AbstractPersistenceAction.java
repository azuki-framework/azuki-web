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

import org.azkfw.business.property.Property;
import org.azkfw.business.property.PropertySupport;
import org.azkfw.context.Context;
import org.azkfw.context.ContextSupport;
import org.azkfw.parameter.Parameter;
import org.azkfw.parameter.ParameterSupport;
import org.azkfw.persistence.session.SessionSupport;
import org.azkfw.store.Store;

/**
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/21
 * @author Kawakicchi
 */
public abstract class AbstractPersistenceAction extends AbstractAction implements SessionSupport, ContextSupport, PropertySupport, ParameterSupport {

	/**
	 * Session store
	 */
	private Store<String, Object> session;

	/**
	 * コンテキスト
	 */
	private Context context;

	/**
	 * プロパティ情報
	 */
	private Property property;

	/**
	 * パラメータ情報
	 */
	private Parameter parameter;

	/**
	 * コンストラクタ
	 */
	public AbstractPersistenceAction() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractPersistenceAction(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractPersistenceAction(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public final void setSession(final Store<String, Object> aSession) {
		session = aSession;
	}

	/**
	 * セッション情報を取得する。
	 * 
	 * @return セッション情報
	 */
	protected final Store<String, Object> getSession() {
		return session;
	}

	@Override
	public final void setContext(final Context aContext) {
		context = aContext;
	}

	/**
	 * コンテキストを取得する。
	 * 
	 * @return コンテキスト
	 */
	protected final Context getContext() {
		return context;
	}

	@Override
	public final void setProperty(final Property aProperty) {
		property = aProperty;
	}

	/**
	 * プロパティ情報を取得する。
	 * 
	 * @return プロパティ情報
	 */
	protected final Property getProperty() {
		return property;
	}

	@Override
	public final void setParameter(final Parameter aParameter) {
		parameter = aParameter;
	}

	/**
	 * パラメータ情報を取得する。
	 * 
	 * @return パラメータ情報
	 */
	protected final Parameter getParameter() {
		return parameter;
	}
}
