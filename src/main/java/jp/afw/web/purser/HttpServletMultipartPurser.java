package jp.afw.web.purser;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.afw.core.util.FileUtility;
import jp.afw.core.util.PathUtility;
import jp.afw.core.util.UUIDUtility;
import jp.afw.web.WebServiceException;
import jp.afw.web.constant.WebConstant;

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
