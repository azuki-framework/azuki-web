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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * このクラスは、HTTPサーブレットパラメーターをそのままパラメーター化するパーサークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/29
 * @author Kawakicchi
 */
public final class DefaultHttpServletPurser extends AbstractHttpServletPurser {

	private String charset = "UTF-8";

	/**
	 * コンストラクタ
	 */
	public DefaultHttpServletPurser() {
		super(DefaultHttpServletPurser.class);
	}

	@Override
	protected void doInitialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doDestroy() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Map<String, Object> doPurse(final HttpServletRequest aReq, final HttpServletResponse aRes) {
		try {
			aReq.setCharacterEncoding(charset);
		} catch (UnsupportedEncodingException ex) {
			error(ex);
		}

		Map<String, Object> parameter = new HashMap<String, Object>();

		for (String key : (Set<String>) aReq.getParameterMap().keySet()) {
			String[] values = aReq.getParameterValues(key);
			if (1 == values.length) {
				parameter.put(key, values[0]);
			} else {
				parameter.put(key, values);
			}
		}

		return parameter;
	}

}
