package com.example.demo.rootService;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.FolderInfo;
import com.example.demo.models.data.Asset;
import com.example.demo.models.data.Folder;
import com.example.demo.models.data.Root;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RootService {

    private Root root;
    private List<FolderInfo> folderInfos;

    public RootService() throws Exception {
        root = RootCreator.createRoot();
        Map<String, Folder> map = root.getRootMap();
        List<FolderInfo> folderInfoList = new ArrayList<>();
        for (Map.Entry<String, Folder> pair : map.entrySet()) {
            FolderInfo folderInfo = new FolderInfo(pair.getValue().getId(), pair.getKey());
            folderInfoList.add(folderInfo);
        }
        folderInfos = folderInfoList;
    }

    public List<FolderInfo> getFolderInfos(int skip, int limit, String query) {
        List<FolderInfo> folderInfoList = folderInfos;
        if (!query.isEmpty()) {
            folderInfoList = folderInfoList.stream().filter(folder -> folder.getPath().contains(query)).collect(Collectors.toList());
        }
        if (skip == 0 && limit == 0) {
            return folderInfoList;
        } else {
            List<FolderInfo> folderInfoPaginationList = new ArrayList<>();
            for (int i = 0; i < folderInfos.size(); i++) {
                if (i >= skip) {
                    if (limit == 0) {
                        folderInfoPaginationList.add(folderInfos.get(i));
                    } else {
                        if (folderInfoPaginationList.size() < limit) {
                            folderInfoPaginationList.add(folderInfos.get(i));
                        }
                    }
                }
            }
            return folderInfoPaginationList;
        }
    }


    public Folder getFolder(String folderId, int skip, int limit, String query) {
        String folderPath = "";
        for (FolderInfo folderInfo : folderInfos) {
            if (folderInfo.getId().equals(folderId)) {
                folderPath = folderInfo.getPath();
                break;
            }
            try {
                String decodedFolderName = URLDecoder.decode(folderId, "UTF-8");
                String folderInfoPathName = folderInfo.getPath();
                String[] folderPathCuted = folderInfoPathName.substring(2).split("/");
                String folderNameWithType = folderPathCuted[folderPathCuted.length -1];
                String folderName = folderNameWithType.split("\\.")[0];
                if (decodedFolderName.equals(folderInfoPathName) || decodedFolderName.equals(folderName)) {
                    folderPath = folderInfo.getPath();
                    break;
                }
            } catch (Exception e) {
                System.out.println("bad encoding");
            }
        }

        if (folderPath.equals("")) {
            throw new ResourceNotFoundException("folder with provided ID not found");
        }

        Folder folderOriginal = root.getRootMap().get(folderPath);
        Folder folder = new Folder(folderOriginal);
        if (!query.isEmpty()) {
            List<Asset> folderAssets = folder.getAssets();
            folderAssets = folderAssets.stream().filter(asset -> asset.getBase().getType().contains(query)).collect(Collectors.toList());
            folder.setAssets(folderAssets);
        }
        if (skip == 0 && limit == 0) {
            return folder;
        } else {
            List<Asset> folderAssets = folder.getAssets();
            List<Asset> assetsPaginationList = new ArrayList<>();
            for (int i = 0; i < folderAssets.size(); i++) {
                if (i >= skip) {
                    if (limit == 0) {
                        assetsPaginationList.add(folderAssets.get(i));
                    } else {
                        if (assetsPaginationList.size() < limit) {
                            assetsPaginationList.add(folderAssets.get(i));
                        }
                    }
                }
            }
            folder.setAssets(assetsPaginationList);
            return folder;
        }
    }
}
