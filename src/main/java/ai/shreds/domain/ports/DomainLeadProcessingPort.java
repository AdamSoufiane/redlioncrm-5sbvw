package ai.shreds.domain.ports;

import ai.shreds.domain.entities.DomainLeadEntity;
import ai.shreds.domain.exceptions.DomainValidationException;

/**
 * Interface for processing lead data within the domain layer.
 * This port is used to define the contract for lead processing operations.
 */
public interface DomainLeadProcessingPort {
    /**
     * Processes a single lead.
     * Implementations of this method should handle the business logic associated with lead processing.
     *
     * @param lead the lead entity to process
     */
    void processLead(DomainLeadEntity lead) throws DomainValidationException;
}