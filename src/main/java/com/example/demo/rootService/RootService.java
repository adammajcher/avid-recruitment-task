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
                if (folderInfoPathName.equals(decodedFolderName)) {
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

        Folder folder = root.getRootMap().get(folderPath);
        List<Asset> folderAssets = folder.getAssets();
        if (!query.isEmpty()) {
            folderAssets = folderAssets.stream().filter(asset -> asset.getBase().getType().contains(query)).collect(Collectors.toList());
        }
        if (skip == 0 && limit == 0) {
            folder.setAssets(folderAssets);
            return folder;
        } else {
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
