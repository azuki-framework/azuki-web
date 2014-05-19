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
package org.azkfw.web.tags.base;

import java.util.Map;

/**
 * このインターフェースは、パラメーター機能のサポートを行うためのインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/31
 * @author Kawakicchi
 */
public interface ParameterTagSupport {

	/**
	 * パラメーターを設定する。
	 * 
	 * @param aKey キー
	 * @param aValue 値
	 */
	public void setParameter(final String aKey, final Object aValue);

	/**
	 * パラメータを設定する。
	 * 
	 * @param aParams パラメータ
	 */
	public void setParameters(final Map<String, Object> aParams);
}
