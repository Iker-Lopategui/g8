# Sistema de Hemograma

Este projeto é uma aplicação JavaFX para a análise de hemogramas.
A aplicação permite a entrada de dados do paciente e dos parâmetros hematológicos, compara esses dados com valores de referência e exibe os resultados na interface gráfica.

## Funcionalidades

- **Entrada de Dados do Paciente:**
  - Nome
  - Idade
  - Categoria de Referência (Criança, Adolescente, Adulto, Idoso)

- **Entrada de Dados do Hemograma:**
  - Hemoglobina
  - Hematócrito
  - Volume Celular Médio
  - Hemoglobina Celular Média
  - Concentração de Hemoglobina Celular
  - Contagem de Glóbulos Vermelhos
  - Amplitude de Distribuição dos Eritrócitos
  - Contagem de Glóbulos Brancos
  - Contagem Diferencial de Glóbulos Brancos (Neutrófilos Bastonetes, Neutrófilos Segmentados, Linfócitos, Monócitos, Eosinófilos, Basófilos)
  - Contagem de Plaquetas

- **Análise dos Dados:**
  - Comparação dos dados inseridos com os valores de referência para a categoria selecionada.
  - Exibição de mensagens sobre valores fora dos intervalos de referência.

- **Armazenamento de Dados:**
  - Armazena informações do paciente e resultados de hemograma em um banco de dados SQLite.

## Pré-Requisitos

- **Java Development Kit (JDK) 11 ou superior**
- **Apache Maven (opcional, para gerenciamento de dependências)**
- **Banco de Dados SQLite**

## Configuração e Execução

1. **Clone o Repositório:**
   ```bash
   git clone https://github.com/poo-ec-2024-1/g8.git
   cd g8

2. **Compile o Projeto**

3. **Configuração do Banco de Dados:**
O caminho do banco de dados é definido no código como pacientes.db localizado no diretório de trabalho atual.
Caso o banco de dados não esteja criado será criado automaticamente quando a aplicação for executada.

## Autores

Líder: Ana Clara Fortunato de Souza
Testador: João Pedro Lopes Siviero
Analista de Sistemas: Jeovane do Nascimento Mendonça
Desenvolvedor Backend: Iker Marciel Lopategui
Desenvolvedor Frontend: Gabriel Ferreira dos Santos