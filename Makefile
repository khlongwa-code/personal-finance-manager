all: build

build:
	scripts/build_script.sh

server:
	scripts/Server.sh

client:
	scripts/Client.sh

clean:
	rm -rf build/