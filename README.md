# api-horas
API de registro de usuários e horas trabalhadas

### Operações
* Autenticação
* Registro e listagem de usuários
* Registro e listagem de horas trabalhadas

#### Autenticação
Utilizando o método POST e o endereço [/api/user/auth](/api/user/auth), submeta o usuário com os seguintes dados:
```json
{
    "cpf": 6,
    "password": "1234"
}
```
> Para todas as operações de registro, é necessário que o *Token* retornado pela autenticação seja inserido no cabeçado da requisição com o nome "token", para que a API reconheça a validade da requisição.

#### Lista de Usuários
Utilizando o método GET e o endereço [/api/user/list](/api/user/list), será retornado uma lista de usuários em formato **JSON**.

#### Registro de Usuário
Utilizando o método POST e o endereço [/api/user/register](/api/user/register), submeta o novo usuário com os seguintes dados:
```json
{
    "cpf": 9,
    "firstName": "Funcionário",
    "lastName": "Sobrenome",
    "password":"1234"
}
```
#### Lista de Horas trabalhadas de um usuário
Utilizando o método GET e o endereço [/api/hour/list/{id}](/api/hour/list/{id}), a API retornará as horas totais trabalhadas, os minutos totais trabalhados e uma lista com todos os horários do usuário a partir do parâmetro {id} (tudo em formato **JSON**). 

> Caso o usuário utilize a API como um sistema de ponto, o cálculo de horas e minutos totais não levará em consideração os registros que não possuem horário de fim.

#### Registro de Horas
Utilizando o método POST e o endereço [/api/hour/register](/api/hour/register), submeta o horário com no mínimo 2 campos, como mostra a seguir:
```json
{
    "user":{"cpf":6},
    "dateBegin": "27/12/2017 8:30:00"
}
```
Ou no máximo com os 3 campos, como mostra a seguir:
```json
{
    "user":{"cpf":6},
    "dateBegin": "27/12/2017 8:30:00",
    "dateEnd": "27/12/2017 11:30:00"
}
```
