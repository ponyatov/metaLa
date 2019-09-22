PACK  = app/src/main/java/io/github/ponyatov
METAL = $(PACK)/metaL
FORTH = $(PACK)/FORTH

lexer:
	xterm -e "ls $(METAL)/*.ragel $(METAL)/Makefile | entr make -C $(METAL)" &
	xterm -e "ls $(FORTH)/*.ragel $(FORTH)/Makefile | entr make -C $(FORTH)" &

NOW = $(shell date +%y%m%d)
VER = $(shell git rev-parse --short=4 HEAD)
TAG = $(NOW)-$(VER)

ver: app/build.gradle
	perl -pi -e "s/(versionCode)\s+.+/\1 $(NOW)/" $<
	perl -pi -e "s/(versionName)\s+.+/\1 \"$(TAG)\"/" $<

MERGE  = Makefile .gitignore README.md
MERGE += app

merge:
	git checkout master
	git checkout ponyatov -- $(MERGE)

release:
	-git tag $(TAG)
	git push -v --tags
	git checkout ponyatov
