# related to shell using

## 1 code for make a git push
git add .
git commit -m "NUMERO_TICKET: BLABLABLA"
git push origin HEAD

## 2
git branch
git checkout -b nom_de_la_nouvelle_branche 
git status
git add .
git commit -m "NUMERO_TICKET: BLABLABLA"
git push origin HEAD

##
find . -type f -name "*.csv"
le type f c'est pour file, pour qu'il cherche que des fichiers (edited) 
le point c'est le répertoire où tu veux chercher
et le name "*.csv" bah c'est pour chercher des csv

Pour afficher tous les répertoires dont le nom se termine par ``s'' :
find . -type d -name "*s" 
