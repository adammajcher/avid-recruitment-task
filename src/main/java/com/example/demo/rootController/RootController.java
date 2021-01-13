package com.example.demo.rootController;

import com.example.demo.models.FolderInfo;
import com.example.demo.models.data.Folder;
import com.example.demo.rootService.RootService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class RootController {

    private RootService rootService;

    public RootController(RootService rootService) {
        this.rootService = rootService;
    }

    @RequestMapping("")
    public ResponseEntity<Map<String, List<FolderInfo>>> getResultsMap(@RequestParam(value = "skip", defaultValue = "0") int skip, @RequestParam(value = "limit", defaultValue = "0") int limit, @RequestParam(value = "query", defaultValue = "") String query) {
        List<FolderInfo> folderInfoList = rootService.getFolderInfos(skip, limit, query);
        Map<String, List<FolderInfo>> map = new HashMap<>();
        map.put("results", folderInfoList);
        return ResponseEntity.ok(map);
    }

    @RequestMapping("/{folderId}")
    public ResponseEntity<Folder> getFolder(@PathVariable String folderId, @RequestParam(value = "skip", defaultValue = "0") int skip, @RequestParam(value = "limit", defaultValue = "0") int limit, @RequestParam(value = "query", defaultValue = "") String query) {
        return ResponseEntity.ok(rootService.getFolder(folderId, skip, limit, query));
    }
}

