package net.mvergara.gestao_vagas.modules.company.respsitories;

import net.mvergara.gestao_vagas.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

    // Busca por vaga com "contains" - LIKE
    // Select * from job where description LIKE %filter%
    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);
}
