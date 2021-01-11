package com.example.demo;

import com.example.demo.rootService.RootCreator;
import com.example.demo.jsonObjects.Folder;
import com.example.demo.jsonObjects.Root;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(DemoApplication.class, args);


        Root root = RootCreator.createRoot();

        Map<String, Folder> map = root.getRootMap();

        String id = "//test-path/CloudUX1/proj1/proj1 Bin.avb";

        String url = URLEncoder.encode(id, "UTF-8");

        String id_decoded = "%2F%2Ftest-path%2FCloudUX1%2FSharedProjects%2FUHD_23_976p%2FcreateTest.avb";
        String id_uncode = URLEncoder.encode(id_decoded, "UTF-8");

        //Folder folder = map.get(id);
        System.out.println(url);

    }


}
