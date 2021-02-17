package com.chassot.auth.domain.authentication.entity;

import com.chassot.auth.domain.authentication.enums.ProviderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Indexed
    private String type;

    @Indexed
    private String value;

    @Indexed
    private LocalDateTime expiresIn;

    @Indexed
    @Enumerated(EnumType.STRING)
    private ProviderEnum provider;

}
