package ai.shreds.application.services;

import ai.shreds.application.ports.ApplicationLeadProcessingOutputPort;
import ai.shreds.domain.entities.DomainLeadEntity;
import ai.shreds.domain.ports.DomainLeadProcessingPort;
import ai.shreds.application.utils.ApplicationLogger;
import ai.shreds.shared.SharedLeadDataDTO;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ApplicationLeadProcessingService implements ApplicationLeadProcessingOutputPort {

    private final DomainLeadProcessingPort domainLeadProcessingPort;
    private final ApplicationLogger logger;

    public ApplicationLeadProcessingService(DomainLeadProcessingPort domainLeadProcessingPort, ApplicationLogger logger) {
        this.domainLeadProcessingPort = domainLeadProcessingPort;
        this.logger = logger;
    }

    @Override
    public void processLeads(List<SharedLeadDataDTO> leads) {
        logger.logInfo("Starting processLeads method.");
        if (leads == null || leads.isEmpty()) {
            logger.logError("No leads to process.");
            return;
        }

        leads.stream()
            .map(this::convertToDomainEntity)
            .forEach(lead -> {
                try {
                    domainLeadProcessingPort.processLead(lead);
                    logger.logInfo("Processed lead: " + lead.getId());
                } catch (Exception e) {
                    logger.logError("Failed to process lead: " + lead.getId() + ", Error: " + e.getMessage());
                }
            });
        logger.logInfo("Finished processLeads method.");
    }

    private DomainLeadEntity convertToDomainEntity(SharedLeadDataDTO dto) {
        DomainLeadEntity entity = new DomainLeadEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setCompany(dto.getCompany());
        entity.setPosition(dto.getPosition());
        entity.setLocation(dto.getLocation());
        entity.setIndustry(dto.getIndustry());
        entity.setRevenue(dto.getRevenue());
        entity.setSourcePlatform(dto.getSourcePlatform());
        entity.setFetchedAt(dto.getFetchedAt());
        return entity;
    }
}