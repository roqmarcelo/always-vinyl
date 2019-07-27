package io.github.alwaysvinyl.domain.service;

import io.github.alwaysvinyl.domain.dto.SaleCreateDto;
import io.github.alwaysvinyl.domain.dto.SaleDto;
import io.github.alwaysvinyl.domain.dto.SaleItemCreateDto;
import io.github.alwaysvinyl.domain.model.Album;
import io.github.alwaysvinyl.domain.model.Sale;
import io.github.alwaysvinyl.domain.model.SaleItem;
import io.github.alwaysvinyl.domain.repository.SaleRepository;
import io.github.alwaysvinyl.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private CashbackService cashbackService;

    @Transactional(readOnly = true)
    public Page<SaleDto> findPaged(final LocalDate startDate, final LocalDate endDate, final Pageable pageable) {
        if (startDate != null && endDate != null) {
            return repository.findByDateSoldBetween(startDate, endDate, pageable).map(SaleDto::of);
        }
        if (startDate != null) {
            return repository.findByDateSoldGreaterThanEqual(startDate, pageable).map(SaleDto::of);
        }
        if (endDate != null) {
            return repository.findByDateSoldLessThanEqual(endDate, pageable).map(SaleDto::of);
        }
        return repository.findBy(pageable).map(SaleDto::of);
    }

    @Transactional(readOnly = true)
    public Sale findById(final Long id) {
        Objects.requireNonNull(id);
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sale", "id", id));
    }

    @Transactional
    public Sale createSale(final SaleCreateDto saleCreateDto) {
        final Sale sale = new Sale();

        for (SaleItemCreateDto saleItemCreateDto : saleCreateDto.getItems()) {
            final Album album = albumService.findById(saleItemCreateDto.getAlbumId());
            final BigDecimal cashbackValue = cashbackService.calculateCashback(album.getGenre(), album.getPrice());
            final SaleItem item = new SaleItem(album, cashbackValue);

            sale.addItem(item);
            sale.addToTotalCashbackValue(item.getCashbackValue());
        }

        return repository.save(sale);
    }
}