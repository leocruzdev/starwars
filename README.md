# Star Wars Challenge

O projeto Star Wars Challenge é um aplicativo Android desenvolvido para testar o conhecimento dos fãs da franquia Star Wars. O aplicativo foi criado utilizando o pacote `com.dacruz.starwarschallenge` e oferece quizzes, fatos interessantes e informações detalhadas sobre os personagens do universo Star Wars. O projeto segue os conceitos de Clean Architecture e está organizado em camadas e módulos específicos.

## Recursos

- Lista de personagens com informações detalhadas (Home)
- Pesquisa de personagens pela barra de pesquisa (Character)
- Paginação com a Paging Library
- Salvamento de personagens favoritos (Favorite)
- Tema escuro e claro
- Arquitetura em camadas com abstração de banco de dados usando Room
- Navegação personalizada com módulo de navigation
- Integração com a API Star Wars (SWAPI) através do módulo de networking e Retrofit
- Módulo de tema para centralizar configurações de cores, estilos e temas

## Arquitetura e Módulos

O projeto utiliza uma arquitetura em camadas e segue os conceitos de Clean Architecture. Cada feature possui seu próprio conjunto de camadas de data, domain e presentation. As features do projeto incluem:

- Home: exibe a lista de personagens com informações detalhadas
- Character: permite pesquisar personagens usando a barra de pesquisa
- Favorite: possibilita salvar personagens como favoritos

Além disso, o projeto é dividido em vários módulos, como:

- `database`: módulo que contém a abstração do banco de dados com Room
- `navigation`: módulo responsável pela navegação personalizada
- `networking`: módulo que provê o builder do Retrofit com tratamento de erros
- `theme`: módulo que centraliza as configurações de cores, estilos e temas

A aplicação utiliza a abordagem de local e remote data sources, juntamente com repositories, use cases e view models. Para as chamadas de rede e IO, o projeto emprega o uso de Flows.

## Próximos Passos e Melhorias

Algumas melhorias e desenvolvimentos futuros estão planejados para o projeto:

- Utilizar Service Catalog para centralizar as dependências do projeto
- Aumentar a cobertura de testes em todos os módulos (testes realizados em `home` e `character`)
- Componentizar mais composables para reutilização eficiente
- Eliminar repetições e redundâncias no código
- Melhorar a experiência de carregamento de mais personagens na lista da tela inicial
- Aprimorar a experiência de design e a interface do usuário
- Refinar a experiência de navegação entre telas
- Criar um módulo de `commons` para centralizar funcionalidades comuns
- Melhorar o módulo de `theme` com a implementação de um Design System

## Instalação

Para instalar e executar o aplicativo no Android Studio, siga estas etapas:

1. Faça o clone do repositório ou baixe os arquivos zip do projeto.
2. Abra o Android Studio e clique em "Open an existing Android Studio project" (Abrir um projeto Android Studio existente).


## Instalação

Para instalar e executar o aplicativo no Android Studio, siga estas etapas:

1. Faça o clone do repositório ou baixe os arquivos zip do projeto.
2. Abra o Android Studio e clique em "Open an existing Android Studio project" (Abrir um projeto Android Studio existente).
3. Navegue até a pasta do projeto e selecione a pasta raiz do projeto Star Wars Challenge.
4. Aguarde o Android Studio sincronizar e construir o projeto.
5. Execute o aplicativo em um emulador ou dispositivo Android conectado.

## Contato

Se você tiver dúvidas ou sugestões, entre em contato comigo:

- Nome: [Leonardo Oliveira da Cruz]
- E-mail: [leonardo_cruz09@hotmail.com]
- LinkedIn: [https://www.linkedin.com/in/leonardo-oliveira-da-cruz-8566a530/]