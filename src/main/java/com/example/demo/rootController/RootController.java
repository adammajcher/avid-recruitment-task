package com.example.demo.rootController;

import com.example.demo.jsonObjects.Folder;
import com.example.demo.jsonObjects.Result;
import com.example.demo.rootService.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RootController {

    @Autowired
    private RootService rootService;

    @RequestMapping("/")
    public ResponseEntity<Map<String, List<Result>>> getResultsMap() {
        return ResponseEntity.ok(rootService.getResultsMap());
    }

    @RequestMapping("/{folderId}")
    public ResponseEntity<Folder> getFolder(@PathVariable String folderId) {
        Folder folder = rootService.getFolder(folderId);
        return ResponseEntity.ok(folder);
    }
}

