package com.neo.controller;

import com.neo.combinatorial.CTModel;
import com.neo.combinatorial.TestCase;
import com.neo.combinatorial.TestSuite;
import com.neo.domain.Result;
import com.neo.generator.SA;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class DockerController {

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    // ACTS 3.0 version
    public Result method(HttpServletRequest request) {
        BufferedReader br;
        StringBuilder sb = null;
        String reqBody = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    request.getInputStream()));
            String line = null;
            sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            reqBody = URLDecoder.decode(sb.toString(), "UTF-8");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(reqBody);



        int parameters = (Integer)jsonObject.get("parameter");
        int strength = (Integer)jsonObject.get("strength");
        JSONArray jsonArray = (JSONArray)jsonObject.get("values");
        List valueList = jsonArray.toList();
        int[] values = new int[valueList.size()];
        for(int i = 0; i < values.length; i++) {
            values[i] = (Integer) valueList.get(i);
        }
        jsonArray = (JSONArray)jsonObject.get("constraints");
        List constraintList = jsonArray.toList();
        List<List<String>> constraint = new ArrayList<>();
        constraint.add(Arrays.asList("0/0", "1/0"));         // [0, 0, -, -, -]
        constraint.add(Arrays.asList("2/1", "4/2"));         // [-, -, 1, -, 2]
        constraint.add(Arrays.asList("2/0", "3/0", "4/1"));  // [-, -, 0, 0, 1]
        // run generation algorithm
        // run generation algorithm
        CTModel model = new CTModel(parameters, values, strength, constraint);
        TestSuite ts = new TestSuite();
        SA gen = new SA(false);

        Instant start = Instant.now();
        gen.generation(model, ts);
        Instant end = Instant.now();

        // output: testsuite, size, time
        for (TestCase each : ts.suite) {
            System.out.println(Arrays.toString(each.test));
        }
        System.out.println("size = " + ts.suite.size());
        System.out.println("time = " + Duration.between(start, end).getSeconds());
        ArrayList<int[]> testsuiteArray=new ArrayList<>();
        for (TestCase each : ts.suite) {
            testsuiteArray.add(each.test);
        }



        Result result =new Result(testsuiteArray,ts.getTestSuitetime(),ts.getTestSuiteSize());


        /*
        get parameters from "jsonObject", like "int a = (Integer)jsonObject.get("parameterName")"
        then run your algorithm, and return result
         */
        System.out.println(new JSONObject(result).toString());
        return result;
    }

    /**
     * only for health check
     * @return
     */
    @GetMapping("/check")
    public String healthCheck(){
        return "ok";
    }
}