JDIR = app/src/main/java/io/github/ponyatov/metaL

lexer:
	xterm -e "ls $(JDIR)/*.ragel $(JDIR)/Makefile | entr make -C $(JDIR)"

NOW = $(shell date +%d%m%y)

MERGE  = Makefile .gitignore README.md
MERGE += app

merge:
	git checkout master
	git checkout ponyatov -- $%(MERGE)

release:
	git tag $(NOW)
	git push -v --tags
	git checkout ponyatov
