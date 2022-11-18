package me.khun.clinic.view.jstl;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class AlertMessageTag extends SimpleTagSupport {

	private static final String TAG = """
			<span class="alert d-inline-block %s">
				%s
				<span class="btn-alert-close font-size-l ml-2">
					<i class="fa-solid fa-xmark"></i>
				</span>
			</span>
			""";
	
	private String message;
	
	private String type = "alert-default";
	
	@Override
	public void doTag() throws JspException, IOException {
		getJspContext().getOut().append(TAG.formatted(type, message));
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setType(String type) {
		this.type = switch (type) {
			case "success" -> "alert-success";
			case "error" -> "alert-error";
			case "warning" -> "alert-warning";
			case "info" -> "alert-info";
			default -> "alert-default";
		};
	}
}
