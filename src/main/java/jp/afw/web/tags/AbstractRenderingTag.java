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
package jp.afw.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

/**
 * このクラスは、レンダリング機能を実装したタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public abstract class AbstractRenderingTag extends AbstractTag {

	/**
	 * コンストラクタ
	 */
	public AbstractRenderingTag() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractRenderingTag(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractRenderingTag(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public int doStartTag() throws JspException {
		return (Tag.SKIP_BODY);
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter writer = getPageContext().getOut();
			StringBuffer s = new StringBuffer();
			doRendering(s);
			writer.print(s.toString());
		} catch (IOException ex) {
			throw new JspException(ex);
		}
		return (Tag.EVAL_PAGE);
	}

	/**
	 * レンダリングを行います。
	 * 
	 * @param aRender レンダリング文字列
	 * @throws JspException JSP操作に起因する問題が発生した場合
	 */
	protected abstract void doRendering(final StringBuffer aRender) throws JspException;

	/**
	 * 
	 * @param aString
	 * @return
	 */
	protected static final String toStringEscapeHTML(final String aString) {
		String s = aString;
		return s;
	}
}
