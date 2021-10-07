package br.com.fatec.apibackend.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fatec.apibackend.repository.CustomTraceRepository;

@RestController
@CrossOrigin()
@RequestMapping("/tracerouter")
public class Main {

  @Autowired
  CustomTraceRepository trace;

  @GetMapping()
  public String getLista() {
    return "heloo";
  }

  @GetMapping("/chaining")
  public List<HttpTrace> chaining() {
    return trace.findAll();
  }

  @GetMapping("/tracerouter/{IP}")
  public String tracerouter(@PathVariable String IP) {
    return runSystemCommand("tracert " + " 191.242.240.210");
  }



  public static String runSystemCommand(String command) {
    String s = "";
    String resp = "";
    try {
      Process p = Runtime.getRuntime().exec(command);
      BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
      while ((s = inputStream.readLine()) != null) {
        System.out.println(s);
        resp += (s);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return resp;
  }
}
