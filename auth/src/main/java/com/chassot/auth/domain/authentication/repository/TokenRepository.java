package com.chassot.auth.domain.authentication.repository;

import com.chassot.auth.domain.authentication.entity.Token;
import com.chassot.auth.domain.authentication.enums.ProviderEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {

    Optional<Token> findByProvider(ProviderEnum provider);

}
