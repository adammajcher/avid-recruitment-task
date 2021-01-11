package com.example.demo.rootService;

import com.example.demo.diffrentObjects.Result;
import com.example.demo.jsonObjects.Asset;
import com.example.demo.jsonObjects.Folder;
import com.example.demo.jsonObjects.Root;
import com.example.demo.resourceNotFoundException.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RootService {

    private Root root;
    private Map<String, List<Result>> resultsMap = new HashMap<>();

    public RootService() throws Exception {
        root = RootCreator.createRoot();
        Map<String, Folder> map = root.getRootMap();

        List<Result> results = new ArrayList<>();

        for (Map.Entry<String, Folder> pair : map.entrySet()) {
            Result result = new Result(pair.getValue().getId(), pair.getKey());
            results.add(result);
        }

        resultsMap.put("results", results);
    }

    public Map<String, List<Result>> getResultsMap(int skip, int limit, String query) {
        List<Result> results = resultsMap.get("results");
        Map<String, List<Result>> newMap = new HashMap<>();
        if (!query.isEmpty()) {
            results = results.stream().filter(folder -> folder.getPath().contains(query)).collect(Collectors.toList());
        }
        if (skip == 0 && limit == 0) {
            newMap.put("results", results);
            return newMap;
        } else {
            //PaddingList paddingList = getPaddingList(new PaddingList(results, null), skip, limit, "results");
            //List<Result> resultsPaginationList = paddingList.getResults();
            List<Result> resultsPaginationList = new ArrayList<>();
            for (int i = 0; i < results.size(); i++) {
                if (i >= skip && i <= results.size() - limit - 1) {
                    resultsPaginationList.add(results.get(i));
                }
            }
            newMap.put("results", resultsPaginationList);
            return newMap;
        }

    }


    public Folder getFolder(String folderId, int skip, int limit, String query) {
        List<Result> results = resultsMap.get("results");

        String folderPath = "";
        for (Result result : results) {
            if (result.getId().equals(folderId)) {
                folderPath = result.getPath();
                break;
            }
            try {
                String decodedFolderName = URLDecoder.decode(folderId, "UTF-8");
                String resultName = result.getPath();
                if (resultName.equals(decodedFolderName)) {
                    folderPath = result.getPath();
                    break;
                }
            } catch (Exception e) {
                System.out.println("bad encoding");
            }
        }

        if (folderPath.equals("")) {
            throw new ResourceNotFoundException("folder with provided ID not found");
        }

        Folder folder = root.getRootMap().get(folderPath);
        List<Asset> assets = folder.getAssets();
        if (!query.isEmpty()) {
            assets = assets.stream().filter(asset -> asset.getBase().getType().contains(query)).collect(Collectors.toList());
        }
        if (skip == 0 && limit == 0) {
            return folder;
        } else {
            //PaddingList paddingList = getPaddingList(new PaddingList(null, assets), skip, limit, "assets");
            //List<Asset> resultsPaddingList = paddingList.getAssets();
            List<Asset> assetsPaginationList = new ArrayList<>();
            for (int i = 0; i < assets.size(); i++) {
                if (i >= skip && i <= assets.size() - limit - 1) {
                    assetsPaginationList.add(assets.get(i));
                }
            }
            folder.setAssets(assetsPaginationList);
            return folder;
        }
    }

//    private PaddingList getPaddingList(PaddingList paddingList, int skip, int limit, String flag){
//        List<Object>  list = new ArrayList<Object>();
//        if(flag.equals("assets")){
//            List<Asset> assetsList = paddingList.getAssets();
//            list = assetsList;
//            List<Asset> newList = new ArrayList<>();
//        } else if (flag.equals("results")){
//           list = paddingList.getResults();
//            List<Result> newList = new ArrayList<>();
//        } else {
//            return paddingList;
//        }
//
//        for (int i = 0; i < list.size(); i++) {
//            if (i >= skip && i <= list.size() - limit - 1) {
//                paddingList.add(list.get(i));
//            }
//        }
//        return paddingList;
//    }
}
