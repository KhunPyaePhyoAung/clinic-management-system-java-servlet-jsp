package me.khun.clinic.view.jstl;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class ErrorMessageTag extends SimpleTagSupport {

	private static final String TAG = """
			<span class="error-message %s">%s</span>
			""";
	
	private String message;
	
	private String classList;
	
	@Override
	public void doTag() throws JspException, IOException {
		getJspContext().getOut().append(TAG.formatted(classList, message));
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setClassList(String classList) {
		this.classList = classList;
	}
}
