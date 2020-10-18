package com.action;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pojo.Dep;
import com.service.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/dep.do")
public class DepAction {

    @Autowired
    private HttpServletRequest request ;

    @Autowired
    private DepService depService ;

    @RequestMapping(params = "p=fenye")
    public String findall(){
        IPage<Dep> ipage = depService.findall();
        request.setAttribute("ipage" , ipage);
        return "show.jsp";
    }


}
