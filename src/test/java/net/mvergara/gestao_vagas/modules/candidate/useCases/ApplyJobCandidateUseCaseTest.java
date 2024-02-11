package net.mvergara.gestao_vagas.modules.candidate.useCases;


import net.mvergara.gestao_vagas.exceptions.JobNotFoundException;
import net.mvergara.gestao_vagas.exceptions.UserNotFoundException;
import net.mvergara.gestao_vagas.modules.candidate.entity.*;
import net.mvergara.gestao_vagas.modules.candidate.repository.ApplyJobRespository;
import net.mvergara.gestao_vagas.modules.candidate.repository.CandidateRepository;
import net.mvergara.gestao_vagas.modules.candidate.useCase.ApplyJobCandidateUseCase;
import net.mvergara.gestao_vagas.modules.company.entities.JobEntity;
import net.mvergara.gestao_vagas.modules.company.respsitories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;
    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRespository applyJobRespository;

    @Test
    @DisplayName("Should not apply job with candidate not found.")
    public void should_not_apply_job_with_candidate_not_found() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    public void should_not_apply_job_with_job_not_found() {

        // Garantir que exita um candidato
        var idCandidate = UUID.randomUUID();
        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        // Testar o job
        try {
            applyJobCandidateUseCase.execute(idCandidate, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    public void should_create_new_apply_job() {

        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRespository.save(applyJob)).thenReturn(new ApplyJobEntity());

        var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        assertThat(result).hasFieldOrProperty("id");
    }
}
