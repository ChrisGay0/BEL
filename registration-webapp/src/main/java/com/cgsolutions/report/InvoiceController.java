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
import com.cgsolutions.registration.domain.Term;
import com.cgsolutions.registration.domain.TermBill;
import com.cgsolutions.registration.service.AttendanceManager;
import com.cgsolutions.registration.service.BillManager;
import com.cgsolutions.registration.service.ChildManager;
import com.cgsolutions.registration.service.SchoolManager;
import com.cgsolutions.registration.service.TermManager;

public class InvoiceController extends MultiActionController {
		private AttendanceManager attendanceManager;
		private TermManager termManager;
		private SchoolManager schoolManager;
		private BillManager billManager;
		private ChildManager childManager;
		
		public ModelAndView printInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {
			List<InvoiceBean> datasource = new ArrayList<InvoiceBean>();
			School school = schoolManager.find();
			Term term;
			if(StringUtils.hasText(request.getParameter("childId"))){
				Child child = childManager.findChild(Integer.parseInt(request.getParameter("childId")));
				term = termManager.findCurrentTerm();
				
				if(term != null){
					datasource.add(getBeanForChild(child, term));
				}
			}
			else{
				term = termManager.find(Integer.parseInt(request.getParameter("termId")));
				List<Child> children = attendanceManager.findChildrenForTerm(term);
				
				if(!CollectionUtils.isEmpty(children)){
					for(Child child: children){
						datasource.add(getBeanForChild(child, term));
					}
				}
			}
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("school", school);
			model.put("term", term);
			model.put("dataSource", datasource);
			
			return new ModelAndView("InvoiceCompile", model);
		}

		private InvoiceBean getBeanForChild(Child child, Term term){
			child.setBills(billManager.findBillsForChild(child));
			InvoiceBean bean = new InvoiceBean();
			bean.setChild(child);
			for(TermBill bill: child.getBills()){
				if(bill.getTerm().getId() == term.getId()){
					bean.setTotalLunches(bill.getLunches());
					bean.setTotalSessions(bill.getSessions());
					bean.setSessionCost(bill.getSessionsCost());
					bean.setLunchCost(bill.getLunchesCost());
				}
			}
			//Current balance shouldn't include this terms cost
			bean.setCurrentBalance(Float.parseFloat(child.getCurrentBalance()) + (bean.getTotalLunchCost() + bean.getTotalSessionCost()));
		
			return bean;
		}
		
		public void setAttendanceManager(AttendanceManager attendanceManager) {
			this.attendanceManager = attendanceManager;
		}

		public void setTermManager(TermManager termManager) {
			this.termManager = termManager;
		}

		public void setSchoolManager(SchoolManager schoolManager) {
			this.schoolManager = schoolManager;
		}

		public void setBillManager(BillManager billManager) {
			this.billManager = billManager;
		}

		public void setChildManager(ChildManager childManager) {
			this.childManager = childManager;
		}
}