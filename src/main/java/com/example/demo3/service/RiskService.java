package com.example.demo3.service;

import com.example.demo3.models.Risk;

public interface RiskService {

    Risk getRisk (int riskId);

    Risk createRisk (Risk risk);

    Risk naOcenkyIAnaliz(int riskId);

    Risk minimizatiya(int riskId);

    Risk naKontrole(int riskId);

    Risk zakryt(int riskId);

    String error ();
}
