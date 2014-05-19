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
package org.azkfw.web.purser;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.azkfw.core.util.FileUtility;
import org.azkfw.core.util.PathUtility;
import org.azkfw.core.util.UUIDUtility;
import org.azkfw.web.WebServiceException;
import org.azkfw.web.constant.WebConstant;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public final class HttpServletMultipartPurser extends AbstractHttpServletPurser {

	// private String encoding = "Windows-31J";
	private String encoding = "UTF-8";

	private String temporaryDirectory;

	/**
	 * コンストラクタ
	 */
	public HttpServletMultipartPurser() {
		super(HttpServletMultipartPurser.class);
	}

	@Override
	protected void doInitialize() {
		String path = WebConstant.getServletFileUploadTemporaryDirectory();
		temporaryDirectory = PathUtility.cat(path, UUIDUtility.generateToShortString());
		File dir = new File(temporaryDirectory);
		dir.mkdirs();
	}

	@Override
	protected void doDestroy() {
		FileUtility.remove(temporaryDirectory);
	}

	@Override
	protected Map<String, Object> doPurse(final HttpServletRequest aReq, final HttpServletResponse aRes) throws WebServiceException {
		Map<String, Object> parameter = new HashMap<String, Object>();

		if (ServletFileUpload.isMultipartContent(aReq)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			factory.setSizeThreshold(1024);
			upload.setSizeMax(-1);
			upload.setHeaderEncoding(encoding);

			try {
				List<FileItem> list = upload.parseRequest(aReq);

				Iterator<FileItem> iterator = list.iterator();
				while (iterator.hasNext()) {
					FileItem fItem = (FileItem) iterator.next();

					if (fItem.isFormField()) {
						parameter.put(fItem.getFieldName(), fItem.getString(encoding));
					} else {
						String fileName = fItem.getName();
						if ((fileName != null) && (!fileName.equals(""))) {
							fileName = (new File(fileName)).getName();
							String filePath = PathUtility.cat(temporaryDirectory, fileName);
							File file = new File(filePath);
							fItem.write(file);
							parameter.put(fItem.getFieldName(), file);
						}
					}
				}
			} catch (FileUploadException ex) {
				throw new WebServiceException(ex);
			} catch (Exception ex) {
				throw new WebServiceException(ex);
			}

		} else {

			for (String key : (Set<String>) aReq.getParameterMap().keySet()) {
				String[] values = aReq.getParameterValues(key);
				if (1 == values.length) {
					parameter.put(key, values[0]);
				} else {
					parameter.put(key, values);
				}
			}

		}

		return parameter;
	}
}
