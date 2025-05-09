# Projeto: scheduling-financial
Projeto de Avaliação usando SpringBoot e Angular

## Sumário

- [Funcionalidades](#funcionalidades)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Como Usar](#como-usar)
- [Exemplos](#exemplos)
- [Contato](#contato)

## Funcionalidades

Desenvolver um sistema de agendamento de transferências financeiras. 
O usuário deve poder agendar uma transferência financeira com as seguintes informações: 

1. Conta de origem (padrão XXXXXXXXXX), conta de destino (padrão XXXXXXXXXX), valor da transferência, taxa (a ser calculada conforme tabela abaixo), data da transferência (data em que será realizada a transferência) e data de agendamento (hoje); 
2. O cálculo da taxa sobre o valor a ser transferido depende da data de transferência.
Observação: Caso não haja taxa aplicável, lançar um alerta sobre o erro e não permitir tranferência. 
3. O usuário deve poder ver o extrato de todos os agendamentos cadastrados. 
Nota: A persistência deve ser feita em banco de dados em memória (h2, por exemplo). 

## Pré-requisitos

Desenvolver tanto a API quanto o front-end (Spring boot e VueJs no front, caso não tenha conhecimentos de VueJs, pode ser feito o front com angular). Utilizar a versão 11 do java. 

## Instalação

1. Clone o repositório:

Backend --> 
Spring Boot (v2.7.18)
Java 11.0.27

Frontend --> (Obs: O diretório 'node_modules' não está no GitHub devido ao tamanho e quantidade de arquivos)
Angular CLI: 19.2.11
Node: 22.15.0
Package Manager: npm 10.9.2
OS: win32 x64

Angular: 19.2.10
... common, compiler, compiler-cli, core, forms
... platform-browser, platform-browser-dynamic, router

Package                         Version
---------------------------------------------------------
@angular-devkit/architect       0.1902.11
@angular-devkit/build-angular   19.2.11
@angular-devkit/core            19.2.11
@angular-devkit/schematics      19.2.11
@angular/cli                    19.2.11
@schematics/angular             19.2.11
rxjs                            7.8.2
typescript                      5.7.3
zone.js                         0.15.0

## Como Usar

Backend --> http://localhost:8080
Frontend --> http://localhost:4200

Deve inserir os dados de Conta Origem, Conta Destino, Valor e Data de Agendamento

## Exemplos

POST --> http://localhost:8080/v1/transfers
{
    "targetAccount": "1479823299",
    "sourceAccount": "2625141566",
    "amount": 10,
    "rate": 25,
    "dataTransfer": "2025-05-09T00:00:00.000Z",
    "appointmentDate": "2025-05-30T00:00:00.000Z"
}

GET --> http://localhost:8080/v1/transfers

## Contato

- LinkedIn: https://www.linkedin.com/in/marcososorio86/
- E-mail: marcos.osorio86@yahoo.com.br
