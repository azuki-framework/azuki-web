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
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * このクラスは、JSP表示機能を備えたビュークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/06/26
 * @author Kawakicchi
 */
public final class JSPView extends AbstractView {

	/**
	 * Character encoding
	 */
	private String characterEncoding;

	/**
	 * Base path
	 */
	private String basePath;

	/**
	 * JSP path
	 */
	private String path;

	/**
	 * Attributes
	 */
	private Map<String, Object> attributes;

	/**
	 * Constructor
	 * 
	 * @param aJspPath JSP path
	 */
	public JSPView(final String aJspPath) {
		characterEncoding = "UTF-8";
		basePath = "/WEB-INF/jsp";
		path = aJspPath;
		attributes = null;
	}

	/**
	 * Constructor
	 * 
	 * @param aJspPath JSP path
	 * @param aAttributes Attributes
	 */
	public JSPView(final String aJspPath, final Map<String, Object> aAttributes) {
		characterEncoding = "UTF-8";
		basePath = "/WEB-INF/jsp";
		path = aJspPath;
		attributes = new HashMap<String, Object>(aAttributes);
	}

	public void setCharacterEncoding(final String aEncoding) {
		characterEncoding = aEncoding;
	}

	public void setBasePath(final String aPath) {
		basePath = aPath;
	}

	@Override
	protected final void doView(final HttpServletRequest aReq, final HttpServletResponse aRes) throws ServletException, IOException {
		aRes.setCharacterEncoding(characterEncoding);

		if (null != attributes) {
			for (String key : attributes.keySet()) {
				aReq.setAttribute(key, attributes.get(key));
			}
		}

		StringBuffer s = new StringBuffer(basePath);
		if (!path.startsWith("/")) {
			s.append("/");
		}
		s.append(path);

		RequestDispatcher rd = aReq.getRequestDispatcher(s.toString());
		rd.forward(aReq, aRes);
	}

}
