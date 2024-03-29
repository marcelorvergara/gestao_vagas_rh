package net.mvergara.gestao_vagas.modules.candidate.useCase;

import net.mvergara.gestao_vagas.exceptions.*;
import net.mvergara.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import net.mvergara.gestao_vagas.modules.candidate.repository.ApplyJobRespository;
import net.mvergara.gestao_vagas.modules.candidate.repository.CandidateRepository;
import net.mvergara.gestao_vagas.modules.company.respsitories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRespository applyJobRespository;

    // Id do candidato
    // Id da vaga
    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {
        // Validar se o candidato existe
        this.candidateRepository.findById(idCandidate)
                .orElseThrow(UserNotFoundException::new);
        // Validar se a vaga existe
        this.jobRepository.findById(idJob)
                .orElseThrow(JobNotFoundException::new);
        // Inscrição do candidato na vaga
        var applyJob = ApplyJobEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();
        applyJob = applyJobRespository.save(applyJob);
        return applyJob;
    }
}
