# Trabalho Prático 01 -- Projeto MovieMe

Professor: João Eduardo Montandon

Valor: 12 Pontos

Nº de Integrantes: 2 alunos

## Introdução

O objetivo deste trabalho é desenvolver a primeira versão de um aplicativo que gerencie a biblioteca de filmes do usuário. Uma vez aberto, o aplicativo irá mostrar uma lista de filmes dos quais ele tem interesse em assistir, ou que já assistiu e tem interesse em guardar. Esses filmes -- que foram cadastrados pelo usuário anteriormente -- podem ser compartilhados para outros usuários através do e-mail ou SMS.

Mais Especificamente, você deverá implementar as seguintes funcionalidades:

1. Listagem dos filmes
1. Cadastro de novo filme
1. Exclusão de filme existente
1. Compartilhamento do filme

O restante do documento contém, em detalhes, a descrição das funcionalidades previstas para este trabalho.

## Funcionalidades

### Modelo de Dados

O aplicativo irá manusear uma série de filmes de interesse do usuário. Obrigatóriamente, o aplicativo deverá armazenar as seguintes informações por filme:

* Nome do Filme
* Gênero (Ação, Drama, Comédia, Suspense, Ficção ou Romance)
* Diretor
* Faixa Etária
* Ano de Lançamento

### Listagem de filmes

A Listagem dos filmes representa a principal funcionalidade do aplicativo e, portanto, deverá ser a primeira tela a ser exibida quando o aplicativo é aberto pelo usuário.

A listagem deverá ser renderizada por meio de uma `ListView` customizada. Cada item da lista deverá exibir, obrigatóriamente, o nome, gênero, faixa etária e ano de lançamento. O gênero deverá ser representado por [ícones indicativos](https://en.wikipedia.org/wiki/Brazilian_advisory_rating_system#Rating_ranges).

A lista deverá recuperar seus itens a partir de um arquivo texto que conterá o registro de todos os filmes cadastrados anteriormente.

### Cadastro de novo filme

O aplicativo deverá permitir ao usuário adicionar novos filmes na lista. Para isso, você deverá implementar uma tela cadastro que permitirá ao usuário adicionar novos filmes à medida em que se tornar necessário.

Os filmes cadastrados deverão ser armazenados em um arquivo texto, para que possam ser recuperados posteriormente, mesmo que o aplicativo tenha sido fechado.

### Exclusão de filmes

Ainda, o aplicativo deverá permitir ao usuário que faça a exclusão um determinado filme. A ação de exclusão deverá ser acessada a partir da tela de listagem, onde o usuário poderá tocar no item que vai vir a ser apagado.

Um pop-up de confirmação de exclusão deverá aparecer antes do filme ser efetivamente apagado.

### Compartilhamento do filme

O aplicativo deverá permitir compartilhar determinados filmes com outros usuários. O usuário deverá selecionar o filme a ser compartilhado através da tela de listagem: o usuário toca no filme, uma tela própria irá se abrir, e nessa tela o usuário poderá visualizar as informações detalhadas do filme como também compartilhá-las com alguem.

Deverão ser implementados dois meios de compartilhamento: por e-mail e via SMS. Em ambos os compartilhamentos, deverão ser enviados todos os dados do filme.

### Internacionalização

Você deverá implementar a internacionalização do seu aplicativo para duas línguas quaisquer, sendo Português (Brasil) uma delas.

### Entrega e Apresentação

O aplicativo deverá ser entregue até o dia 23 de abril, utilizando o mecanismo de pull request. Além disso, o trabalho deverá ser apresentado para o professor no dia a ser definido pela escola.