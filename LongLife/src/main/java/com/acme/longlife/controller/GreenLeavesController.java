package com.acme.longlife.controller;

import com.acme.longlife.domain.model.BigTree;
import com.acme.longlife.domain.model.GreenLeaf;
import com.acme.longlife.domain.service.BigTreeService;
import com.acme.longlife.domain.service.GreenLeafService;
import com.acme.longlife.resource.BigTreeResource;
import com.acme.longlife.resource.GreenLeafResource;
import com.acme.longlife.resource.SaveBigTreeResource;
import com.acme.longlife.resource.SaveGreenLeafResource;
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
public class GreenLeavesController {
    @Autowired
    private GreenLeafService greenLeafService;
    @Autowired
    private ModelMapper mapper;


    //GET ALL GREEN LEAVES BY BIG TREE ID
    @Operation(summary = "Get GreenLeaves By BigTreeId", description = "Get GreenLeaves By BigTreeId by Pages", tags = {"Green Leaves"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All GreenLeaves returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/big_trees/{bigTreeId}/green_leaves")
    public Page<GreenLeafResource> getAllGreenLeavesByBigTreeId(@PathVariable Long bigTreeId, Pageable pageable){
        Page<GreenLeaf> greenLeafPage = greenLeafService.getAllGreenLeavesByBigTreeId(bigTreeId, pageable);
        List<GreenLeafResource> resources =greenLeafPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    //GET GREEN LEAF BY ID
    @Operation(summary = "Get GreenLeaf By Id And BigTreeId", description = "Get GreenLeaf By Id And BigTreeId", tags = {"Green Leaves"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "GreenLeaf returned", content = @Content(mediaType =
                    "application/json"))
    })
    @GetMapping("/big_trees/{bigTreeId}/green_leaves/{greenLeafId}")
    public GreenLeafResource getGreenLeafByIdAndBigTreeId(@PathVariable Long bigTreeId, @PathVariable Long greenLeafId){
        return convertToResource(greenLeafService.getGreenLeafByIdAndBigTreeId(bigTreeId, greenLeafId));
    }


    //POST GREEN LEAF
    @Operation(summary = "Post GreenLeaf", description = "Create a GreenLeaf", tags = {"Green Leaves"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "GreenLeaf created", content = @Content(mediaType =
                    "application/json"))
    })
    @PostMapping("/big_trees/{bigTreeId}/green_leaves")
    public GreenLeafResource createGreenLeaf(@PathVariable Long bigTreeId, @Valid @RequestBody SaveGreenLeafResource resource){
        GreenLeaf greenLeaf = convertToEntity(resource);
        return convertToResource(greenLeafService.createGreenLeaf(bigTreeId, greenLeaf));
    }


    //PUT GREEN LEAF
    @Operation(summary = "Put GreenLeaf", description = "Update a GreenLeaf", tags = {"Green Leaves"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "GreenLeaf updated", content = @Content(mediaType =
                    "application/json"))
    })
    @PutMapping("/big_trees/{bigTreeId}/green_leaves/{greenLeafId}")
    public GreenLeafResource updateGreenLeaf(@PathVariable Long bigTreeId, @PathVariable Long greenLeafId, @RequestBody SaveGreenLeafResource resource){
        GreenLeaf greenLeaf = convertToEntity(resource);
        return convertToResource(greenLeafService.updateGreenLeaf(bigTreeId, greenLeafId, greenLeaf));
    }


    //DELETE BIG TREE
    @Operation(summary = "Delete GreenLeaf", description = "Delete a GreenLeaf by Id", tags = {"Green Leaves"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "GreenLeaf deleted", content = @Content(mediaType =
                    "application/json"))
    })
    @DeleteMapping("/big_trees/{bigTreeId}/green_leaves/{greenLeafId}")
    public ResponseEntity<?> deleteBigTree(@PathVariable Long bigTreeId, @PathVariable Long greenLeafId){
        return greenLeafService.deleteGreenLeaf(bigTreeId, greenLeafId);
    }


    private GreenLeaf convertToEntity(SaveGreenLeafResource resource) {
        return mapper.map(resource, GreenLeaf.class);
    }

    private GreenLeafResource convertToResource(GreenLeaf entity) {
        return mapper.map(entity, GreenLeafResource.class);
    }
}
