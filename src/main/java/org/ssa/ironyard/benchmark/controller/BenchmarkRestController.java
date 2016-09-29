package org.ssa.ironyard.benchmark.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.ssa.ironyard.benchmark.model.Benchmark;
import org.ssa.ironyard.benchmark.service.BenchmarkService;

@RestController
@RequestMapping("/frameworks")
public class BenchmarkRestController
{
    @Autowired
    BenchmarkService benchmarkService;
    
    @RequestMapping(value = "/benchmarks", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Benchmark>> allBenchmarks()
    {
        return ResponseEntity.ok(benchmarkService.getAllBenchmarks());
    }
    
    @RequestMapping(value = "/benchmarks/language", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Benchmark>> benchmarksByLanguage(HttpServletRequest request)
    {
        //TODO: implementation
        return null;
    }
    
    @RequestMapping(value = "/benchmarks/server", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Benchmark>> benchmarksByServer(HttpServletRequest request)
    {
        String[] servers = request.getParameterValues("servers");
        
        
        
        //TODO: implementation
        return null;
    }
    
    @RequestMapping(value = "/benchmarks/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Benchmark> benchmarksByID(@PathVariable String id)
    {
        Integer benchmarkID = Integer.parseInt(id);
        
        Benchmark benchmark = benchmarkService.getBenchmarkByBenchmarkID(benchmarkID);
        
        return ResponseEntity.ok(benchmark);
    }

}
