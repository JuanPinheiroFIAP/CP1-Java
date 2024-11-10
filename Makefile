clean_and_package:
	mvn clean
	mvn package

run_main_v0:
	java -jar target/CHECKPOINT_1_GRUPO_BATATA_DOCE.jar

run_all: clean_and_package run_main_v0