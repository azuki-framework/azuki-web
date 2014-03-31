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
package jp.afw.web.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.afw.web.constant.WebConstant;

/**
 * このクラスは、リダイレクト機能を備えたビュークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/01
 * @author Kawakicchi
 */
public class RedirectView extends AbstractView {

	/**
	 * url
	 */
	private String url;

	/**
	 * direct
	 */
	private boolean direct;

	/**
	 * コンストラクタ
	 * 
	 * @param aUrl URL
	 */
	public RedirectView(final String aUrl) {
		url = aUrl;
		direct = false;
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aUrl URL
	 * @param aDirect Direct
	 */
	public RedirectView(final String aUrl, final boolean aDirect) {
		url = aUrl;
		direct = aDirect;
	}

	@Override
	protected final void doView(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException {
		String s = null;
		if (direct) {
			s = url;
		} else {
			s = WebConstant.getUrl(url);
		}
		aRes.sendRedirect(s);
	}
}
