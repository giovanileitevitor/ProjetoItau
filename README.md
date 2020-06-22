# ProjetoItau
Projeto-Teste-Itau

Projeto: App Teste para processo Itau - Dev Mobile
Detalhes:
- Composto por 3 telas (Splash, Repository, Details)
- Cada tela possui sua respectiva viewModel

O App está dividido em 4 módulos:
- App: módulo principal que contem as libs e manifestos
- Data: módulo que contem toda a modelagem de dados, parse de json, entidades, mappers
- Infra: módulo que contem as configuracoes do Rx, extensoes, etc
- features: módulo que contem os layouts, activities, viewmodels e demais recursos

Detalhes de cada tela:
- Icon
https://user-images.githubusercontent.com/27828713/85243347-07b8fc80-b418-11ea-93f4-35a11904a836.png

- Tela splahs
https://user-images.githubusercontent.com/27828713/85243351-0a1b5680-b418-11ea-9df9-4bb8b290bca5.png

- Tela Repo
https://user-images.githubusercontent.com/27828713/85243356-0d164700-b418-11ea-9240-f0e224500206.png
Contem Toolbar com dados dinamicos (Total de repositórios encontrados por página)
Contém BottomBar com dados dinamicos (Número da pagina atual e controles de navegacao)

- Tela Details
https://user-images.githubusercontent.com/27828713/85243359-10113780-b418-11ea-80b8-2fb7fd06609c.png
Contem Toolbar com controle de navegacao (back buttom)
Contém BottomBar com dados dinamicos (Número de pull requests abertos/fechados)
