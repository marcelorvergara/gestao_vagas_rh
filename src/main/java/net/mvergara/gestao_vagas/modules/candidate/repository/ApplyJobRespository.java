package net.mvergara.gestao_vagas.modules.candidate.repository;

import net.mvergara.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRespository extends JpaRepository<ApplyJobEntity, UUID> {
}
