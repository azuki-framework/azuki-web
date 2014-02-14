package jp.afw.web.purser;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.afw.web.WebServiceException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public final class HttpServletMultipartPurser extends AbstractHttpServletPurser {

//	private String encoding = "Windows-31J";
	private String encoding = "UTF-8";

	/**
	 * コンストラクタ
	 */
	public HttpServletMultipartPurser() {
		super(HttpServletMultipartPurser.class);
	}

	@Override
	protected Map<String, Object> doPurse(final HttpServletRequest aReq, final HttpServletResponse aRes) throws WebServiceException {
		Map<String, Object> parameter = new HashMap<String, Object>();

		if (ServletFileUpload.isMultipartContent(aReq)) {
			System.out.println("multi");
			String path = "c:\\temp";

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

					System.out.println("name : " + fItem.getFieldName());
					if (fItem.isFormField()) {
						System.out.println("value : " + fItem.getString(encoding));
					} else {
						System.out.println("file : " + fItem.getName());
						String fileName = fItem.getName();
						if ((fileName != null) && (!fileName.equals(""))) {
							fileName = (new File(fileName)).getName();
							fItem.write(new File(path + "/" + fileName));
						}
					}
				}
			} catch (FileUploadException ex) {
				throw new WebServiceException(ex);
			} catch (Exception ex) {
				throw new WebServiceException(ex);
			}
		} else {

		}

		return parameter;
	}
}
