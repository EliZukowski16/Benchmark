package org.ssa.ironyard.benchmark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/frameworks")
public class HomeController
{
    @RequestMapping(value = "/")
    public String home()
    {
        return "/benchmarks.html";
    }

}
