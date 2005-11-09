package view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.RequestProcessor;

/**
 * @author Nicolas Ricard
 * 
 */
public class WoopsRequestProcessor extends RequestProcessor {

    public WoopsRequestProcessor() {
    }

    /**
     * @see org.apache.struts.action.RequestProcessor#processPreprocess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected boolean processPreprocess(HttpServletRequest request, HttpServletResponse response) {
        boolean ret = true;
        
        return ret;
    }

    
    
}
