package com.action;

import com.alibaba.fastjson.JSON;
import com.pojo.Dep;
import com.service.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/dep.do")
public class DepAction {

    @Autowired
    private DepService depService ;

    @RequestMapping(params = "p=findall")
    public String findall(HttpServletRequest request){
        List<Dep> list = depService.findall();
        request.setAttribute("list" , list);
        return "show.jsp";
    }

    @RequestMapping(params = "p=delbyid")
    public String delbyid(HttpServletRequest request){
        String depid = request.getParameter("depid");
        int n = depService.delbyid(depid);
        return "redirect:dep.do?p=findall";
    }

    @ResponseBody
    @RequestMapping(params = "p=findajax")
    public String findajax(HttpServletRequest request , HttpServletResponse response){
       response.setContentType("text/html;charset=utf-8");
        List<Dep> list = depService.findall();
        String s = JSON.toJSONString(list);
        System.out.println(s);
        return s;
    }

}
