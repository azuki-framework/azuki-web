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
package jp.afw.web.tags.logic;

import java.util.List;
import java.util.Map;

/**
 * このクラスは、値が<code>null</code>または空の場合に実装するタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/23
 * @author Kawakicchi
 */
public final class IfEmptyTag extends AbstractValueConditionTag {

	/**
	 * コンストラクタ
	 */
	public IfEmptyTag() {
		super(IfEmptyTag.class);
	}

	@Override
	protected boolean isCondition(final Object aValue) {
		if (null == aValue) {
			return true;
		} else if (aValue instanceof String) {
			return ((String) aValue).isEmpty();
		} else if (aValue instanceof List<?>) {
			return ((List<?>) aValue).isEmpty();
		} else if (aValue instanceof Map<?, ?>) {
			return ((Map<?, ?>) aValue).isEmpty();
		} else {
			return false;
		}
	}
}
