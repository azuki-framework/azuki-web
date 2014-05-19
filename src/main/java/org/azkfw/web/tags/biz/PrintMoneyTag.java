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
package org.azkfw.web.tags.biz;

import javax.servlet.jsp.JspException;

import org.azkfw.core.util.StringUtility;
import org.azkfw.web.tags.AbstractPrintTag;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public final class PrintMoneyTag extends AbstractPrintTag {

	/**
	 * コンストラクタ
	 */
	public PrintMoneyTag() {
		super(PrintMoneyTag.class);
	}

	@Override
	protected String doPrint(Object aValue) throws JspException {
		Long value = null;
		if (null == aValue) {

		} else if (aValue instanceof Short) {
			value = ((Short) aValue).longValue();
		} else if (aValue instanceof Integer) {
			value = ((Integer) aValue).longValue();
		} else if (aValue instanceof Long) {
			value = (Long) aValue;
		}

		if (null != value) {
			String str = String.format("%,3d円", value);
			return str;
		} else {
			return StringUtility.EMPTY;
		}
	}
}
