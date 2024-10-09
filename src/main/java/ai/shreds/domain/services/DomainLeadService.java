package ai.shreds.domain.services;

import ai.shreds.domain.entities.DomainLeadEntity;
import ai.shreds.domain.ports.DomainLeadProcessingPort;
import ai.shreds.domain.ports.DomainLeadRepositoryPort;
import ai.shreds.domain.ports.DomainSecurityServicePort;
import ai.shreds.domain.ports.DomainLeadExternalProcessingPort;
import ai.shreds.domain.exceptions.DomainValidationException;
import ai.shreds.infrastructure.exceptions.InfrastructureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service responsible for processing lead data within the domain layer.
 * It interacts with repository, security, and processing ports to handle lead data.
 */
@Slf4j
@RequiredArgsConstructor
public class DomainLeadService implements DomainLeadProcessingPort {

    private final DomainLeadRepositoryPort leadRepositoryPort;
    private final DomainSecurityServicePort securityServicePort;
    private final DomainLeadExternalProcessingPort leadProcessingPort;

    /**
     * Processes the given lead by validating, saving, and forwarding it for further processing.
     *
     * @param lead the lead entity to be processed
     * @throws DomainValidationException if lead validation fails
     */
    @Override
    public void processLead(DomainLeadEntity lead) throws DomainValidationException {
        log.info("Processing lead: {}", lead);
        if (validateLead(lead)) {
            leadRepositoryPort.saveLead(lead);
            try {
                leadProcessingPort.processLead(lead);
            } catch (InfrastructureException e) {
                log.error("Error processing lead with external service: {}", e.getMessage());
                // Handle or propagate the exception as needed
            }
        } else {
            log.error("Lead validation failed for lead: {}", lead);
            throw new DomainValidationException("Lead validation failed for lead: " + lead.getId());
        }
    }

    /**
     * Validates the given lead using the security service port.
     *
     * @param lead the lead entity to be validated
     * @return true if the lead is valid, false otherwise
     */
    public boolean validateLead(DomainLeadEntity lead) {
        log.info("Validating lead: {}", lead);
        return securityServicePort.validateLeadData(lead);
    }
}