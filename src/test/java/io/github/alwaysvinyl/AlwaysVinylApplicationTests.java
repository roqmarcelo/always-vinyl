package io.github.alwaysvinyl;

import io.github.alwaysvinyl.domain.GenreTest;
import io.github.alwaysvinyl.domain.SaleTest;
import io.github.alwaysvinyl.service.CashbackServiceTest;
import io.github.alwaysvinyl.service.PriceGeneratorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		GenreTest.class,
		SaleTest.class,
		CashbackServiceTest.class,
		PriceGeneratorTest.class
})
public class AlwaysVinylApplicationTests {
}