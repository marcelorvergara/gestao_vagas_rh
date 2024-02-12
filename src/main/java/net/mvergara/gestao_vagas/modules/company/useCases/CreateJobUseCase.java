package net.mvergara.gestao_vagas.modules.company.useCases;

import net.mvergara.gestao_vagas.exceptions.CompanyNotFoundException;
import net.mvergara.gestao_vagas.modules.company.entities.JobEntity;
import net.mvergara.gestao_vagas.modules.company.respsitories.CompanyRepository;
import net.mvergara.gestao_vagas.modules.company.respsitories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {


    private final JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public CreateJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public JobEntity execute(JobEntity jobEntity) {

        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(()-> {
            throw new CompanyNotFoundException();
        });
        return this.jobRepository.save(jobEntity);
    }
}
