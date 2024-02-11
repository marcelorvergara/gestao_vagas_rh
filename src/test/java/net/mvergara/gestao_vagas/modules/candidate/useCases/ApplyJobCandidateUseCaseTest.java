package net.mvergara.gestao_vagas.modules.candidate.useCases;


import net.mvergara.gestao_vagas.exceptions.JobNotFoundException;
import net.mvergara.gestao_vagas.exceptions.UserNotFoundException;
import net.mvergara.gestao_vagas.modules.candidate.CandidateEntity;
import net.mvergara.gestao_vagas.modules.candidate.CandidateRepository;
import net.mvergara.gestao_vagas.modules.candidate.useCase.ApplyJobCandidateUseCase;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;
    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private JobRepository jobRepository;

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
    public void should_not_apply_job_with_job_not_found(){

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
}
