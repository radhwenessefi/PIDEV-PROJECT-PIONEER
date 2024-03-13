package tn.esprit.projectbackend;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;


import tn.esprit.projectbackend.Entity.Portfolio;
import tn.esprit.projectbackend.Service.IPortfolioService;
import tn.esprit.projectbackend.Service.PortfolioServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;


@SpringBootTest
class ProjectBackendApplicationTests {
	private PortfolioServiceImp yourServiceClass ;
	@Autowired
	IPortfolioService portfolioService;

	@Test
	public void testFetchDataFromApi() {
		List<Portfolio> result = portfolioService.fetchDataFromApi();
		Assertions.assertNotNull(result);

	}
}
