package com.udacity.EntityExec.DAO;

import com.udacity.EntityExec.data.Candy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CandyDAOImpl implements CandyDAO{
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    //we can avoid some typo-based errors by using string constants
    private static final String CANDY_ID = "candyId";
    private static final String DELIVERY_ID = "deliveryId";

    private static final String SELECT_CANDY =
            " SELECT * FROM candy";

    private static final String INSERT_CANDY_TO_DELIVERY =
            "INSERT INTO candy_delivery(candy_id, delivery_id)" +
            "VALUES (:" + CANDY_ID + ", :" + DELIVERY_ID + ")";

    private static final String SELECT_CANDY_BY_DELIVERYID =
            "SELECT * FROM candy_delivery cd" +
            "JOIN candy c" +
            "ON c.id = cd.candy_id" +
            "WHERE cd.delivery_id = :" + DELIVERY_ID;

    private static final RowMapper<Candy> CANDY_ROW_MAPPER =
            new BeanPropertyRowMapper<>(Candy.class);

    @Override
    public List<Candy> getAllCandy() {
        return jdbcTemplate.query(
                SELECT_CANDY,
                CANDY_ROW_MAPPER
        );

    }

    /**
     * [PreparedStatementSetter (Spring Framework 5.3.17 API)](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/PreparedStatementSetter.html)
     * [MapSqlParameterSource (Spring Framework 5.3.17 API)](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/namedparam/MapSqlParameterSource.html)
     */
    @Override
    public void addToDeliveryById(Long candy_id, Long delivery_id) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT_CANDY_TO_DELIVERY,
                new MapSqlParameterSource()
                        .addValue(CANDY_ID, candy_id)
                        .addValue(DELIVERY_ID, delivery_id),
                key
                );
    }

    /**
     * MapSqlParameterSource: A map of parameter names to parameter values
     * [RowCallbackHandler (Spring Framework 5.3.17 API)](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/RowCallbackHandler.html)
     * **/
    @Override
    public List<Candy> findByDelivery(Long delivery_id) {
        return jdbcTemplate.query(
                SELECT_CANDY_BY_DELIVERYID,
                new MapSqlParameterSource(DELIVERY_ID, delivery_id),
                new BeanPropertyRowMapper<>(Candy.class)
        );
    }
}
