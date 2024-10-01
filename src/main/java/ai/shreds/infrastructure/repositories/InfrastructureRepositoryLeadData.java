package ai.shreds.infrastructure.repositories;

import ai.shreds.domain.entities.DomainLeadEntity;
import ai.shreds.domain.ports.DomainLeadRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

public class InfrastructureRepositoryLeadData implements DomainLeadRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureRepositoryLeadData.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveLead(DomainLeadEntity lead) {
        try {
            entityManager.persist(lead);
            logger.info("Lead saved successfully: {}", lead);
        } catch (Exception e) {
            logger.error("Error saving lead: {}", lead, e);
            throw new RuntimeException("Error saving lead: " + lead, e);
        }
    }

    @Override
    public DomainLeadEntity findLeadById(UUID id) {
        try {
            DomainLeadEntity lead = entityManager.find(DomainLeadEntity.class, id);
            if (lead == null) {
                logger.warn("Lead not found: {}", id);
                throw new EntityNotFoundException("Lead not found with id: " + id);
            }
            logger.info("Lead found: {}", lead);
            return lead;
        } catch (EntityNotFoundException e) {
            logger.warn("Lead not found: {}", id, e);
            throw e;
        } catch (Exception e) {
            logger.error("Error finding lead by id: {}", id, e);
            throw e;
        }
    }

    @Override
    public List<DomainLeadEntity> findAllLeads() {
        try {
            TypedQuery<DomainLeadEntity> query = entityManager.createQuery("SELECT l FROM DomainLeadEntity l", DomainLeadEntity.class);
            List<DomainLeadEntity> leads = query.getResultList();
            logger.info("All leads retrieved: {}", leads.size());
            return leads;
        } catch (Exception e) {
            logger.error("Error retrieving all leads", e);
            throw e;
        }
    }

    @Override
    public void deleteLead(UUID id) {
        try {
            DomainLeadEntity lead = findLeadById(id);
            if (lead != null) {
                entityManager.remove(lead);
                logger.info("Lead deleted successfully: {}", lead);
            } else {
                logger.warn("Lead not found for deletion: {}", id);
                throw new EntityNotFoundException("Lead not found for deletion: " + id);
            }
        } catch (EntityNotFoundException e) {
            logger.warn("Lead not found for deletion: {}", id, e);
            throw e;
        } catch (Exception e) {
            logger.error("Error deleting lead by id: {}", id, e);
            throw e;
        }
    }
}