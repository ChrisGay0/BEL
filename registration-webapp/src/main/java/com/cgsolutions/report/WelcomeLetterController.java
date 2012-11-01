package com.cgsolutions.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.cgsolutions.registration.domain.Child;
import com.cgsolutions.registration.domain.School;
import com.cgsolutions.registration.domain.WelcomeLetter;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.SchoolManager;

public class WelcomeLetterController extends MultiActionController {
	private ChildManager childManager;
	private SchoolManager schoolManager;
	
	public ModelAndView printLetter(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<WelcomeLetterBean> datasource = new ArrayList<WelcomeLetterBean>();
		School school = schoolManager.find();
		if(StringUtils.hasText(request.getParameter("childId"))){
			Child child = childManager.findChild(Integer.parseInt(request.getParameter("childId")));
			
			datasource.add(generateBean(child));
		}
		else{
			List<Child> children = childManager.findChildrenNeedingWelcomeLetters();
			if(!CollectionUtils.isEmpty(children)){
				for(Child child: children){
					datasource.add(generateBean(child));
				}
			}
		}
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("dataSource", datasource);
		model.put("welcomeLetter", new WelcomeLetter(school));
		model.put("school", school);
		
		return new ModelAndView("WelcomeLetterCompile", model);
	}
	
	private WelcomeLetterBean generateBean(Child child){
		child.setWelcomeLetterPrinted(true);
		childManager.saveChild(child);
		
		WelcomeLetterBean bean = new WelcomeLetterBean();
		bean.setChild(child);
		
		return bean;
	}

	public void setChildManager(ChildManager childManager) {
		this.childManager = childManager;
	}

	public void setSchoolManager(SchoolManager schoolManager) {
		this.schoolManager = schoolManager;
	}
}
