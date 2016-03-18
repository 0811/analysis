package com.dempe.analysis.manger.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.dempe.analysis.manger.system.model.User;
import com.dempe.analysis.manger.system.service.UserService;
import com.dempe.analysis.manger.utils.JsonResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/16
 * Time: 14:01
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @Autowired
    @Qualifier("org.springframework.security.authenticationManager")
    protected AuthenticationManager authenticationManager;

    @RequestMapping("index")
    public String index(Model model) {
        List<User> userList = userService.listUser();
        for (User user : userList) {
            if (user.getRoleId() == User.RoleType.ROLE_ADMIN.value()) {
                user.setRoleName("管理员");
            } else if (user.getRoleId() == User.RoleType.ROLE_MANGER.value()) {
                user.setRoleName("超级管理员");
            } else if (user.getRoleId() == User.RoleType.ROLE_USER.value()) {
                user.setRoleName("普通用户");
            }
        }
        model.addAttribute("userList", userList);
        return "/user/index";
    }

    @RequestMapping("/new")
    public String newUser() {
        return "/user/newUser";
    }

    @RequestMapping("/getUserByUid")
    @ResponseBody
    public User getUserByUid(@RequestParam String id) {
        return userService.findByUid(id);
    }

    @RequestMapping("/del")
    @ResponseBody
    public JSONObject delUser(@RequestParam String id) {
        userService.delUser(id);
        return JsonResult.getJsonResult();
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request, @ModelAttribute User user) {
        user.setRoleId(1);
        userService.saveUser(user);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getName(), user.getPwd());
        //request.getSession();
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        return "redirect:/analysis/index";
    }


    @RequestMapping("/update")
    public String update(@ModelAttribute User user) {
        if (StringUtils.isNotBlank(user.getUid())) {
            userService.updateUser(user);
        }
        return "redirect:/user/index";
    }

}
