install:
	mvn install

test:
	mvn test

clean:
	rm -rf target
docs:
	mvn javadoc:javadoc
