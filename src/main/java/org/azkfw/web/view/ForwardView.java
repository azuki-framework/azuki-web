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
package org.azkfw.web.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * このクラスは、フォワード機能を備えたビュークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/21
 * @author Kawakicchi
 */
public class ForwardView extends AbstractView {

	/**
	 * url
	 */
	private String url;

	/**
	 * コンストラクタ
	 * 
	 * @param aUrl URL
	 */
	public ForwardView(final String aUrl) {
		url = aUrl;
	}

	@Override
	protected final void doView(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException {
		aReq.getRequestDispatcher(url).forward(aReq, aRes);
	}
}
