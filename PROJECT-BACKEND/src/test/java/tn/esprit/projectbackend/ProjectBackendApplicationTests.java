package tn.esprit.projectbackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.projectbackend.Entity.Portfolio;
import tn.esprit.projectbackend.Service.PortfolioServiceImp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.AssertJUnit.assertNotNull;

@SpringBootTest
class ProjectBackendApplicationTests {


	@Test
	public void testFetchDataFromApi() {
		PortfolioServiceImp yourServiceClass = new PortfolioServiceImp();
		List<Portfolio> result = yourServiceClass.fetchDataFromApi();
		assertNotNull(result);

	}



}
