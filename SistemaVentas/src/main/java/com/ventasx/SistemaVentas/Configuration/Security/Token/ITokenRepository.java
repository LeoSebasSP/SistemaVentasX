package com.ventasx.SistemaVentas.Configuration.Security.Token;

import java.util.List;
import java.util.Optional;

import com.ventasx.SistemaVentas.Persistence.Repository.IGenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface ITokenRepository extends IGenericRepository<Token, Long> {

    @Query(value = """
      select t from Token t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :userId and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(Long userId);

    Optional<Token> findByToken(String token);
}
