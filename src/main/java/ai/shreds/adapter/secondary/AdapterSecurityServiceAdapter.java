package ai.shreds.adapter.secondary;

import ai.shreds.application.ports.ApplicationSecurityServicePort;
import ai.shreds.shared.SharedLeadDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdapterSecurityServiceAdapter implements ApplicationSecurityServicePort {

    private static final Logger logger = LoggerFactory.getLogger(AdapterSecurityServiceAdapter.class);

    private final ExternalSecurityService externalSecurityService;

    @Autowired
    public AdapterSecurityServiceAdapter(ExternalSecurityService externalSecurityService) {
        this.externalSecurityService = externalSecurityService;
    }

    @Override
    public boolean validateData(SharedLeadDataDTO leadData) {
        if (leadData == null) {
            logger.error("Lead data is null");
            return false;
        }
        logger.info("Validating lead data: {}", leadData);
        return externalSecurityService.validateLeadData(leadData.getId(), leadData.getEmail(), leadData.getCompany());
    }

    @Override
    public boolean authorizeOperation() {
        logger.info("Authorizing operation");
        return externalSecurityService.checkAuthorization();
    }
}