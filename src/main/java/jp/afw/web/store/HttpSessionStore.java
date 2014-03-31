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
package jp.afw.web.store;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import jp.afw.persistence.store.AbstractStore;

/**
 * このクラスは、HTTPセッション用のストアクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/05
 * @author Kawakicchi
 */
public final class HttpSessionStore extends AbstractStore<String, Object> {

	private static final String SESSION_NAME_USER_AREA = "__USER_AREA__";

	/**
	 * Http session
	 */
	private HttpSession session;

	/**
	 * コンストラクタ
	 * 
	 * @param aSession HttpSession
	 */
	public HttpSessionStore(final HttpSession aSession) {
		session = aSession;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void put(final String aKey, final Object aValue) {
		Object o = session.getAttribute(SESSION_NAME_USER_AREA);
		if (null == o) {
			o = new HashMap<String, Object>();
			session.setAttribute(SESSION_NAME_USER_AREA, o);
		}
		((Map<String, Object>) o).put(aKey, aValue);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putAll(final Map<String, Object> aMap) {
		Object o = session.getAttribute(SESSION_NAME_USER_AREA);
		if (null == o) {
			o = new HashMap<String, Object>();
			session.setAttribute(SESSION_NAME_USER_AREA, o);
		}
		for (String key : aMap.keySet()) {
			((Map<String, Object>) o).put(key, aMap.get(key));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object get(String aKey) {
		Object result = null;
		Object o = session.getAttribute(SESSION_NAME_USER_AREA);
		if (null != o) {
			result = ((Map<String, Object>) o).get(aKey);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object get(final String aKey, final Object aDefault) {
		Object result = aDefault;
		Object o = session.getAttribute(SESSION_NAME_USER_AREA);
		if (null != o) {
			if (null != ((Map<String, Object>) o).get(aKey)) {
				result = ((Map<String, Object>) o).get(aKey);
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean has(final String aKey) {
		Object o = session.getAttribute(SESSION_NAME_USER_AREA);
		if (null != o) {
			Object obj = ((Map<String, Object>) o).get(aKey);
			return (null != obj);
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(final String aKey) {
		Object o = session.getAttribute(SESSION_NAME_USER_AREA);
		if (null != o) {
			((Map<String, Object>) o).remove(aKey);
		}
	}

}
