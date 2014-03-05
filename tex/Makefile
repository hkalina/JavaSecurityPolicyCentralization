# makefile pro preklad LaTeX verze Bc. prace
# (c) 2008 Michal Bidlo
# E-mail: bidlom AT fit vutbr cz
#===========================================
# asi budete chtit prejmenovat:
CO=projekt

all: $(CO).pdf

pdf: $(CO).pdf

$(CO).ps: $(CO).dvi
	dvips $(CO)

$(CO).pdf: clean
	pdflatex -halt-on-error $(CO)
	bibtex $(CO)
	pdflatex -halt-on-error $(CO)
	pdflatex -halt-on-error $(CO)

$(CO).dvi: $(CO).tex $(CO).bib
	latex -halt-on-error $(CO)
	bibtex $(CO)
	latex -halt-on-error $(CO)
	latex -halt-on-error $(CO)

znaky:
	untex obsah.tex | wc -m

desky:
#	latex desky
#	dvips desky
#	dvipdf desky
	pdflatex -halt-on-error desky

clean:
	rm -f *.dvi *.log $(CO).blg $(CO).bbl $(CO).toc *.aux $(CO).out $(CO).lof
	rm -f $(CO).pdf
	rm -f *~

pack:
	tar czvf bp-xjmeno.tar.gz *.tex *.bib *.bst ./fig/* ./cls/* Makefile Changelog
