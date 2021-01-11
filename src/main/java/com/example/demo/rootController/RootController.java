package com.example.demo.rootController;

import com.example.demo.jsonObjects.Folder;
import com.example.demo.diffrentObjects.Result;
import com.example.demo.rootService.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RootController {

    @Autowired
    private RootService rootService;

    @RequestMapping("")
    public ResponseEntity<Map<String, List<Result>>> getResultsMap(@RequestParam(value = "skip", defaultValue = "0") int skip, @RequestParam(value = "limit", defaultValue = "0") int limit) {
        return ResponseEntity.ok(rootService.getResultsMap(skip, limit));
    }

    @RequestMapping("/{folderId}")
    public ResponseEntity<Folder> getFolder(@PathVariable String folderId, @RequestParam(value = "skip", defaultValue = "0") int skip, @RequestParam(value = "limit", defaultValue = "0") int limit) {
        return ResponseEntity.ok(rootService.getFolder(folderId, skip, limit));
    }
}

