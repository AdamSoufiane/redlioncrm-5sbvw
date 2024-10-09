package ai.shreds.domain.ports;

import ai.shreds.domain.entities.DomainLeadEntity;
import ai.shreds.infrastructure.exceptions.InfrastructureException;

/**
 * Interface for external processing of lead data within the domain layer.
 * This port is used to define the contract for lead processing operations that involve external services.
 */
public interface DomainLeadExternalProcessingPort {
    /**
     * Processes a single lead.
     * Implementations of this method should handle the business logic associated with lead processing involving external systems.
     *
     * @param lead the lead entity to process
     * @throws InfrastructureException if there is an error during processing
     */
    void processLead(DomainLeadEntity lead) throws InfrastructureException;
}