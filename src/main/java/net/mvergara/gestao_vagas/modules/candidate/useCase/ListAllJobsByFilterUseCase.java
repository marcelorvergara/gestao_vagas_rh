package net.mvergara.gestao_vagas.modules.candidate.useCase;

import net.mvergara.gestao_vagas.modules.company.entities.JobEntity;
import net.mvergara.gestao_vagas.modules.company.respsitories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter) {
        return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }
}
