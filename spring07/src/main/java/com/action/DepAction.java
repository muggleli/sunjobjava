package com.action;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mapper.DepMapper;
import com.pojo.Dep;
import com.service.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/dep.do")
public class DepAction {

    @Autowired
    private DepService depService;

    @Autowired
    private HttpServletRequest request ;

    @RequestMapping(params = "p=fenye")
    public String fenye(){
        IPage<Dep> iPage = new Page<Dep>(1,2);
        QueryWrapper queryWrapper = new QueryWrapper();
        IPage page = depService.fenye(iPage,queryWrapper);
        request.setAttribute("page",page);
        return  "show.jsp";
    }
}
