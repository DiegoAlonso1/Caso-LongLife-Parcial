package com.acme.longlife.controller;

import com.acme.longlife.domain.model.BigTree;
import com.acme.longlife.domain.service.BigTreeService;
import com.acme.longlife.resource.BigTreeResource;
import com.acme.longlife.resource.SaveBigTreeResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BigTreesController {
    @Autowired
    private BigTreeService bigTreeService;
    @Autowired
    private ModelMapper mapper;


    //GET ALL BIG TREES
    @Operation(summary = "Get BigTrees", description = "Get All BigTrees by Pages", tags = {"Big Trees"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All BigTrees returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/big_trees")
    public Page<BigTreeResource> getAllBigTrees(Pageable pageable){
        Page<BigTree> bigTreePage = bigTreeService.getAllBigTrees(pageable);
        List<BigTreeResource> resources =bigTreePage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    //POST BIG TREE
    @Operation(summary = "Post BigTree", description = "Create a BigTree", tags = {"Big Trees"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BigTree created", content = @Content(mediaType =
                    "application/json"))
    })
    @PostMapping("/big_trees")
    public BigTreeResource createBigTree(@Valid @RequestBody SaveBigTreeResource resource){
        BigTree bigTree = convertToEntity(resource);
        return convertToResource(bigTreeService.createBigTree(bigTree));
    }


    //GET BIG TREE BY ID
    @Operation(summary = "Get BigTree By Id", description = "Get a BigTree by Id", tags = {"Big Trees"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BigTree returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/big_trees/{bigTreeId}")
    public BigTreeResource getBigTreeById(@PathVariable Long bigTreeId){
        return convertToResource(bigTreeService.getBigTreeById(bigTreeId));
    }


    //PUT BIG TREE
    @Operation(summary = "Put BigTree", description = "Update a BigTree", tags = {"Big Trees"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BigTree updated", content = @Content(mediaType =
                    "application/json"))
    })
    @PutMapping("/big_trees/{bigTreeId}")
    public BigTreeResource updateBigTree(@PathVariable Long bigTreeId, @RequestBody SaveBigTreeResource resource){
        BigTree bigTree = convertToEntity(resource);
        return convertToResource(bigTreeService.updateBigTree(bigTreeId, bigTree));
    }


    //DELETE BIG TREE
    @Operation(summary = "Delete BigTree", description = "Delete a BigTree by Id", tags = {"Big Trees"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BigTree deleted", content = @Content(mediaType =
                    "application/json"))
    })
    @DeleteMapping("/big_trees/{bigTreeId}")
    public ResponseEntity<?> deleteBigTree(@PathVariable Long bigTreeId){
        return bigTreeService.deleteBigTree(bigTreeId);
    }



    private BigTree convertToEntity(SaveBigTreeResource resource) {
        return mapper.map(resource, BigTree.class);
    }

    private BigTreeResource convertToResource(BigTree entity) {
        return mapper.map(entity, BigTreeResource.class);
    }
}
