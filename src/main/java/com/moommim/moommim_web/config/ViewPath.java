package com.moommim.moommim_web.config;

public class ViewPath {

    public static final String BASE_PAGE = "/pages";

    public static final String BASE_HOME_PAGE = "/home";

    public static final String BASE_LOGIN_PAGE = "/login";
    
    public static final String BASE_USER_PAGE = "/user";

    public static final String INDEX_VIEW = BASE_PAGE + BASE_HOME_PAGE + "/index.jsp";

    public static final String LOGIN_VIEW = BASE_PAGE + BASE_LOGIN_PAGE + "/index.jsp";
    
    public static final String FORGOTPASSWORD_VIEW = BASE_PAGE + BASE_LOGIN_PAGE + "/forgotpassword.jsp";
    
    public static final String ACTIVE_USER_VIEW = BASE_PAGE + BASE_USER_PAGE + "/active_user.jsp";
    
    public static final String CREATE_USER_VIEW = BASE_PAGE + BASE_USER_PAGE + "/create_user.jsp";
    
    public static final String EDIT_USER_VIEW = BASE_PAGE + BASE_USER_PAGE + "/edit_user.jsp";

}
