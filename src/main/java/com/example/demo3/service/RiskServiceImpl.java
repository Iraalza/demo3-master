package com.example.demo3.service;

import com.example.demo3.exceptions.ContactNotFoundException;
import com.example.demo3.exceptions.RiskNotFoundException;
import com.example.demo3.models.Risk;
import com.example.demo3.repositories.RiskRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Primary
@Service
public class RiskServiceImpl implements RiskService {
    private static final String SQL_NA_OCENKY_I_ANALIZ = "UPDATE risks set risk_status = '2. На оценку и анализ' where risk_id = :riskId";
    private static final String SQL_MINIMIZATIYA = "UPDATE risks set risk_status = '3. Минимизация' where risk_id = :riskId";
    private static final String SQL_NA_KONTROLE = "UPDATE risks set risk_status = '4. На контроле' where risk_id = :riskId";
    private static final String SQL_ZAKRYT = "UPDATE risks set risk_status = '5. Закрыт' where risk_id = :riskId";
    private final RiskRepository riskRepository;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public RiskServiceImpl (RiskRepository riskRepository, NamedParameterJdbcTemplate jdbcTemplate){
        this.riskRepository = riskRepository;
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Risk getRisk (int riskId){
        return riskRepository.findById(riskId).orElseThrow(() -> new RiskNotFoundException(riskId));
    }
    @Override
    public Risk createRisk(Risk risk){
        return riskRepository.save(risk);
    }
    @Override
    public Risk naOcenkyIAnaliz (int riskId){
        var params = new MapSqlParameterSource();
        params.addValue("riskId", riskId);
        jdbcTemplate.update(SQL_NA_OCENKY_I_ANALIZ, params);
        return riskRepository.findById(riskId).orElseThrow(() -> new RiskNotFoundException(riskId));
    }
    @Override
    public Risk minimizatiya (int riskId){
        var params = new MapSqlParameterSource();
        params.addValue("riskId", riskId);
        jdbcTemplate.update(SQL_MINIMIZATIYA, params);
        return riskRepository.findById(riskId).orElseThrow(() -> new RiskNotFoundException(riskId));
    }
    @Override
    public Risk naKontrole (int riskId){
        var params = new MapSqlParameterSource();
        params.addValue("riskId", riskId);
        jdbcTemplate.update(SQL_NA_KONTROLE, params);
        return riskRepository.findById(riskId).orElseThrow(() -> new RiskNotFoundException(riskId));
    }
    @Override
    public Risk zakryt (int riskId){
        var params = new MapSqlParameterSource();
        params.addValue("riskId", riskId);
        jdbcTemplate.update(SQL_ZAKRYT, params);
        return riskRepository.findById(riskId).orElseThrow(() -> new RiskNotFoundException(riskId));
    }

    @Override
    public String error(){
        return RiskNotFoundException.getError();
    }

}
