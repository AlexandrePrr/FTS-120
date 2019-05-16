$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("DaConsultaAoCarrinho.feature");
formatter.feature({
  "line": 1,
  "name": "ConsultaCompra",
  "description": "",
  "id": "consultacompra",
  "keyword": "Feature"
});
formatter.before({
  "duration": 24539872600,
  "status": "passed"
});
formatter.scenario({
  "line": 2,
  "name": "Caminho Feliz",
  "description": "",
  "id": "consultacompra;caminho-feliz",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 3,
  "name": "que acesso o site Submarino",
  "keyword": "Given "
});
formatter.step({
  "line": 4,
  "name": "pesquiso por \"smartphone\" e pressiona Enter",
  "keyword": "When "
});
formatter.step({
  "line": 5,
  "name": "exibe a lista de produtos relacionados com \"smartphone\"",
  "keyword": "Then "
});
formatter.match({
  "location": "DaConsultaAoCarrinho.que_acesso_o_site_Submarino()"
});
formatter.result({
  "duration": 41224706900,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "smartphone",
      "offset": 14
    }
  ],
  "location": "DaConsultaAoCarrinho.pesquiso_por_e_pressiona_Enter(String)"
});
formatter.result({
  "duration": 22049236700,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "smartphone",
      "offset": 44
    }
  ],
  "location": "DaConsultaAoCarrinho.exibe_a_lista_de_produtos_relacionados_com(String)"
});
formatter.result({
  "duration": 453470699,
  "status": "passed"
});
formatter.after({
  "duration": 892116500,
  "status": "passed"
});
});