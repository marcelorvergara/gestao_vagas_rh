package net.mvergara.gestao_vagas.modules.candidate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {

    // Automagicamente sabe fazer o select pelo email e pelo username
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
}
