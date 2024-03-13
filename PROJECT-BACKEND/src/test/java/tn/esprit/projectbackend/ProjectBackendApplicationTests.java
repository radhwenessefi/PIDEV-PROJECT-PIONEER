package tn.esprit.projectbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ProjectBackendApplicationTests {
	private PortfolioServiceImp yourServiceClass ;
	@Autowired
	IPortfolioService portfolioService;

//	@Test
//	public void testFetchDataFromApi() {
//		List<Portfolio> result = portfolioService.fetchDataFromApi();
//		Assertions.assertNotNull(result);
//
//	}
}
