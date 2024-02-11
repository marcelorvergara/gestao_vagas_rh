package net.mvergara.gestao_vagas.modules.candidate.useCase;

import net.mvergara.gestao_vagas.modules.candidate.dto.ProfileCnadidateResponseDTO;
import net.mvergara.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import net.mvergara.gestao_vagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;
    public ProfileCnadidateResponseDTO execute(UUID idCandidate){
        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });
        var candidateDTO = ProfileCnadidateResponseDTO.builder()
                .description(candidate.getDescription())
                .name(candidate.getName())
                .email(candidate.getEmail())
                .username(candidate.getUsername())
                .id(candidate.getId())
                .build();
        return candidateDTO;
    }
}
