package io.github.alwaysvinyl.service;

import io.github.alwaysvinyl.domain.dto.SaleCreateDto;
import io.github.alwaysvinyl.domain.dto.SaleItemCreateDto;
import io.github.alwaysvinyl.domain.model.Album;
import io.github.alwaysvinyl.domain.model.Genre;
import io.github.alwaysvinyl.domain.model.Sale;
import io.github.alwaysvinyl.domain.model.SaleItem;
import io.github.alwaysvinyl.domain.repository.SaleRepository;
import io.github.alwaysvinyl.domain.service.AlbumService;
import io.github.alwaysvinyl.domain.service.CashbackService;
import io.github.alwaysvinyl.domain.service.SaleService;
import io.github.alwaysvinyl.exception.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SaleServiceTest {

    @Mock
    private SaleRepository repository;

    @Mock
    private AlbumService albumService;

    @Mock
    private CashbackService cashbackService;

    @InjectMocks
    private SaleService service;

    @Before
    public void setUp() {
        final Album bornToBeBlue = new Album("Born to Be Blue", "Freddie Hubbard", BigDecimal.TEN, Genre.CLASSIC);

        setUpCashback();
        setUpAlbum(bornToBeBlue);
        setUpSale(bornToBeBlue);
    }

    @Test(expected = NullPointerException.class)
    public void whenNullId_thenThrowsException() {
        service.findById(null);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void whenInvalidId_thenThrowsException() {
        service.findById(0L);
    }

    @Test
    public void whenValidId_thenReturnSale() {
        final Sale sale = service.findById(1L);
        assertThat(sale).isNotNull();
        assertThat(sale.getItems()).isNotEmpty();
        assertThat(sale.getTotalCashbackValue()).isEqualByComparingTo(BigDecimal.valueOf(5.0));
    }

    @Test
    public void testCreateSale() {
        final SaleCreateDto saleCreateDto = new SaleCreateDto();
        saleCreateDto.getItems().add(new SaleItemCreateDto(1L));
        final Sale createdSale = service.createSale(saleCreateDto);
        assertThat(createdSale).isNotNull();
    }

    private void setUpCashback() {
        when(cashbackService.calculateCashback(Genre.CLASSIC, BigDecimal.TEN)).thenReturn(BigDecimal.valueOf(5.0));
    }

    private void setUpAlbum(final Album album) {
        when(albumService.findById(1L)).thenReturn(album);
    }

    private void setUpSale(final Album album) {
        final Sale sale = new Sale();
        final SaleItem saleItem = new SaleItem(album, BigDecimal.valueOf(5.0));
        sale.addItem(saleItem);
        sale.addToTotalCashbackValue(saleItem.getCashbackValue());
        when(repository.findById(1L)).thenReturn(Optional.of(sale));
        when(repository.save(sale)).thenReturn(sale);
    }
}