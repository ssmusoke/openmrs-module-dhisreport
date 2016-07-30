package org.openmrs.module.dhisreport.api.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import static org.junit.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.Location;
import org.openmrs.module.dhisreport.api.DHIS2ReportingService;
import org.openmrs.module.dhisreport.api.dxf2.DataValueSet;
import org.openmrs.module.dhisreport.api.model.*;
import org.openmrs.module.dhisreport.api.utils.MonthlyPeriod;
import org.openmrs.module.dhisreport.api.utils.Period;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.openmrs.api.context.Context;

/**
 * Tests {@link $ DHIS2ReportingService} .
 */
public class DHIS2ReportingServiceImplTest{

	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private DHIS2ReportingService service;

	@Before
	public void before() {
		BasicConfigurator.configure();
		//service = Context.getService(DHIS2ReportingService.class);
	}

	/**
	 * @see DHIS2ReportingServiceImpl#evaluateReportDefinition(ReportDefinition,Period,Location)
	 * @verifies evaluate Report Definition
	 */
	@Test
	public void evaluateReportDefinition_shouldEvaluateReportDefinition() throws Exception {

		ClassPathResource resource = new ClassPathResource("reportDefinition.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(ReportTemplates.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		ReportTemplates reportTemplates = (ReportTemplates) jaxbUnmarshaller.unmarshal(resource.getInputStream());
		List<ReportDefinition> rds = reportTemplates.getReportDefinitions();
		DHIS2ReportingServiceImpl impl = new DHIS2ReportingServiceImpl();
		String timeperiod = "2016-06-28";
		Location location = new Location();
		location.setName("County General");
		location.setDescription("desc");
		location.setAddress1("address1");
		Period period = new MonthlyPeriod(new SimpleDateFormat("yyyy-MM-dd").parse(timeperiod));

		for (ReportDefinition rd : rds) {
			System.out.println("ReportDefinition uid = " + rd.getUid() + " name  = " + rd.getName());
			impl.evaluateReportDefinition(rd, period, location);
			// DHIS2ReportingService service = Context.getService(
			// DHIS2ReportingService.class );
			//DataValueSet dvs = service.evaluateReportDefinition(rd, period, location);
		}
	}
}
