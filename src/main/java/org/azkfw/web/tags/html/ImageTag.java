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
package org.azkfw.web.tags.html;

import org.azkfw.web.constant.WebConstant;

/**
 * このクラスは、URLをレンダリングするタグクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/10/18
 * @author Kawakicchi
 */
public class ImageTag extends AbstractHtmlTag {

	/**
	 * src
	 */
	private String src = null;

	/**
	 * absolute
	 */
	private boolean absolute = false;

	/**
	 * ソースを設定する。
	 * 
	 * @param aSrc ソース
	 */
	public final void setSrc(final String aSrc) {
		src = aSrc;
	}

	/**
	 * ソースを取得する。
	 * 
	 * @return ソース
	 */
	protected final String getSrc() {
		return src;
	}

	/**
	 * 絶対URLかどうかを設定する。
	 * 
	 * @param aAbsolute 絶対URLの場合、<code>true</code>を設定する。
	 */
	public final void setAbsolute(final boolean aAbsolute) {
		absolute = aAbsolute;
	}

	/**
	 * 絶対URLか判断する。
	 * 
	 * @return 絶対URLの場合、<code>true</code>を返す。
	 */
	protected final boolean isAbsolute() {
		return absolute;
	}

	protected String getSource() {
		String source = WebConstant.getUrl(getSrc(), isAbsolute());
		return source;
	}

	@Override
	protected final String getTagName() {
		return "img";
	}

	@Override
	protected void doAppendAttributes() {
		super.doAppendAttributes();

		addAttribute("src", getSource());
	}
}
