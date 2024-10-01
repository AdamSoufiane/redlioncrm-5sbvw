package ai.shreds.infrastructure.external_services;

import ai.shreds.domain.entities.DomainLeadEntity;
import ai.shreds.domain.ports.DomainSecurityServicePort;
import ai.shreds.infrastructure.logging.InfrastructureLogger;
import org.springframework.stereotype.Component;

@Component
public class InfrastructureSecurityServiceClient implements DomainSecurityServicePort {

    private final InfrastructureLogger logger;

    public InfrastructureSecurityServiceClient(InfrastructureLogger logger) {
        this.logger = logger;
    }

    @Override
    public boolean validateLeadData(DomainLeadEntity lead) {
        try {
            boolean isValid = externalSecurityCheck(lead);
            if (isValid) {
                logger.logInfo("Lead data validated successfully.");
            }
            return isValid;
        } catch (Exception e) {
            logger.logError("Error validating lead data: " + e.getMessage());
            return false;
        }
    }

    private boolean externalSecurityCheck(DomainLeadEntity lead) throws Exception {
        // Here you would implement the actual security check logic
        // For demonstration, assume the check always passes
        return true;
    }
}