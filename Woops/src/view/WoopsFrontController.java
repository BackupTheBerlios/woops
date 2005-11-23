package view;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionServlet;

import com.cc.framework.ui.painter.PainterFactory;
import com.cc.framework.ui.painter.def.DefPainterFactory;
import com.cc.framework.ui.painter.html.HtmlPainterFactory;
  
  public class WoopsFrontController extends ActionServlet { 
  	public void init() throws ServletException { 
  	super.init(); 
  	/* Autorisation de l'internationalisation */ 
  	getServletContext().setAttribute( com.cc.framework.Globals.LOCALENAME_KEY, "true");
  	PainterFactory.registerApplicationPainter ( getServletContext (), DefPainterFactory.instance());
  	PainterFactory.registerApplicationPainter ( getServletContext (), HtmlPainterFactory.instance()); 
  	} 
  } 