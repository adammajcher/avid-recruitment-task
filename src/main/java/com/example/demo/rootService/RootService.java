package com.example.demo.rootService;

import com.example.demo.resourceNotFoundException.ResourceNotFoundException;
import com.example.demo.jsonObjects.Folder;
import com.example.demo.jsonObjects.Result;
import com.example.demo.jsonObjects.Root;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RootService {

    private Root root;
    private Map<String, List<Result>> resultsMap = new HashMap<>();

    public RootService() {
        try {
            root = RootCreator.createRoot();
            Map<String, Folder> map = root.getRootMap();

            List<Result> results = new ArrayList<>();

            for (Map.Entry<String, Folder> pair : map.entrySet()) {
                Result result = new Result(pair.getValue().getId(), pair.getKey());
                results.add(result);
            }

            resultsMap.put("results", results);

        } catch (Exception e) {
            System.out.println("Service couldn't load root (data.json file).");
        }
    }

    public Map<String, List<Result>> getResultsMap() {
        return resultsMap;
    }

    public Folder getFolder(String folderId) {
        List<Result> results = resultsMap.get("results");

        String folderPath = "";
        for (Result result : results) {
            if (result.getId().equals(folderId)) {
                folderPath = result.getPath();
                break;
            }
//            try {
//                if(URLEncoder.encode(result.getPath(), "UTF-8").equals(folderId)){
//                    folderPath = result.getPath();
//                    break;
//                }
//            } catch (Exception e){ System.out.println("bad encoding"); }
        }

        if (folderPath.equals("")) {
            throw new ResourceNotFoundException("folder with provided ID not found");
        }

        return root.getRootMap().get(folderPath);
    }


}
