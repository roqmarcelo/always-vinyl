package io.github.alwaysvinyl.resources;

import com.weddini.throttling.Throttling;
import io.github.alwaysvinyl.domain.dto.SaleCreateDto;
import io.github.alwaysvinyl.domain.dto.SaleDto;
import io.github.alwaysvinyl.domain.service.SaleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Validated
@RestController
@RequestMapping("/sales")
public class SaleResource {

    @Autowired
    private SaleService service;

    @GetMapping
    @Throttling(limit = 50)
    @ApiOperation(value = "View a list of sales made", response = Page.class)
    public Page<SaleDto> findSales(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate,
            @SortDefault(sort = "dateSold", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.findPaged(startDate, endDate, pageable);
    }

    @GetMapping("/{id}")
    @Throttling(limit = 50)
    @ApiOperation(value = "View sale by the given id",response = SaleDto.class)
    public SaleDto findById(@PathVariable(value = "id") @Min(1) Long id) {
        return SaleDto.of(service.findById(id));
    }

    @PostMapping
    @Throttling(limit = 50)
    @ApiOperation(value = "Register a sale order", response = SaleDto.class)
    public ResponseEntity<SaleDto> createSale(@RequestBody @Valid @NotNull SaleCreateDto saleCreateDto) {
        SaleDto createdSale = SaleDto.of(service.createSale(saleCreateDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSale);
    }
}