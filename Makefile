JDIR = app/src/main/java/io/github/ponyatov/metaL

lexer:
	xterm -e "ls $(JDIR)/*.ragel $(JDIR)/Makefile | entr make -C $(JDIR)"

NOW = $(shell date +%y%m%d)
VER = $(shell git rev-parse --short=4 HEAD)

ver: app/build.gradle
	perl -pi -e "s/(versionCode)\s+.+/\1 $(NOW)/" $<
	perl -pi -e "s/(versionName)\s+.+/\1 \"$(NOW)-$(VER)\"/" $<

MERGE  = Makefile .gitignore README.md
MERGE += app

merge:
	git checkout master
	git checkout ponyatov -- $(MERGE)

release:
	-git tag $(NOW)
	git push -v --tags
	git checkout ponyatov
