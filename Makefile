JDIR = app/src/main/java/io/github/ponyatov/metaL

lexer:
	xterm -e "ls $(JDIR)/*.ragel $(JDIR)/Makefile | entr make -C $(JDIR)"
