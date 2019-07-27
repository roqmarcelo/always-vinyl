package io.github.alwaysvinyl.resources;

import com.weddini.throttling.Throttling;
import io.github.alwaysvinyl.domain.dto.AlbumDto;
import io.github.alwaysvinyl.domain.service.AlbumService;
import io.github.alwaysvinyl.exception.validator.Genre;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/albums")
public class AlbumResource {

    @Autowired
    private AlbumService service;

    @GetMapping
    @Throttling(limit = 50)
    @ApiOperation(value = "View a list of available albums", response = Page.class)
    public Page<AlbumDto> findAlbums(
            @RequestParam(value = "genre", required = false) @Genre String genre,
            @SortDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findPaged(genre, pageable);
    }

    @GetMapping("/{id}")
    @Throttling(limit = 50)
    @ApiOperation(value = "View album by the given id", response = AlbumDto.class)
    public AlbumDto findById(@PathVariable(value = "id") @Min(1) Long id) {
        return AlbumDto.of(service.findById(id));
    }
}