package com.cgsolutions.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.WelcomeLetter;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.SchoolManager;

public class WelcomeLetterController extends MultiActionController {
	private ChildManager childManager;
	private SchoolManager schoolManager;
	
	public ModelAndView printLetter(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<WelcomeLetterBean> datasource = new ArrayList<WelcomeLetterBean>();
		Child child = childManager.findChild(Integer.parseInt(request.getParameter("childId")));
		child.getGuardians().toString();
		
		WelcomeLetterBean bean = new WelcomeLetterBean();
		bean.setChild(child);
		datasource.add(bean);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("dataSource", datasource);
		model.put("welcomeLetter", new WelcomeLetter());
		model.put("school", schoolManager.find());
		
		return new ModelAndView("WelcomeLetterCompile", model);
	}

	public void setChildManager(ChildManager childManager) {
		this.childManager = childManager;
	}

	public void setSchoolManager(SchoolManager schoolManager) {
		this.schoolManager = schoolManager;
	}
}
