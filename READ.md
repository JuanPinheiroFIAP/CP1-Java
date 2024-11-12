# Checkpoint 1 - Gerador de Sequências de Nucleotídeos

Projeto desenvolvido para o Checkpoint 1

## Integrantes do Grupo
- Nome:Lucca Alexandre                          RM:99700
- Nome:Juan Pinheiro de França                  RM:552202
- Nome:Vitor da Silva Santana                   RM:99586
- Nome:Victor Augusto Wittner                   RM:98667

## Descrição do Projeto
Este Projeto recebe e devolve uma sequencia de Necleotideos.

### Funcionalidades Principais
- Recebe um arquivo .txt com uma sequências de nucleotídeos A, C, T e G
- Salvamento automático da sequência em arquivo texto
- Interface de linha de comando amigável
- Tratamento de erros e validações

## Como Executar o Projeto

1. Compile o projeto:
```bash
mvn clean package
```

2. Execute o programa:
```bash
java -jar target/CHECKPOINT_1_GRUPO_BATATA_DOCE-1.0-SNAPSHOT.jar inputs/<nome_arquivo_entrada.txt> outputs/<nome_arquivo_saida>.txt
```

### Exemplo de Uso
```bash
java -jar target/CHECKPOINT_1_GRUPO_BATATA_DOCE-1.0-SNAPSHOT.jar inputs/input.txt outputs/output.txt
```

## Estrutura do Código

### Principais Classes
- `App`
- `CompressorRLE`
- `TesteApp`
