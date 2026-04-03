public class Case {
    public int valeur;
    public boolean estFixe; // Indique si le chiffre vient du fichier/saisie initiale

    public Case(int valeur) {
        this.valeur = valeur;
        // Si le chiffre n'est pas 0, il est verrouillé (le solveur ne doit pas y toucher)
        this.estFixe = (valeur != 0);
    }
}