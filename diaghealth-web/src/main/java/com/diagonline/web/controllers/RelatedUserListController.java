package com.diagonline.web.controllers;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.portable.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.diagonline.nodes.user.UserDetails;
import com.diagonline.services.SearchService;
import com.diagonline.services.UserRegisterService;
import com.diagonline.utils.UserType;
import com.diagonline.web.utils.ModelBuilder;
import com.diagonline.web.utils.SessionUtil;

@Controller
public class RelatedUserListController {
	
	@Autowired
    private SessionUtil sessionUtil;
	@Autowired
	private SearchService searchService;
	@Autowired
	private UserRegisterService userRegisterService;
	@Value("${show.userlist.jsp}")
	private String USERS_SHOW_JSP;
	@Value("${user.list.attr}")
	private String USER_LIST_ATTR;
	@Value("${user.history.attr}")
	private String USER_HISTORY_ATTR;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/related", method = RequestMethod.GET)
	public ModelAndView searchPage(@RequestParam String type, HttpServletRequest httpServletRequest, ModelAndView mv /*ModelMap model*/) throws ApplicationException {
		
		UserDetails loggedInUser = sessionUtil.getLoggedInUser(httpServletRequest);
		Set<UserDetails> relatedUsers = searchService.searchRelatedUsers(loggedInUser.getId(), UserType.valueOf(type.toUpperCase()));
		
		new ModelBuilder().buildUserListModel(loggedInUser, mv, UserType.valueOf(type.toUpperCase()), relatedUsers);
		mv.setViewName(USERS_SHOW_JSP);
		return mv;
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView addUserToRelation(@RequestParam Long id, @RequestParam String type, HttpServletRequest httpServletRequest, ModelAndView mv /*ModelMap model*/) throws ApplicationException {
		UserDetails loggedInUser = sessionUtil.getLoggedInUser(httpServletRequest);		
		boolean success = userRegisterService.saveRelationshipNode(loggedInUser, id, UserType.valueOf(type));
		
		String displayPage = USERS_SHOW_JSP;
		if(success){
			displayPage = "redirect:" + "/related?type=" + type;
		} else {
			logger.error("Cannot add relationship between " + loggedInUser.getUsername() + " and id: " + id + " type: " + type);
		}
		logger.info("Created Realtionship between " + loggedInUser.getUsername() + " and id: " + id + " type: " + type);
		mv.setViewName(displayPage);
		return mv;
	}
	
	
		
}
