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

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

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
public final class PrintTimeTag extends AbstractPrintTag {

	/**
	 * コンストラクタ
	 */
	public PrintTimeTag() {
		super(PrintTimeTag.class);
	}

	@Override
	protected String doPrint(Object aValue) throws JspException {
		Calendar cln = null;
		if (null == aValue) {

		} else if (aValue instanceof Date) {
			Date dt = (Date) aValue;
			cln = Calendar.getInstance();
			cln.setTimeInMillis(dt.getTime());
		} else if (aValue instanceof java.sql.Date) {
			java.sql.Date dt = (java.sql.Date) aValue;
			cln = Calendar.getInstance();
			cln.setTimeInMillis(dt.getTime());
		} else if (aValue instanceof Timestamp) {
			Timestamp ts = (Timestamp) aValue;
			cln = Calendar.getInstance();
			cln.setTimeInMillis(ts.getTime());
		}

		if (null != cln) {
			String str = String.format("%02d時%02d分%02d秒", cln.get(Calendar.HOUR_OF_DAY), cln.get(Calendar.MINUTE), cln.get(Calendar.SECOND));
			return str;
		} else {
			return StringUtility.EMPTY;
		}
	}
}
