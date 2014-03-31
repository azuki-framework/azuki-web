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
package jp.afw.web.tags.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

/**
 * このクラスは、HTMLタグの拡張を行うためのタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/23
 * @author Kawakicchi
 */
public abstract class AbstractBodyHtmlTag extends AbstractHtmlTag implements BodyTag {

	/**
	 * ボディコンテンツ
	 */
	private BodyContent bodyContent;

	/**
	 * ボディ文字列
	 */
	private String bodyString;

	/**
	 * コンストラクタ
	 */
	public AbstractBodyHtmlTag() {
		super(AbstractBodyHtmlTag.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractBodyHtmlTag(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractBodyHtmlTag(final Class<?> aClass) {
		super(aClass);
	}

	protected final void setBodyString(final String aBody) {
		bodyString = aBody;
	}

	@Override
	public int doStartTag() throws JspException {
		return (BodyTag.EVAL_BODY_BUFFERED);
	}

	@Override
	public int doEndTag() throws JspException {
		doAppendAttributes();

		StringBuffer s = new StringBuffer();
		s.append("<");
		writeTagName(s);
		writeAttributes(s);
		s.append(">");
		writeBody(s);
		s.append("</");
		writeTagName(s);
		s.append(">");
		try {
			JspWriter writer = getPageContext().getOut();
			writer.print(s.toString());
		} catch (IOException ex) {
			throw new JspException(ex);
		}
		return (Tag.EVAL_PAGE);
	}

	protected final void writeBody(final StringBuffer s) {
		s.append(convertTextareaValue(bodyString));
	}

	@Override
	public final void setBodyContent(final BodyContent aBodyContext) {
		bodyContent = aBodyContext;
	}

	@Override
	public final void doInitBody() throws JspException {
	}

	@Override
	public final int doAfterBody() throws JspException {
		bodyString = bodyContent.getString();
		return (Tag.SKIP_BODY);
	}

}
