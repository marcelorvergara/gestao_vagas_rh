package net.mvergara.gestao_vagas.modules.company.useCases;

import net.mvergara.gestao_vagas.modules.company.entities.JobEntity;
import net.mvergara.gestao_vagas.modules.company.respsitories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {


    private final JobRepository jobRepository;

    public CreateJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public JobEntity execute(JobEntity jobEntity) {
        return this.jobRepository.save(jobEntity);
    }
}
