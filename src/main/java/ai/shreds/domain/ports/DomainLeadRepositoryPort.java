package ai.shreds.domain.ports;

import ai.shreds.domain.entities.DomainLeadEntity;
import java.util.List;
import java.util.UUID;

/**
 * Port interface for repository operations on lead data within the domain.
 */
public interface DomainLeadRepositoryPort {
    /**
     * Saves a lead entity to the database.
     * @param lead the lead entity to save
     */
    void saveLead(DomainLeadEntity lead);

    /**
     * Retrieves a lead entity by its unique identifier.
     * @param id the UUID of the lead
     * @return the found lead entity, or null if not found
     */
    DomainLeadEntity findLeadById(UUID id);

    /**
     * Retrieves all lead entities from the database.
     * @return a list of all lead entities
     */
    List<DomainLeadEntity> findAllLeads();

    /**
     * Deletes a lead entity from the database based on its ID.
     * @param id the UUID of the lead to delete
     */
    void deleteLead(UUID id);

    /**
     * Updates an existing lead entity in the database.
     * @param lead the lead entity to update
     */
    void updateLead(DomainLeadEntity lead);
}