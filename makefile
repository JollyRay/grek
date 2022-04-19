DIR = ru/mironova/grek
TARGET = Main
SRC = src
DST = dst

all: clear
	@javac -d $(DST) -cp $(SRC) $(SRC)/$(DIR)/$(TARGET).java
	@java -cp $(DST) $(DIR)/$(TARGET) -r 6 -A 1 test/test2 --exclude=^*

jar: clear
	@javac -d $(DST) -cp $(SRC) $(SRC)/$(DIR)/$(TARGET).java && cd $(DST) && jar -cfe ../term/usr/bin/grek.jar $(DIR)/$(TARGET) $(DIR)/*.class

clear:
	@rm -rf $(DST)/*
	@rm -rf term/usr/bin/grek.jar

deb: clearDeb all
	@cd term && tar czf data.tar.gz usr && tar czf control.tar.gz control && ar -r grek.deb debian-binary control.tar.gz data.tar.gz

clearDeb:
	@cd term &&  && rm -rf data.tar.gz control.tar.gz grek.deb

install: remove deb
	@cd term && sudo dpkg -i grek.deb

remove:
	@sudo apt-get remove grek