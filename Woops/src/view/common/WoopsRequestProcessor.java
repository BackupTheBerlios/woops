package view.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import view.PresentationConstantes;

import com.cc.framework.adapter.struts.FWRequestProcessor;

public class WoopsRequestProcessor extends FWRequestProcessor {

	/**
	 * Constructeur
	 */
	public WoopsRequestProcessor() {
		super();
	}

	protected boolean processRoles(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) throws IOException, ServletException {
		try {
			return super.processRoles(request, response, mapping);
		} catch (ServletException se) {
			/* L'utilisateur n'a pas les permissions pour accéder à la page, il est déconnecté de l'application */
			ActionForward forward = mapping.findForward(PresentationConstantes.FORWARD_LOGOUT);
			request.getRequestDispatcher(forward.getPath()).forward(request, response);
			return false;
		}
	}
}
