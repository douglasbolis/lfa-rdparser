space:=$(empty) $(empty)

# Binários
JAVAC=/usr/bin/javac
JAVA=/usr/bin/java
JAR=/usr/bin/jar

# Diretórios destinos
DISTDIR=dist

# Adicione qualquer classpath externo que você precise
USERCLASSPATH=.

# Criando classpath dinâmico
TMPCLASSPATH=$(USERCLASSPATH):$(realpath $(BASE)$(DISTDIR))
ifneq (,$(wildcard $(jars)/*))
	CLASSPATH=$(TMPCLASSPATH):$(subst $(space),:,$(foreach jar,$(wildcard *.jar),$(realpath $(jar))))
endif

# Flags de compilação
JCFLAGS=-g -d $(BASE)$(DISTDIR) -classpath $(CLASSPATH)
# Flags de execução
JFLAGS=-classpath $(CLASSPATH)

build:
	rm -rf *.jar $(BASE)$(DISTDIR) && mkdir $(BASE)$(DISTDIR)
	$(JAVAC) $(JCFLAGS) Main.java
	$(JAR) cfe trab1.jar Main -C $(BASE)$(DISTDIR)/ .

run:
	@java -jar trab1.jar

clean:
	-find . -type d -name $(DISTDIR) | xargs -I{} rm -rf {}
	-rm -f *.jar

all:
	build
